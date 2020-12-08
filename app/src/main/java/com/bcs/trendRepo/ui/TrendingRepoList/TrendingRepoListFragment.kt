package com.bcs.trendRepo.ui.TrendingRepoList

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcs.trendRepo.R
import com.bcs.trendRepo.data.entity.TrendingRepoResponse
import com.bcs.trendRepo.data.repository.TrendRepoDBListRepository
import com.bcs.trendRepo.databinding.TrendRepoFragBinding
import com.bcs.trendRepo.di.Injectable
import com.bcs.trendRepo.util.Resource
import com.bcs.trendRepo.util.Status
import com.bcs.trendRepo.util.autoCleared
import javax.inject.Inject


class TrendingRepoListFragment : Fragment(), Injectable,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var trendingRepoViewModel: TrendingRepoViewModel

    var binding by autoCleared<TrendRepoFragBinding>()

    lateinit var adapter: RepoListAdapter

    companion object {
        var trendingRepoList = mutableListOf<TrendingRepoResponse>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        trendingRepoViewModel =
            ViewModelProvider(this, viewModelFactory).get(TrendingRepoViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.trend_repo_frag,
            container,
            false
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.search.setOnClickListener {
            // Navigate from this fragment to search fragment
            navController().navigate(R.id.searchFrag)
        }

        binding.tryAgain.setOnClickListener {
            fetchData()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSwipeToRefresh()
        fetchData()

    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        adapter.filter(newText)
        return false
    }

    private fun initSwipeToRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            trendingRepoViewModel.refresh()
        }
    }

    // Method to fetch data from api response and populate data into customized adapter
    private fun fetchData() {

        try {

            if (isConnectedToInternet()) {
                // If network is enabled this will execute

                binding.noNetwotkLayout.isVisible = false
                binding.search.isVisible = true
                binding.trendRepoTxt.isVisible = true


                trendingRepoViewModel.getMyTrendRepoList().observe(viewLifecycleOwner, Observer {

                    if (!binding.swipeRefresh.isRefreshing) {
                        // To show loading progress bar
                        binding.resource = Resource(Status.LOADING, null, null)
                    }

                    val trendingRepoResponse: List<TrendingRepoResponse>? = it.data

                    trendingRepoList.clear()

                    if (trendingRepoResponse != null) {
                        trendingRepoResponse.forEach {
                            trendingRepoList.add(it)
                        }
                        // Once data loaded disable loading progress bar
                        binding.swipeRefresh.isRefreshing = false
                        binding.resource = Resource(Status.SUCCESS, null, null)

                        // Method to insert into database
                        insertDataIntoDB(trendingRepoList)
                    }

                    // Initialize recyclerview view and populate data into it
                    binding.recyclerView.layoutManager = LinearLayoutManager(activity)
                    adapter = RepoListAdapter(trendingRepoList)
                    binding.recyclerView.adapter = adapter

                })
            } else {
                // To fetch data from local and populate into adapter (Offline mode)

                binding.swipeRefresh.isEnabled = false

                if (!getDataFromDB()) {
                    binding.noNetwotkLayout.isVisible = true
                    binding.trendRepoTxt.isVisible = false
                    binding.search.isVisible = false
                } else {
                    binding.noNetwotkLayout.isVisible = false
                }

                binding.recyclerView.layoutManager = LinearLayoutManager(activity)
                adapter = RepoListAdapter(trendingRepoList)
                binding.recyclerView.adapter = adapter
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Method to insert data into room database
    private fun insertDataIntoDB(list: List<TrendingRepoResponse>) {
        val trendRepo = TrendRepoDBListRepository(requireContext())
        // Before accessing data , delete all will remove existing data
        trendRepo.deleteAll()

        list.forEach {
            trendRepo.insertData(it)
        }

    }

    // Method to retrieve data from database and put into list
    private fun getDataFromDB(): Boolean {
        val trendRepo = TrendRepoDBListRepository(requireContext())

        val trendRepoList = trendRepo.getAllData()
        trendingRepoList.clear()
        trendRepoList.forEach {
            trendingRepoList.add(it)
        }

        return trendingRepoList.size > 0

    }

    // Method to navigate from one fragment to another fragment
    private fun navController() = findNavController()

    // Method to check whether internet is connected or not
    private fun isConnectedToInternet(): Boolean {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }
}