package com.kzdev.projetocrud.ui.subscriber

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.kzdev.projetocrud.R
import com.kzdev.projetocrud.data.db.AppDataBase
import com.kzdev.projetocrud.data.db.dao.SubscriberDAO
import com.kzdev.projetocrud.databinding.FragmentSubscriberBinding
import com.kzdev.projetocrud.extension.hideKeyboard
import com.kzdev.projetocrud.repository.DataBaseDataSource
import com.kzdev.projetocrud.repository.SubscriberRepository

class SubscriberFragment : Fragment(R.layout.fragment_subscriber) {

    private val viewModel: SubscriberViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDAO =
                    AppDataBase.getInstance(requireContext()).subscriberDAO

                val repository: SubscriberRepository = DataBaseDataSource(subscriberDAO)
                return SubscriberViewModel(repository) as T
            }
        }
    }

    private val args: SubscriberFragmentArgs by navArgs()

    private lateinit var binding: FragmentSubscriberBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSubscriberBinding.bind(view)

        args.subscriber?.let { subscriber ->
            binding.buttonAdd.text = getString(R.string.subscriber_button_update)
            binding.inputName.setText(subscriber.name)
            binding.inputData.setText(subscriber.birth)
            binding.inputCpf.setText(subscriber.cpf)
            binding.inputCel.setText(subscriber.tel)

            binding.buttonDelete.visibility = View.VISIBLE
        }

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.subscriberStateEvenData.observe(viewLifecycleOwner) { subscriberState ->
            when (subscriberState) {
                is SubscriberViewModel.SubscriberState.Inserted,
                is SubscriberViewModel.SubscriberState.Update,
                is SubscriberViewModel.SubscriberState.Deleted -> {

                    clearFields()
                    hideKeyboard()
                    requireView().requestFocus()
                    findNavController().popBackStack()
                }
            }
        }

        viewModel.messageEvenData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        binding.inputName.text?.clear()
        binding.inputData.text?.clear()
        binding.inputCpf.text?.clear()
        binding.inputCel.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        binding.buttonAdd.setOnClickListener {
            val name = binding.inputName.text.toString()
            val birth = binding.inputData.text.toString()
            val cpf = binding.inputCpf.text.toString()
            val tel = binding.inputCel.text.toString()

            viewModel.addOrUpdateSubscriber(name, birth, cpf, tel, args.subscriber?.id ?: 0)
        }

        binding.buttonDelete.setOnClickListener {
            viewModel.removeSubscriber(args.subscriber?.id ?: 0)
        }
    }
}
