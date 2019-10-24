package com.onilrose.samplecoroutine.ui.sortlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onilrose.samplecoroutine.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

/**
 * Created by Nizar Assegaf on 23,October,2019
 */

class SortListAdapter() : ListAdapter<String, SortListAdapter.ViewHolder>(
    DiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_sort_list,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(holder, getItem(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvItem: TextView by lazy { view.findViewById<TextView>(R.id.tv_item) }
        fun bind(holder: ViewHolder, item: String) {
            holder.tvItem.text = item
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }
    }

}