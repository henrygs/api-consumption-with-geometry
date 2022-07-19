package com.henry.mobi360.local.remote

import com.henry.mobi360.local.model.map.Map
import com.henry.mobi360.local.model.owner.Data
import com.henry.mobi360.local.model.owner.Mobi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MobiApi {

    @GET("?op=list")
    fun getOwners() : Call<Mobi>

    @GET("?op=getlocations")
    fun getLocationUserId(@Query("userid") userid: String
    ): Call<Map>
}