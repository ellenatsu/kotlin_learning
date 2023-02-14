package com.example.fragmentpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentpractice.databinding.NewsContentFragBinding


class NewsContentFragment : Fragment() {
    //view binding
    private var _binding: NewsContentFragBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewsContentFragBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    fun refresh(title: String, content: String){
        binding.contentLayout.visibility = View.VISIBLE
        binding.newsTitle.text = title
        binding.newsContent.text = content
    }

}