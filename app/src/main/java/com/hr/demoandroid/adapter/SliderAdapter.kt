package com.hr.demoandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hr.demoandroid.R
import com.hr.demoandroid.adapter.SliderAdapter.SliderAdapterViewHolder
import com.hr.demoandroid.models.MainBannerItem
import com.smarteist.autoimageslider.SliderViewAdapter

public class SliderAdapter(context: Context?, sliderDataArrayList: ArrayList<MainBannerItem>) :
    SliderViewAdapter<SliderAdapterViewHolder>() {
    //list for storing urls of images.
    private val mSliderItems: List<MainBannerItem>

    //We are inflating the slider_layout inside on Create View Holder method.
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.slider_list_row, null)
        return SliderAdapterViewHolder(inflate)
    }

    //Inside on bind view holder we will set data to item of Slider View.
    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {
        val (imageUrl) = mSliderItems[position]

        //Glide is use to load image from url in your imageview.
        Glide.with(viewHolder.itemView)
            .load(imageUrl)
            .fitCenter()
            .into(viewHolder.imageViewBackground)
    }

    //this method will return the count of our list.
    override fun getCount(): Int {
        return mSliderItems.size
    }

    class SliderAdapterViewHolder(itemView: View) : ViewHolder(itemView) {
        //Adapter class for initializing the views of our slider view.

        var imageViewBackground: ImageView = itemView.findViewById(R.id.imageThumbslider)
    }

    //Constructor
    init {
        mSliderItems = sliderDataArrayList
    }
}