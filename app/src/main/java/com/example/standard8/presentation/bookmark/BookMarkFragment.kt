package com.example.standard8.presentation.bookmark

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
import com.example.standard8.databinding.FragmentBookMarkBinding
import com.example.standard8.presentation.home.HomeAdapter
import com.example.standard8.presentation.home.HomeViewModel
import com.example.standard8.presentation.home.HomeViewmodelFactory


class BookMarkFragment : Fragment() {
    private val bookAdapter by lazy { HomeAdapter() }
    private val binding by lazy { FragmentBookMarkBinding.inflate(layoutInflater)}
    private val viewModel by activityViewModels<HomeViewModel> {HomeViewmodelFactory()}

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
        switchOnClick()
    }

    private fun defaultBind() {
        with(binding.rvBookmark) {
            adapter = bookAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeData() {
        viewModel.saved.observe(viewLifecycleOwner, Observer { data ->
            bookAdapter.saveListupdate(data)
            bookAdapter.listUpdate(data)
        })
    }

    private fun switchOnClick() {
        bookAdapter.itemClick = object : HomeAdapter.ItemClick {
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