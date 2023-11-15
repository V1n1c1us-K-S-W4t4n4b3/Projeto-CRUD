package com.kzdev.projetocrud.ui.subscriberlist


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kzdev.projetocrud.R
import com.kzdev.projetocrud.data.db.AppDataBase
import com.kzdev.projetocrud.data.db.dao.SubscriberDAO
import com.kzdev.projetocrud.databinding.FragmentSubscriberListBinding
import com.kzdev.projetocrud.extension.navigateWithAnimations
import com.kzdev.projetocrud.repository.DatabaseDataSource
import com.kzdev.projetocrud.repository.SubscriberRepository

class SubscriberListFragment : Fragment(R.layout.fragment_subscriber_list) {

    private val viewModel: SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                val subscriberDAO: SubscriberDAO =
                    AppDataBase.getInstance(requireContext()).subscriberDAO

                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return SubscriberListViewModel(repository) as T
            }
        }
    }

    private lateinit var binding: FragmentSubscriberListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSubscriberListBinding.bind(view)


        observeViewModelsEventes()

        configViewListener()


    }

    private fun observeViewModelsEventes() {

        viewModel.allSubscribersEvents.observe(viewLifecycleOwner) { allSubscribers ->
            val subsListAdapter = SubscriberListAdapter(allSubscribers)

            binding.rvSubs.let {
                with(it) {
                    setHasFixedSize(true)
                    adapter = subsListAdapter
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSubscribers()
    }

    private fun configViewListener() {
        binding.fabAddButton.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.CRUDFragment)
        }
    }
}