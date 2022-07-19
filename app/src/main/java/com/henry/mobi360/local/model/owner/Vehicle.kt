package com.henry.mobi360.local.model.owner

import java.io.Serializable

data class Vehicle(
    val color: String? = "",
    val foto: String? = "",
    val make: String? = "",
    val model: String? = "",
    val vehicleid: Int? = null,
    val vin: String? = "",
    val year: String? = ""
): Serializable {
    companion object {
        fun renderCarHenri(): List<Vehicle>{
            val listCar = ArrayList<Vehicle>()

            listCar.add(
                Vehicle(
                    foto = "http://volvoperformanceshop.com/images/header_volvo_rotation5.jpg",
                    make = "Volvo",
                    year = "2005",
                    model = "S60"
                )
            )
            listCar.add(
                Vehicle(
                    foto = "https://s-media-cache-ak0.pinimg.com/736x/a0/68/0d/a0680da9e0730971bf817ae7da78d2d5.jpg",
                    make = "Toyota",
                    year = "2007",
                    model = "S60"
                )
            )

            return listCar
        }
    }
}