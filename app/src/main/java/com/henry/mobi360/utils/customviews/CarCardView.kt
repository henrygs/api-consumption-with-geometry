package com.henry.mobi360.utils.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.henry.mobi360.R
import com.henry.mobi360.databinding.ItemCarBinding
import com.henry.mobi360.local.model.owner.Vehicle

class CarCardView(context: Context, attr: AttributeSet?) : MaterialCardView(context, attr) {

    constructor(context: Context) : this(context, null)

    private val binding: ItemCarBinding = ItemCarBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun renderItens(vahicle: Vehicle){

        with(binding){
            with(vahicle){
                val imgCar = imageCar
                val tvName = txNameCar
                val tvYear = txYearCar
                val tvModelCar = txModelCar

                tvName.text = model
                tvYear.text = year
                tvModelCar.text = make

                Glide.with(this@CarCardView).load(foto).into(imgCar)
            }
        }

    }
}

