package com.hr.demoandroid.models

import com.google.gson.annotations.SerializedName

data class BannerResponse(

	@field:SerializedName("Message")
	val message: String? = null,

	@field:SerializedName("Data")
	val data: Datas? = null,

	@field:SerializedName("ErrorCode")
	val errorCode: String? = null
)

data class Recommended(

	@field:SerializedName("productTagId")
	val productTagId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class MainBannerItem(

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class PromotionalBanner2Item(

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class PromotionalBannerItem(

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Datas(

	@field:SerializedName("mainBanner")
	val mainBanner: List<MainBannerItem?>? = null,

	@field:SerializedName("promotionalBanner2")
	val promotionalBanner2: List<PromotionalBanner2Item?>? = null,

	@field:SerializedName("promotionalBanner")
	val promotionalBanner: List<PromotionalBannerItem?>? = null,

	@field:SerializedName("brandZoneBanner")
	val brandZoneBanner: List<BrandZoneBannerItem?>? = null,

	@field:SerializedName("recommended")
	val recommended: Recommended? = null
)

data class BrandZoneBannerItem(

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
