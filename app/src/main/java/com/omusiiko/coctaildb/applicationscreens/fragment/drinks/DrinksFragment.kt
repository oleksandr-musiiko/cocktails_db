package com.omusiiko.coctaildb.applicationscreens.fragment.drinks

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omusiiko.coctaildb.R
import com.omusiiko.coctaildb.core.base.BaseFragment
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.adapter.DrinksListAdapterDiffUtils
import com.omusiiko.coctaildb.core.base.recyclerview.AdapterDelegatesManager
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.delegate.DrinkInfoAdapterDelegate
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.delegate.DrinksDividerAdapterDelegate
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.delegate.DrinksHeaderAdapterDelegate
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DividerItem
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.recyclerview.model.DrinkDisplayItem
import com.omusiiko.coctaildb.applicationscreens.fragment.drinks.viewmodel.DrinksViewModel
import kotlinx.android.synthetic.main.fragment_drinks.*
import kotlinx.android.synthetic.main.toolbar_drinks.*

class DrinksFragment : BaseFragment(R.layout.fragment_drinks) {

    private val viewModel by viewModels<DrinksViewModel>(factoryProducer = { this.viewModelFactory })
    private lateinit var drinksListAdapter: DrinksListAdapterDiffUtils

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListAdapters()
        addObservers()
        setViews()
        viewModel.getCategories()
    }

    private fun setViews() {
        setUpRecyclerView()
        changeVisibilityTextViewNoDrinks(false)
        setFilterButtonClickListener()
    }

    private fun navigateToFragmentFilters() {
        findNavController().navigate(DrinksFragmentDirections.actionFragmentDrinksToFragmentFilters())
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(requireActivity())
        recyclerView_Drinks_fragmentDrinks.apply {
            adapter = drinksListAdapter
            this.layoutManager = layoutManager
        }
        drinksListAdapter.setItems(ArrayList())

        var loading = true
        var pastVisiblesItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int

        // pagination
        recyclerView_Drinks_fragmentDrinks.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false

                            Log.d("SCROLL", "Last Item!")

                            viewModel.loadNextCategoryDrinksList()

                            loading = true
                        }
                    }
                }
            }
        })
    }

    private fun addObservers() {
        viewModel.drinksDisplayItemsForAdd
            .observe(
                viewLifecycleOwner,
                Observer<List<DrinkDisplayItem>> { list ->
                    if (list != null) {
                        val items = arrayListOf<DrinkDisplayItem>()
                        if (!items.containsAll(list)) {
                            items.addAll(drinksListAdapter.listOfItems)
                        }
                        if (items.isNotEmpty()) {
                            items.add(
                                DividerItem(
                                    Any()
                                )
                            )
                        }
                        items.addAll(list)
                        Log.d("TO RECYCLER VIEW", "$items")
                        drinksListAdapter.setItems(items.toMutableList())
                        drinksListAdapter.notifyDataSetChanged()
                    } else {
                        drinksListAdapter.setItems(ArrayList())
                        drinksListAdapter.notifyDataSetChanged()
                    }
                }
            )

        viewModel.visibilityTextViewNoDrinks.observe(
            viewLifecycleOwner,
            Observer<Boolean> { visibilityTextViewNoDrinks ->
                changeVisibilityTextViewNoDrinks(visibilityTextViewNoDrinks)
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
        drinksListAdapter =
            DrinksListAdapterDiffUtils(
                AdapterDelegatesManager<DrinkDisplayItem>()
                    .apply {
                    addDelegate(DrinksDividerAdapterDelegate())
                    addDelegate(DrinksHeaderAdapterDelegate())
                    addDelegate(DrinkInfoAdapterDelegate())
                }
            )
    }

    private fun setFilterButtonClickListener() {
        imageView_filterButton_toolbarDrinks.setOnClickListener {
            navigateToFragmentFilters()
        }
    }

    private fun changeVisibilityTextViewNoDrinks(visibility: Boolean) {
        if (visibility) {
            textView_noDrinks_fragmentDrinks.visibility = View.VISIBLE
        } else {
            textView_noDrinks_fragmentDrinks.visibility = View.INVISIBLE
        }
    }
}