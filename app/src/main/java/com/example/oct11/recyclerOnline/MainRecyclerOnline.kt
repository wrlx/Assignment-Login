package com.example.oct11.recyclerOnline

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oct11.R
import com.example.oct11.recyclerOnline.dataclass.ItemsViewModelOnline
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainRecyclerOnline : AppCompatActivity() {
    val dataOnline = ArrayList<ItemsViewModelOnline>()


    lateinit var adapter: AdapterOnline
//    fun View.show() {
//        visibility = View.VISIBLE
//    }
//    fun View.hide() {
//        visibility = View.GONE
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_recycler_online)

        val carApi = RetrofitHelperCar.getInstance().create(CarApiFetchable::class.java)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerviewonline)
        val progerssBar = findViewById<ProgressBar>(R.id.progressBarRecycler)

        recyclerview.layoutManager = LinearLayoutManager(this)


        GlobalScope.launch {
            progerssBar.visibility = View.VISIBLE
//            recyclerview.visibility = View.INVISIBLE
            val result = carApi.getCarData()

            progerssBar.visibility = View.INVISIBLE

//            recyclerview.visibility = View.VISIBLE

            if (result != null) {
                val temp = result.body()?.Results

                if (temp != null) {
                    for (item in temp) {
                        val country = item.Country
                        val mfrName = item.Mfr_Name
                     val vType = item.VehicleTypes
                        dataOnline.add(ItemsViewModelOnline(mfrName, country, vType))
                    }
                    runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
        adapter = AdapterOnline(dataOnline)
        recyclerview.adapter = adapter
        progerssBar.visibility=View.GONE

        adapter.setOnItemClickListener(object  : AdapterOnline.onItemClickListener{
            override fun onItemClick(position: Int) {
                Log.d("mm","som")
            }
        })
    }
}