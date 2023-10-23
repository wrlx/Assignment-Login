package com.example.oct11.carmvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class ManufactureViewModel : ViewModel(){

    var carDetails : MutableLiveData<CarList> = MutableLiveData()

    fun getCarDetails(){

        viewModelScope.launch{
            val carApi = RetrofitHelperCarMvvm.getInstance().create(ApiMunufacture::class.java)
            try{
                val result = carApi.getCarData()
                carDetails.value = result
            }catch(e: Exception){
                Log.d("whon", e.toString())
            }


        }
    }
}