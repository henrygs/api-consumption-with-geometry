package com.henry.mobi360.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.henry.mobi360.databinding.ItemOwnerCarBinding
import com.henry.mobi360.local.model.owner.Data
import com.henry.mobi360.utils.customviews.CarCardView

class OwnerAdapter(
    private val listener: OwnerAdapterListener,
    private val owners: ArrayList<Data>
) : RecyclerView.Adapter<OwnerAdapter.OwnerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwnerViewHolder {
        val itemList =
            ItemOwnerCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OwnerViewHolder(itemList, parent.context)
    }

    override fun getItemCount(): Int = owners.size

    override fun onBindViewHolder(holder: OwnerViewHolder, position: Int) {
        when (holder) {
            is OwnerViewHolder -> {
                holder.bind(owners[position])
            }
        }
    }

    inner class OwnerViewHolder(val binding: ItemOwnerCarBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Data) {
            with(binding){
                cardView.setOnClickListener {
                    listener.onClick(data.userid!!)
                }

                txOwner.text = "${data.owner?.name} ${data.owner?.surname}"


                Glide.with(binding.root).load(data.owner?.foto).into(imageOwner)
                data.vehicles?.forEach {
                    val renderItens = CarCardView(context)

                    renderItens.renderItens(it)
                    LinearCar.addView(renderItens)
                }
            }
        }
    }
}




