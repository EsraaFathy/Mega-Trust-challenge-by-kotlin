package com.example.shopy.datalayer.entity

import com.google.gson.annotations.SerializedName


data class Products (

        @SerializedName("id") var id : Int,
        @SerializedName("title") var title : String,
        @SerializedName("body_html") var bodyHtml : String,
        @SerializedName("vendor") var vendor : String,
        @SerializedName("product_type") var productType : String,
        @SerializedName("created_at") var createdAt : String,
        @SerializedName("handle") var handle : String,
        @SerializedName("updated_at") var updatedAt : String,
        @SerializedName("published_at") var publishedAt : String,
        @SerializedName("template_suffix") var templateSuffix : String,
        @SerializedName("published_scope") var publishedScope : String,
        @SerializedName("tags") var tags : String,
        @SerializedName("admin_graphql_api_id") var adminGraphqlApiId : String,
        @SerializedName("options") var options : List<Options>,
        @SerializedName("images") var images : List<Images>,
        @SerializedName("image") var image : Image

)