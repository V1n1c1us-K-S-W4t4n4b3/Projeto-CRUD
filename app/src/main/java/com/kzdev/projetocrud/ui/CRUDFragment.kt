package com.kzdev.projetocrud.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kzdev.projetocrud.R

class CRUDFragment : Fragment() {


    private lateinit var viewModel: CRUDViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_c_r_u_d, container, false)
    }

}