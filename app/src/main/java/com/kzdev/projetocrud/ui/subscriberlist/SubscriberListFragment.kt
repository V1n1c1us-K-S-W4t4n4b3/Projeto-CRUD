package com.kzdev.projetocrud.ui.subscriberlist


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.kzdev.projetocrud.R
import com.kzdev.projetocrud.data.db.entity.SubscriberEntity

class SubscriberListFragment : Fragment(R.layout.fragment_subscriber_list) {

    private lateinit var viewModel: SubscriberListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subListAdapter = SubscriberListAdapter(
            listOf(
                SubscriberEntity(1, "Kazu", "kaz@email.com"),
                SubscriberEntity(2, "Lionel Messi", "messi@email.com")
            )
        )

        val recyclerSub: RecyclerView = view.findViewById(R.id.rv_subs)

        recyclerSub.run {
            setHasFixedSize(true)
            adapter = subListAdapter
        }
        
    }
}