//package com.bcs.trendRepo.ui.TrendingRepoList
//
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.bcs.trendRepo.data.entity.TrendingRepoResponse
//import com.bcs.trendRepo.ui.TrendingRepoList.TrendingRepoViewModel.Companion.trendingRepoResponse
//
//class TrendingRepoAdapter(
//    private val itemClickCallback: ((Int) -> Unit)
//) : ListAdapter<TrendingRepoResponse, RecyclerView.ViewHolder>(ITEM_COMPARATOR) {
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return TrendingRepoViewHolder.create(parent, trendingRepoResponse, itemClickCallback)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        (holder as TrendingRepoViewHolder).bind(trendingRepoResponse, position)
//    }
//
//    override fun getItemCount(): Int {
//        return super.getItemCount()
//    }
//
//
//    companion object {
//        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<TrendingRepoResponse>() {
//            override fun areItemsTheSame(
//                oldItem: TrendingRepoResponse,
//                newItem: TrendingRepoResponse
//            ): Boolean {
//                oldItem == newItem
//                return true
//            }
//
//            override fun areContentsTheSame(
//                oldItem: TrendingRepoResponse,
//                newItem: TrendingRepoResponse
//            ): Boolean {
//                oldItem == newItem
//                return true
//            }
//        }
//    }
//
//}