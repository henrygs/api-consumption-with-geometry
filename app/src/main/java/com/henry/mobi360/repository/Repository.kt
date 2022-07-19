package com.henry.mobi360.repository

import com.henry.mobi360.local.remote.MobiApi

class Repository constructor(private val retrofitService: MobiApi) {

    fun getOwners() = retrofitService.getOwners()
    fun getLocationUserId(userId: Int) = retrofitService.getLocationUserId(userId.toString())
}