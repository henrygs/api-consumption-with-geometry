package com.henry.mobi360.ui.fragment.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henry.mobi360.local.model.map.Location
import com.henry.mobi360.local.model.map.Map
import com.henry.mobi360.local.model.owner.Data
import com.henry.mobi360.local.model.owner.Mobi
import com.henry.mobi360.local.remote.RetrofiteInstance
import com.henry.mobi360.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeViewModel : ViewModel() {


    val ownersLiveData = MutableLiveData<List<Data>>()
    val locationLiveData = MutableLiveData<Array<Location>>()
    private val repository = Repository(RetrofiteInstance.api)

    init {
        getOwners()
    }

    private fun getOwners() = viewModelScope.launch(Dispatchers.Main) {
        repository.getOwners().enqueue(
            object : Callback<Mobi> {
                override fun onResponse(call: Call<Mobi>, response: Response<Mobi>) {
                    val owners = response.body()?.data
                    val newOwners = arrayListOf<Data>()
                    owners?.forEach {
                        if (it.owner?.name != null){
                            newOwners.add(it)
                        }
                    }
                    ownersLiveData.value = newOwners
                }

                override fun onFailure(call: Call<Mobi>, t: Throwable) {
                    Log.e("Erro", "onFalure ${t.message}")
                }
            }
        )
    }

    fun getLocationUserId(userid: Int) = viewModelScope.launch(Dispatchers.Main) {
        repository.getLocationUserId(userid).enqueue(object : Callback<Map> {
            override fun onResponse(call: Call<Map>, response: Response<Map>) {
                val vehicle: Map? = response.body()
                locationLiveData.postValue(vehicle?.data)
            }

            override fun onFailure(call: Call<Map>, t: Throwable) {
                Log.e("Erro", "onFalure ${t.message}")
            }
        })
    }
}