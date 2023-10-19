package com.example.oct11.recycleronlineapi.detailsofcars

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oct11.R
import com.example.oct11.recycleronlineapi.dataclass.ItemsViewModelOnline
import com.example.oct11.recycleronlineapi.detailsofcars.dataclass.ItemsViewModelOnlineDetails


class CarDetailsFragment : Fragment() {

    val data = ArrayList<ItemsViewModelOnlineDetails>()
    lateinit var adapter: AdapterOnlineDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_third, container, false)
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Retrieve the Parcelable item from the arguments
//        val clickedItem = requireArguments().getParcelable<ItemsViewModelOnline>("clickedItem")
//
//
//        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerviewOnlineDetail)
//
//        // this creates a vertical layout Manager
//        recyclerview.layoutManager = LinearLayoutManager(requireContext())
//
//        // ArrayList of class ItemsViewModel
//
//
//        // This loop will create 20 Views containing
//        // the image with the count of view
////        for (i in 1..20) {
////            data.add(ItemsViewModelOnlineDetails( "Item " + i))
////        }
//
//        // This will pass the ArrayList to our Adapter
//
//
//        // This loop will create items for the data list
//        if (clickedItem != null) {
//            val temp = clickedItem.vTp
//            if (temp != null) {
//                for (item in temp) {
//                    val name = item.Name.toString()
//                    Log.d("jil", name)
//                    data.add(ItemsViewModelOnlineDetails(name))
//                }
//            }
//        }
//
//
//        adapter = AdapterOnlineDetails(data)
//
//        // Setting the Adapter with the recyclerview
//        recyclerview.adapter = adapter
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_car_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clickedItem = requireArguments().getParcelable<ItemsViewModelOnline>("clickedItem")

        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerviewOnlineDetail)
        val textMan = view.findViewById<TextView>(R.id.manufactureName)
        val textCount = view.findViewById<TextView>(R.id.countryName)
        textMan.text = clickedItem!!.textOne
        textCount.text = clickedItem.textTwo
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        try{
            if (clickedItem != null) {
                val temp = clickedItem.vTp
                if (temp != null) {
                    for (item in temp) {
                        val name = item.Name
                        Log.d("jil", name)
                        data.add(ItemsViewModelOnlineDetails(name))
                    }
                }
            }

            adapter = AdapterOnlineDetails(data)
            recyclerview.adapter = adapter
        } catch (e: Exception) {
            Log.e("DataLoadingError", "An error occurred while loading data: ${e.message}")
        }

    }


    companion object {

        fun newInstance(clickedItem: ItemsViewModelOnline) =
            CarDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("clickedItem", clickedItem)
                }
            }
    }
}