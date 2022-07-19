package com.henry.mobi360.local.model.map

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Location(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("vehicleid")
    val vehicleid: Int,

    //val latLong: LatLng = LatLng(lat, lon)
) : Parcelable {
    fun latLong(): LatLng = LatLng(lat, lon)


}