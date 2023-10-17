package com.example.oct11.recyclerOnline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oct11.R
import com.example.oct11.recycler.AdapterOffline
import com.example.oct11.recycler.ItemsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainRecyclerOnline : AppCompatActivity() {
    val dataOnline = ArrayList<ItemsViewModel>()

//    val data = ArrayList<ItemsViewModel>()
    lateinit var adapter: AdapterOffline
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_recycler_online)

        val carApi = RetrofitHelperCar.getInstance().create(CarApiFetchable::class.java)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerviewonline)


        recyclerview.layoutManager = LinearLayoutManager(this)


        GlobalScope.launch {
            val result = carApi.getCarData()
            if (result != null) {
                val temp = result.body()?.Results

                if (temp != null) {
                    for (item in temp) {
                        val country = item.Country
                        val mfrName = item.Mfr_Name
                        dataOnline.add(ItemsViewModel(mfrName, country))
                    }
                    runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
        adapter = AdapterOffline(dataOnline)
        recyclerview.adapter = adapter

    }
}