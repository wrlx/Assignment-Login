package com.example.oct11.carmvvm

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oct11.R


class CarTypeAdapter(private val mList: List<VehicleType>,) : RecyclerView.Adapter<CarTypeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_online_details, parent, false)


        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return mList.size
    }

    override fun onBindViewHolder(holder: CarTypeAdapter.ViewHolder, position: Int) {

        var vehicleType:String=(position+1).toString() +". "+ mList[position].name
        Log.d("jinu", vehicleType)

        holder.vehicleType.text=vehicleType



    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


        val vehicleType: TextView = itemView.findViewById(R.id.onlineDetails)
    }
}