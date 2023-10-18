package com.example.oct11.recyclerOnline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oct11.R
import com.example.oct11.recyclerOnline.dataclass.ItemsViewModelOnline
import java.security.PrivateKey


class AdapterOnline(private val mList: ArrayList<ItemsViewModelOnline>) : RecyclerView.Adapter<AdapterOnline.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_online, parent, false)
        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModelOnline = mList[position]
        holder.itemOne.text = ItemsViewModelOnline.textOne
        holder.itemTwo.text = ItemsViewModelOnline.textTwo


    }
    override fun getItemCount(): Int {
        return mList.size
    }



    class ViewHolder(ItemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(ItemView) {
        val itemOne: TextView = itemView.findViewById(R.id.textOnlineOne)
        val itemTwo: TextView = itemView.findViewById(R.id.textOnlineTwo)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(absoluteAdapterPosition)
            }
        }
    }
}
