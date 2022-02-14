package com.hr.demoandroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.hr.demoandroid.adapter.ProductListPagingAdapter
import com.hr.demoandroid.adapter.SliderAdapter
import com.hr.demoandroid.databinding.FragmentRecyclerListBinding
import com.hr.demoandroid.models.BannerResponse
import com.hr.demoandroid.models.MainBannerItem
import com.hr.demoandroid.viewmodel.MainActivityViewModel
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class RecyclerListFragment : Fragment() {

    val vmObj: MainActivityViewModel get() = ViewModelProvider(this)[MainActivityViewModel::class.java]
    private lateinit var recyclerAdapter: ProductListPagingAdapter
    private var searchJob: Job? = null
    private lateinit var mbinding: FragmentRecyclerListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mbinding = FragmentRecyclerListBinding.inflate(inflater, container, false)
        mbinding.vmObj = vmObj
        mbinding.lifecycleOwner = this
        recyclerAdapter = ProductListPagingAdapter {}
        mbinding.recyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
        mbinding.recyclerView.adapter = recyclerAdapter
        recyclerAdapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && recyclerAdapter.itemCount < 1) {
                Log.e("size1", recyclerAdapter.itemCount.toString())
            } else {
                Log.e("size", recyclerAdapter.itemCount.toString())
            }
        }
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            vmObj.searchProduct("").collectLatest {
                Log.e("dataResponse", it.toString())
                recyclerAdapter.submitData(it)
                recyclerAdapter.notifyDataSetChanged()
            }
        }
        vmObj.getBannerListObserver().observe(viewLifecycleOwner, Observer<BannerResponse> {
            if (it != null) {
                val adapter = SliderAdapter(
                    requireActivity(),
                    it.data?.mainBanner as ArrayList<MainBannerItem>
                )
                mbinding.imageSlider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR)
                mbinding.imageSlider.setSliderAdapter(adapter)
                mbinding.imageSlider.setScrollTimeInSec(3)
                mbinding.imageSlider.setAutoCycle(true)
                //to start autocycle below method is used.
                mbinding.imageSlider.startAutoCycle()
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        vmObj.makeApiCallBanner()
        return mbinding.root

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            RecyclerListFragment()
    }
}