package com.henry.mobi360.local.model.map

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Map(
    @SerializedName("data")
    val data: Array<Location>
): Parcelable