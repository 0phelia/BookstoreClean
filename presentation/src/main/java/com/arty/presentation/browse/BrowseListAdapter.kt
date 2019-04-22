package com.arty.presentation.browse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arty.presentation.R
import com.arty.presentation.model.BookView

class BrowseListAdapter(private var listener: View.OnClickListener) : RecyclerView.Adapter<BrowseListAdapter.DataViewHolder>() {

    var data : List<BookView> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemBinding: com.arty.presentation.databinding.ItemBrowseBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_browse,
                parent,
                false)
        return DataViewHolder(itemBinding, listener)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun updateBooks(books: List<BookView>) {
        data = books
        notifyDataSetChanged()
    }

    fun getItem(itemPosition: Int): BookView {
        return data[itemPosition]
    }

    class DataViewHolder(private val binding: com.arty.presentation.databinding.ItemBrowseBinding,
                         listener: View.OnClickListener)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener(listener)
        }

        fun bind(item: BookView) {
            binding.apply {
                ivBookCover.transitionName= item.id
                simpleTransitionName = item.id
                book = item
                executePendingBindings()
            }
        }

    }

}