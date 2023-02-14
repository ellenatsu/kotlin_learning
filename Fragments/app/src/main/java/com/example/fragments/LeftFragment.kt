package com.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import com.example.fragments.databinding.LeftFragmentBinding

class LeftFragment : Fragment() {
    //view binding
    private var _binding: LeftFragmentBinding? = null
    private val binding get() = _binding!!
    //onCreate
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LeftFragmentBinding.inflate(layoutInflater, container, false)
        //assign button
        binding.button1.onset
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }




}