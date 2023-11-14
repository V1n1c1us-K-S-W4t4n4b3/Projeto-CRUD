package com.kzdev.projetocrud.ui.subscriberlist


import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kzdev.projetocrud.R
import com.kzdev.projetocrud.data.db.AppDataBase
import com.kzdev.projetocrud.data.db.dao.SubscriberDAO
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelsEventes()

    }

    private fun observeViewModelsEventes() {

        viewModel.allSubscribersEvents.observe(viewLifecycleOwner) { allSubscribers ->

            val subsListAdapter = SubscriberListAdapter(allSubscribers)

            val recyclerSub: RecyclerView? = view?.findViewById(R.id.rv_subs)

            recyclerSub?.let {
                with(it) {
                    setHasFixedSize(true)
                    adapter = subsListAdapter
                }
            }

        }
    }

}