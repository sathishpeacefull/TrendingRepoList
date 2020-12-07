package com.bcs.trendRepo.ui.Search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcs.trendRepo.R
import com.bcs.trendRepo.databinding.SearchFragmentBinding
import com.bcs.trendRepo.ui.TrendingRepoList.RepoListAdapter
import com.bcs.trendRepo.ui.TrendingRepoList.TrendingRepoListFragment.Companion.trendingRepoList
import com.bcs.trendRepo.util.autoCleared

class SearchFrag : Fragment(), SearchView.OnQueryTextListener {

    var binding by autoCleared<SearchFragmentBinding>()

    lateinit var adapter: RepoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.search_fragment,
            container,
            false
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.searchInput.setOnQueryTextListener(this)

        // Destroy this fragment and get back to previous fragment
        binding.back.setOnClickListener {
            activity?.onBackPressed()
        }

        // Initialize a recyclerview and fetch data into adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = RepoListAdapter(trendingRepoList)
        binding.recyclerView.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        // The text gets from the user input and pass into adapter filter function to filter content
        adapter.filter(newText)
        return false
    }

}