package com.hr.demoandroid.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hr.demoandroid.databinding.RecyclerListRowBinding
import com.hr.demoandroid.models.MarketListItem
import com.hr.demoandroid.models.MarketListItemDiff

class ProductListPagingAdapter (
    private val onClick: (MarketListItem) -> Unit
): PagingDataAdapter<MarketListItem, ProductListPagingAdapter.OnlineReservationsPagingViewHolder>(
    MarketListItemDiff
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnlineReservationsPagingViewHolder {
        return OnlineReservationsPagingViewHolder(
            RecyclerListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnlineReservationsPagingViewHolder, position: Int) {
        val photo = getItem(position)
        Log.e("item",photo.toString())
        if (photo != null) {
            holder.bind(photo)
        }
    }

    inner class OnlineReservationsPagingViewHolder(
        private val binding: RecyclerListRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
//            binding.setClickListener { view ->
//                binding.restaurant?.let { restaurant ->
//                    onClick(restaurant)
//                }
//            }
        }

        fun bind(item: MarketListItem) {
            if (item.rank!! > 0) {
                binding.rlRating.visibility = View.VISIBLE
            }else{
                binding.rlRating.visibility = View.GONE
            }
            binding.apply {
                this.item = item

                executePendingBindings()
            }
        }
    }
}
