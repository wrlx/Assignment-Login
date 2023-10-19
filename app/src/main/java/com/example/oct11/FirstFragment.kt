package com.example.oct11

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {

    private lateinit var etNameDisplay: TextView
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_first, container, false)


        val etMessageDisplay = view.findViewById<TextView>(R.id.fragmentOneMsg)
        val quoteApi = RetrofitHelper.getInstance().create(ApiFetchable::class.java)
        GlobalScope.launch {
            try {
                val response = quoteApi.getData()

                if (response.isSuccessful) {
                    val data = response.body() // Assuming the API response is a string
                    withContext(Dispatchers.Main) {
                        // Update the TextView with the API data on the main thread
                        etMessageDisplay.text = data!![0].toString()
                    }
                } else {
                    // Handle API error
                    withContext(Dispatchers.Main) {
                        etMessageDisplay.text = "API Error: ${response.message()}"
                    }
                }
            } catch (e: Exception) {
                // Handle exceptions here (e.g., network errors)
                withContext(Dispatchers.Main) {
                    etMessageDisplay.text = "Error: ${e.message}"
                }
            }
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etNameDisplay = view.findViewById(R.id.fragmentOneUserName)
        val sh = requireContext().getSharedPreferences("LoginData", MODE_PRIVATE)
        val fname = sh.getString("fName", "")
        val lname = sh.getString("lName", "")
        val name = fname+ " " +lname
        etNameDisplay.setText(name.toString())
//        age.setText(a.toString())

    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}