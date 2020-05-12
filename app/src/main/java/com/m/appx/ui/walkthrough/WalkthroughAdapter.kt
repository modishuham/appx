package com.m.appx.ui.walkthrough

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.m.appx.R

class WalkThroughAdapter : RecyclerView.Adapter<WalkThroughAdapter.WalkThroughViewHolder>() {

    var data = arrayListOf(
        R.drawable.ic_walkthrough_profile,
        R.drawable.ic_walkthrough_search,
        R.drawable.ic_walkthrough_find,
        R.drawable.ic_walkthrough_chat
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalkThroughViewHolder {
        return WalkThroughViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_walkthrough,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: WalkThroughViewHolder, position: Int) {
        holder.image.setBackgroundResource(data[position])
    }

    class WalkThroughViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.iv_walk_through)
    }
}