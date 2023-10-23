package com.example.oct11.carmvvm

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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CarDetailsFragmentMvvm.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarDetailsFragmentMvvm : Fragment() {
    // TODO: Rename and change types of parameters

    private var carModel: CarModel? = null
    private  val CAR_OBJ = "carModel"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            carModel = it.getParcelable(CAR_OBJ) as CarModel?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_details_mvvm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val carManufactureNameText : TextView? = view?.findViewById(R.id.manufactureName)
        val countryNameText: TextView?= view.findViewById(R.id.countryName)

        val vehicleType: RecyclerView =view.findViewById(R.id.recyclerviewOnlineDetail)

        vehicleType.layoutManager = LinearLayoutManager(this.context)

        carModel?.let {
            carManufactureNameText?.text = it.carManufactureName
            countryNameText?.text = it.countryName
            Log.d("addiosm", it.carManufactureName.toString())
            val adapter= CarTypeAdapter(carModel!!.vehicleType)

            vehicleType.adapter=adapter
        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CarDetailsFragmentMvvm.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(carModel: CarModel) =
            CarDetailsFragmentMvvm().apply {
                arguments = Bundle().apply {
                    putParcelable(CAR_OBJ, carModel)
                }
            }
    }
}