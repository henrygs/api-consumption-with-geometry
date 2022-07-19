package com.henry.mobi360.local.model.owner

import java.io.Serializable

data class Data(
    val owner: Owner?,
    val userid: Int? = null,
    val vehicles: List<Vehicle>?
): Serializable