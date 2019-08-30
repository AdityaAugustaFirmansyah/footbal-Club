package com.example.aditya.matchscheduller.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aditya.matchscheduller.R
import kotlinx.android.synthetic.main.fragment_description.view.*
import androidx.core.os.bundleOf


class Description : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rootView =  inflater.inflate(R.layout.fragment_description, container, false)
        val args = arguments?.getString(desc)
        rootView.description.text = args
        return rootView
    }

    companion object {
        const val desc = "desc"

        fun newInstance(args:String):Description{
            val fragment = Description()
            val bundle = bundleOf(desc to args)
            fragment.arguments = bundle
            return fragment
        }
    }

}
