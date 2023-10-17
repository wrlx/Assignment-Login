package com.example.oct11.recyclerOnline

import com.example.oct11.recyclerOnline.dataclass.ItemsViewModelOnline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oct11.R

class AdapterOnline(private val mList: List<ItemsViewModelOnline>) : RecyclerView.Adapter<AdapterOnline.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_online, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        holder.itemOne.text = ItemsViewModel.textOnlineOne
        holder.itemTwo.text = ItemsViewModel.textOnlineTwo

    }
    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val itemOne: TextView = itemView.findViewById(R.id.textOnlineOne)
        val itemTwo: TextView = itemView.findViewById(R.id.textOnlineTwo)
    }
}
