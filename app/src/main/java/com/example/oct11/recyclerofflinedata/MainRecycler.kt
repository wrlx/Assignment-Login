package com.example.oct11.recyclerofflinedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oct11.R
import com.example.oct11.recycleronlineapi.CarApiFetchable
import com.example.oct11.recycleronlineapi.RetrofitHelperCar


class MainRecycler : AppCompatActivity() {

    val data = ArrayList<ItemsViewModel>()
    lateinit var adapter: AdapterOffline
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_recycler)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)


        val carApi = RetrofitHelperCar.getInstance().create(CarApiFetchable::class.java)

        for (i in 1..86) {
            data.add(ItemsViewModel("jinu", "item" + i))
        }

        val adapter = AdapterOffline(data)

        recyclerview.adapter = adapter


    }
}