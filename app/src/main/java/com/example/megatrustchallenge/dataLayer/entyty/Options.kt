package com.example.shopy.datalayer.entity

import com.google.gson.annotations.SerializedName


data class Options (

        @SerializedName("id") var id : Int,
        @SerializedName("product_id") var productId : Int,
        @SerializedName("name") var name : String,
        @SerializedName("position") var position : Int

)