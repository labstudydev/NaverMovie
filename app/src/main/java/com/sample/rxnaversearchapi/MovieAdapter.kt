package com.sample.rxnaversearchapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val itemClickListener: (itemUrl: String) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private val movieItemList by lazy { mutableListOf<MovieItem>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view, itemClickListener)
    }

    override fun getItemCount(): Int =
        movieItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(movieItemList[position])

    fun replaceAll(movieItemList: List<MovieItem>) {
        this.movieItemList.clear()
        this.movieItemList.addAll(movieItemList)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View, private val itemClickListener: (itemUrl: String) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val ivImage: ImageView = itemView.findViewById(R.id.iv_img)
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val tvRate: TextView = itemView.findViewById(R.id.tv_rate)
        private val tvPubDate: TextView = itemView.findViewById(R.id.tv_pub_date)
        private val tvDirector: TextView = itemView.findViewById(R.id.tv_director)
        private val tvActor: TextView = itemView.findViewById(R.id.tv_actor)

        fun bind(item: MovieItem) {
            itemView.setOnClickListener {
                itemClickListener(item.link)
            }
            Glide.with(itemView.context)
                .load(item.image)
                .into(ivImage)
            tvTitle.text = item.title
            tvRate.text = item.userRating
            tvPubDate.text = item.pubDate
            tvDirector.text = item.director
            tvActor.text = item.actor
        }
    }
}