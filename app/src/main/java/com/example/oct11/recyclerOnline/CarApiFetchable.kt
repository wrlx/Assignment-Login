package com.example.oct11.recyclerOnline

import com.example.oct11.recyclerOnline.dataclass.CarDetails
import retrofit2.Response
import retrofit2.http.GET

interface CarApiFetchable {
    @GET("/api/vehicles/getallmanufacturers?format=json")
    suspend fun getCarData(): Response<CarDetails>
}