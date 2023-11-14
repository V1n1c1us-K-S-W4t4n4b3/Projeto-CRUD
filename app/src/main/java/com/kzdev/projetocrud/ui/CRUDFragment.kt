package com.kzdev.projetocrud.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.kzdev.projetocrud.R
import com.kzdev.projetocrud.data.db.AppDataBase
import com.kzdev.projetocrud.data.db.dao.SubscriberDAO
import com.kzdev.projetocrud.databinding.FragmentCRUDBinding
import com.kzdev.projetocrud.extension.hideKeyboard
import com.kzdev.projetocrud.repository.DatabaseDataSource
import com.kzdev.projetocrud.repository.SubscriberRepository

class CRUDFragment : Fragment(R.layout.fragment_c_r_u_d) {

    private val viewModel: CRUDViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDAO =
                    AppDataBase.getInstance(requireContext()).subscriberDAO

                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return CRUDViewModel(repository) as T
            }
        }
    }

    private lateinit var binding: FragmentCRUDBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCRUDBinding.bind(view)

        observerEvents()
        setListeners()

    }


    private fun observerEvents() {
        viewModel.subscribersStateEventData.observe(viewLifecycleOwner) { subscriberState ->
            when (subscriberState) {
                is CRUDViewModel.SubscriberState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                }
            }
        }


        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun clearFields() {

        binding.tietName.text?.clear()
        binding.tietEmail.text?.clear()

    }

    private fun setListeners() {
        binding.bAdd.setOnClickListener {
            val name = binding.tietName.text.toString()
            val email = binding.tietEmail.text.toString()

            viewModel.addSubscriber(name, email)

        }
    }
}
