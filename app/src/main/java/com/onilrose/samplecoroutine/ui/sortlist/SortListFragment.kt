package com.onilrose.samplecoroutine.ui.sortlist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onilrose.samplecoroutine.R
import kotlinx.android.synthetic.main.fragment_sort_list.*
import android.view.animation.OvershootInterpolator
import androidx.core.view.ViewCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class SortListFragment : Fragment() {

    companion object {
        fun newInstance() = SortListFragment()
    }

    private lateinit var viewModel: SortListViewModel
    private var mSortListAdapter: SortListAdapter? = null
    private var isAsc = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sort_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleViewOrderHistory()
        initObserve()
        initAction()
    }

    private fun initAction() {
        fab_sort.setOnClickListener {
            if (isAsc) {
                rotateFabBackward()
                viewModel.sortListDesc()
            } else {
                rotateFabForward()
                viewModel.sortList()
            }
        }
    }

    private fun rotateFabForward() {
        isAsc = true
        ViewCompat.animate(fab_sort)
            .rotation(180.0f)
            .withLayer()
            .setDuration(200L)
            .setInterpolator(OvershootInterpolator(10.0f))
            .start()
    }

    private fun rotateFabBackward() {
        isAsc = false
        ViewCompat.animate(fab_sort)
            .rotation(0.0f)
            .withLayer()
            .setDuration(200L)
            .setInterpolator(OvershootInterpolator(10.0f))
            .start()
    }

    private fun initObserve() {
        viewModel = ViewModelProviders.of(this).get(SortListViewModel::class.java)
        viewModel.apply {
            mList.observe(this@SortListFragment, Observer { list ->
                viewModel.hideLoading()
                submit(list)
            })

            showLoading.observe(this@SortListFragment, Observer {
                if (it)
                    progress_list.visibility = View.VISIBLE
                else
                    progress_list.visibility = View.GONE
            })
        }
    }

    private fun submit(list: List<String>) {
        mSortListAdapter?.submitList(list)
    }

    private fun initRecycleViewOrderHistory() {
        val linearLayoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        mSortListAdapter = SortListAdapter()
        rv_sort_list.apply {
            layoutManager = linearLayoutManager
            adapter = mSortListAdapter
        }
    }

}
