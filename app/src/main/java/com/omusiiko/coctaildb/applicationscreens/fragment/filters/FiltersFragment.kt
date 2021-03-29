package com.omusiiko.coctaildb.applicationscreens.fragment.filters

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.omusiiko.coctaildb.R
import com.omusiiko.coctaildb.core.base.BaseFragment
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.adapter.FiltersListAdapterDiffUtils
import com.omusiiko.coctaildb.core.base.recyclerview.AdapterDelegatesManager
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.delegate.FilterInfoAdapterDelegate
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.recyclerview.model.FilterDisplayItem
import com.omusiiko.coctaildb.applicationscreens.fragment.filters.viewmodel.FiltersViewModel
import kotlinx.android.synthetic.main.fragment_filters.*
import kotlinx.android.synthetic.main.toolbar_filters.*

class FiltersFragment : BaseFragment(R.layout.fragment_filters) {

    private val viewModel by viewModels<FiltersViewModel>(factoryProducer = { this.viewModelFactory })
    private lateinit var filtersListAdapter: FiltersListAdapterDiffUtils

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListAdapters()
        addObservers()
        setViews()
        viewModel.getFiltersFromLocalStorage()
    }

    private fun setViews() {
        setUpRecyclerView()
        setApplyButtonClickListener()
        setBackButtonClickListener()
    }

    private fun setApplyButtonClickListener() {
        button_apply_fragmentFilters.setOnClickListener {
            viewModel.updateFiltersSettingsToLocalStorage()
        }
    }

    private fun setUpRecyclerView() {
        recyclerView_Filters_fragmentFilters.apply {
            adapter = filtersListAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
        filtersListAdapter.setItems(ArrayList())
    }

    private fun addObservers() {
        viewModel.filters
            .observe(
                viewLifecycleOwner,
                Observer<List<FilterDisplayItem>> { list ->
                    filtersListAdapter.setItems(list.toMutableList())
                    changeClickableApplyButton(true)
                }
            )

        viewModel.visibilityTextViewNoFilters.observe(
            viewLifecycleOwner,
            Observer<Boolean> { visibilityTextViewNoFilters ->
                changeVisibilityTextViewNoFilters(visibilityTextViewNoFilters)
            }
        )

        viewModel.message.observe(
            viewLifecycleOwner,
            Observer<String> { message ->
                if (!message.isNullOrEmpty()) {
                    Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun setListAdapters() {
        filtersListAdapter =
            FiltersListAdapterDiffUtils(
                AdapterDelegatesManager<FilterDisplayItem>()
                    .apply {
                    addDelegate(
                        FilterInfoAdapterDelegate(
                            flagChangedListener = viewModel
                        )
                    )
                }
            )
    }

    private fun setBackButtonClickListener() {
        imageView_backButton_toolbarFilters.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun changeClickableApplyButton(clickable: Boolean) {
        button_apply_fragmentFilters.isClickable = clickable
    }

    private fun changeVisibilityTextViewNoFilters(visibility: Boolean) {
        if (visibility) {
            textView_noFilters_fragmentFilters.visibility = View.VISIBLE
        } else {
            textView_noFilters_fragmentFilters.visibility = View.INVISIBLE
        }
    }
}