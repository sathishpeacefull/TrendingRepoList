//package com.bcs.trendRepo.ui.TrendingRepoList
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bcs.trendRepo.R
//import com.bcs.trendRepo.data.entity.TrendingRepoResponse
//
//class TrendingRepoViewHolder(
//    view: View, private val trendingRepoResponse: List<TrendingRepoResponse>?,
//    private val itemClickCallBack: (Int) -> Unit
//
//) : RecyclerView.ViewHolder(view) {
//    private val author: TextView = view.findViewById(R.id.author)
//
//    private var item: List<TrendingRepoResponse>? = null
//
//    fun bind(item: List<TrendingRepoResponse>?, position: Int) {
//        try {
//            this.item = item
//
//            if (item != null) {
//                author.text = item.get(position).author
//            }
//
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    companion object {
//        fun create(
//            viewGroup: ViewGroup,
//            trendingRepoResponse: List<TrendingRepoResponse>?,
//            itemClickCallBack: (Int) -> Unit
//        ): TrendingRepoViewHolder {
//            val view = LayoutInflater.from(viewGroup.context)
//                .inflate(R.layout.trend_repo_list, viewGroup, false)
//            return TrendingRepoViewHolder(view, trendingRepoResponse, itemClickCallBack)
//        }
//    }
//}