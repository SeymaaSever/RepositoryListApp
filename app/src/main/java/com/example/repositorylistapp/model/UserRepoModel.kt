package com.example.repositorylistapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class UserRepoModel (
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0,

    @ColumnInfo
    @SerializedName("id")val id: String?,
    @ColumnInfo
    @SerializedName("owner") var owner: OwnerModel?,
    @ColumnInfo
    @SerializedName("name")val name: String?,
    @ColumnInfo
    @SerializedName("has_issues")val hasIssue: Boolean?,
    @ColumnInfo
    @SerializedName("open_issues_count")val issueCount: Int?,
    @ColumnInfo
    @SerializedName("stargazers_count")val startCount: Int?,
    @ColumnInfo
    @SerializedName("description")val description: String?,
    @ColumnInfo
    val isFavourite: Boolean?

    ) : Serializable