package com.example.oct11.recyclerOnline.dataclass

data class CarDetails(
    val Count: Int,
    val Message: String,
    val Results: List<Result>,
    val SearchCriteria: Any
)