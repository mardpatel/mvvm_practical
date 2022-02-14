package com.hr.demoandroid.models

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class ProductResponse(
	val Message: String? = null,
	val Data: Data? = null,
	val ErrorCode: String? = null
)

data class Pagination(
	val totalPage: Int? = null,
	val page: Int? = null,
	val totalCount: Int? = null,
	val rowsPerPage: Int? = null
)

data class MarketListItem(
	val imgUrl: String? = null,
	val ratingEmoji: String? = null,
	val productId: Int? = null,
	val name: String? = null,
	val rank: Int? = 0,
	val localCrossedPrice: Int? = null,
	val brand: String? = null,
	val localPrice: Int? = null
)

data class Data(
	val Pagination: Pagination? = null,
	val marketList: List<MarketListItem> = arrayListOf()
)
object MarketListItemDiff : DiffUtil.ItemCallback<MarketListItem>() {
	override fun areItemsTheSame(oldItem: MarketListItem, newItem: MarketListItem) =
		oldItem.productId == newItem.productId

	override fun areContentsTheSame(oldItem: MarketListItem, newItem: MarketListItem) = oldItem == newItem
}
