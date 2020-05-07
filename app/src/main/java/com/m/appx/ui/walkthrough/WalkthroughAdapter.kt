package com.m.appx.ui.walkthrough

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.m.appx.R

class WalkThroughAdapter : RecyclerView.Adapter<WalkThroughAdapter.WalkThroughViewHolder>() {

    var data = arrayListOf<String>("a", "b", "c")

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
        holder.data.text = data[position]

    }

    class WalkThroughViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var data = itemView.findViewById<TextView>(R.id.tv_walk_through)
    }
}