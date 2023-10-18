package com.example.oct11


import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oct11.recyclerOnline.AdapterOnline
import com.example.oct11.recyclerOnline.CarApiFetchable
import com.example.oct11.recyclerOnline.RetrofitHelperCar
import com.example.oct11.recyclerOnline.ThirdFragment
import com.example.oct11.recyclerOnline.dataclass.ItemsViewModelOnline
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    val dataOnline = ArrayList<ItemsViewModelOnline>()
    lateinit var adapter: AdapterOnline
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.activity_main_recycler_online, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carApi = RetrofitHelperCar.getInstance().create(CarApiFetchable::class.java)

        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerviewonline)
        val progerssBar = view.findViewById<ProgressBar>(R.id.progressBarRecycler)

        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        try {

            GlobalScope.launch {
//            progerssBar.visibility = View.VISIBLE
                val result = carApi.getCarData()

                progerssBar.visibility = View.INVISIBLE

                if (result != null) {
                    val temp = result.body()?.Results

                    if (temp != null) {
                        for (item in temp) {
                            val country = item.Country
                            val mfrName = item.Mfr_Name
                            val vType = item.VehicleTypes
                            dataOnline.add(ItemsViewModelOnline(mfrName, country, vType))
                        }

                        requireActivity().runOnUiThread {
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }

            adapter = AdapterOnline(dataOnline)
            recyclerview.adapter = adapter

        }catch (e: Exception) {
            // Handle the exception here
            Log.e("NetworkError", "An error occurred: ${e.message}")
            progerssBar.visibility = View.INVISIBLE
        }


        adapter.setOnItemClickListener(object : AdapterOnline.onItemClickListener {
            override fun onItemClick(position: Int) {
                val clickedItem = dataOnline[position]

                val newFragment = ThirdFragment.newInstance(clickedItem)
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainer, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance(clickedItem: ItemsViewModelOnline) =
            ThirdFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("clickedItem", clickedItem)
                }
            }
    }
}