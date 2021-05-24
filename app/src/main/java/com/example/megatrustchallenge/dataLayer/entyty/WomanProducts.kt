package com.example.shopy.datalayer.entity

import com.google.gson.annotations.SerializedName


data class WomanProducts (

    @SerializedName("products") var products : List<Products>

)