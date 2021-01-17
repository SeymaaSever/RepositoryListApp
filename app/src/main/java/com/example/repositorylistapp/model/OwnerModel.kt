package com.example.repositorylistapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OwnerModel(
    @SerializedName("login")val name: String?,
    @SerializedName("id")val productId: String?,
    @SerializedName("avatar_url")val url: String?
): Serializable