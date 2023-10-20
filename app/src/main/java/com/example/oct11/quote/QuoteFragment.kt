package com.example.oct11.quote

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.oct11.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuoteFragment : Fragment() {

    private lateinit var etNameDisplay: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_quotes, container, false)


        val etMessageDisplay = view.findViewById<TextView>(R.id.fragmentOneMsg)
        val quoteApi = RetrofitHelper.getInstance().create(ApiQuoteFetchable::class.java)
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
        val sh = requireContext().getSharedPreferences("LoginData", Context.MODE_PRIVATE)
        val fname = sh.getString("fName", "")
        val lname = sh.getString("lName", "")
        val name = fname+ " " +lname
        etNameDisplay.setText(name.toString())
//        age.setText(a.toString())

    }




}