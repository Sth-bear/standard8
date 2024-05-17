package com.example.standard8.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.standard8.R
import com.example.standard8.data.model.local.ImageDocument
import com.example.standard8.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val viewModel by activityViewModels<HomeViewModel>{HomeViewmodelFactory()}
    private val homeAdapter by lazy { HomeAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defaultBind()
        observeData()
        searchOnClick()
        switchOnClick()
    }

    private fun defaultBind() {
        with(binding.rvSearch) {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeData(){
        viewModel.images.observe(viewLifecycleOwner, Observer { data ->
            homeAdapter.listUpdate(data)
        })
        viewModel.saved.observe(viewLifecycleOwner, Observer { data ->
            homeAdapter.saveListupdate(data)
        })
    }

    private fun searchOnClick() {
        binding.btnSearchFrag.setOnClickListener {
            val query = binding.etSearchFrag.text.toString()
            viewModel.searchData(query)
        }
    }

    private fun switchOnClick() {
        homeAdapter.itemClick = object : HomeAdapter.ItemClick {
            override fun onClick(view: View,item: ImageDocument) {
                if (viewModel.saved.value?.contains(item)?: false) {
                    viewModel.delData(item)
                    view.findViewById<ImageView>(R.id.iv_mark).setImageResource(R.drawable.icon_empty)
                } else {
                    viewModel.saveData(item)
                    view.findViewById<ImageView>(R.id.iv_mark).setImageResource(R.drawable.icon_full)
                }
            }
        }
    }
}