package com.henry.mobi360.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.henry.mobi360.R
import com.henry.mobi360.databinding.FragmentHomeBinding
import com.henry.mobi360.local.remote.RetrofiteInstance
import com.henry.mobi360.ui.adapter.OwnerAdapter
import com.henry.mobi360.ui.adapter.OwnerAdapterListener
import com.henry.mobi360.utils.Constants.Companion.USER_ID_KEY

class HomeFragment : Fragment(), OwnerAdapterListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private val retrofitService = RetrofiteInstance.api

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

//        viewModel = ViewModelProvider(this, HomeViewModelFactory(Repository(retrofitService))).get(
//            HomeViewModel::class.java
//        )

        initViewModel()
    }

    fun initViewModel() {
        viewModel.ownersLiveData.observe(viewLifecycleOwner, Observer { listOwner ->
            binding.rvOwner.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = OwnerAdapter(this@HomeFragment, listOwner as ArrayList)

            }
        })
        viewModel.locationLiveData.observe(viewLifecycleOwner, Observer { listLocation ->

            val action =
                HomeFragmentDirections.actionHomeFragmentToMapsFragment(listLocation)
            findNavController().navigate(action)
        })
    }

    override fun onClick(userid: Int) {
        findNavController().navigate(R.id.action_homeFragment_to_mapsFragment, Bundle().apply {
            putInt(USER_ID_KEY, userid)
        })
    }
}

