package com.example.oct11.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oct11.R

class AdapterOffline(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<AdapterOffline.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        holder.itemOne.text = ItemsViewModel.textOne
        holder.itemTwo.text = ItemsViewModel.textTwo

    }
    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val itemOne: TextView = itemView.findViewById(R.id.textOne)
        val itemTwo: TextView = itemView.findViewById(R.id.textTwo)
    }
}
