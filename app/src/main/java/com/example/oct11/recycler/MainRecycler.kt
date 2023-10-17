package com.example.oct11.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oct11.R
import com.example.oct11.recyclerOnline.CarApiFetchable
import com.example.oct11.recyclerOnline.RetrofitHelperCar
import com.example.oct11.recyclerOnline.dataclass.CarDetails
import com.example.oct11.recyclerOnline.dataclass.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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