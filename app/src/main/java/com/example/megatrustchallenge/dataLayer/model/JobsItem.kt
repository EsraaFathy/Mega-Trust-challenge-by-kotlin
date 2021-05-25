package com.example.megatrustchallenge.dataLayer.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class JobsItem(
        val company: String?,
        val company_logo: String?,
        val company_url: String?,
        val created_at: String?,
        val description: String?,
        val how_to_apply: String?,
        @PrimaryKey val id: String,
        val location: String?,
        val title: String?,
        val type: String?,
        val url: String?
) : Serializable