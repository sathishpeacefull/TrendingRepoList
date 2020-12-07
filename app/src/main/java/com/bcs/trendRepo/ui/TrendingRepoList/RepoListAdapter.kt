package com.bcs.trendRepo.ui.TrendingRepoList

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bcs.trendRepo.R
import com.bcs.trendRepo.data.entity.TrendingRepoResponse
import com.bcs.trendRepo.ui.TrendingRepoList.TrendingRepoListFragment.Companion.trendingRepoList
import java.util.*


class RepoListAdapter(var list: MutableList<TrendingRepoResponse>) :
    RecyclerView.Adapter<RepoListAdapter.MyViewHolder>() {

    private val arrayList: ArrayList<TrendingRepoResponse> = ArrayList()

    init {
        this.arrayList.addAll(trendingRepoList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.trend_repo_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(myHolder: MyViewHolder, position: Int) {
        val user = list.get(position)
        myHolder.bind(user)
    }

    // Filter list by author and language
    fun filter(charText: String) {
        var text = charText.toLowerCase(Locale.getDefault())
        trendingRepoList.clear()
        if (text.isEmpty()) {
            trendingRepoList.addAll(arrayList)
        } else {
            for (data in arrayList) {
                if (data.author.toLowerCase().contains(text) || data.language.toLowerCase()
                        .contains(text)
                ) {
                    trendingRepoList.add(data)
                }
            }
        }
        notifyDataSetChanged()
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var autor: TextView? = null
        private var desc: TextView? = null
        private var lang: TextView? = null
        private var stars: TextView? = null
        private var button: ImageView? = null

        init {
            autor = itemView.findViewById(R.id.author)
            desc = itemView.findViewById(R.id.desc)
            lang = itemView.findViewById(R.id.lang)
            stars = itemView.findViewById(R.id.stars)
            button = itemView.findViewById(R.id.button)
        }

        fun bind(data: TrendingRepoResponse) {
            autor?.text = data.author
            desc?.text = data.description
            lang?.text = data.language
            stars?.text = data.stars.toString()

            // Set color dynamically as per hex code received from api response
            (button?.getBackground() as GradientDrawable).setColor(Color.parseColor(data.languageColor))
        }

    }

}
