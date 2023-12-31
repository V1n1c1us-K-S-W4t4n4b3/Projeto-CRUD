package com.kzdev.projetocrud.ui.subscriberlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kzdev.projetocrud.R
import com.kzdev.projetocrud.data.db.entity.SubscriberEntity

class SubscriberListAdapter(private val subscriber: List<SubscriberEntity>) :
    RecyclerView.Adapter<SubscriberListAdapter.SubscriberListViewHolder>() {

    var onItemClick: ((entity: SubscriberEntity) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberListViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.subscriber_item, parent, false)

        return SubscriberListViewHolder(view)

    }

    override fun onBindViewHolder(holder: SubscriberListViewHolder, position: Int) {
        holder.bindView(subscriber[position])
    }

    override fun getItemCount() = subscriber.size


    inner class SubscriberListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tVSubName: TextView = itemView.findViewById(R.id.text_name)
        private val tVSubEmail: TextView = itemView.findViewById(R.id.text_data)

        fun bindView(subscriber: SubscriberEntity) {
            tVSubName.text = subscriber.name
            tVSubEmail.text = subscriber.birth

            itemView.setOnClickListener {
                onItemClick?.invoke(subscriber)
            }
        }
    }
}