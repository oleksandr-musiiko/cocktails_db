package com.omusiiko.coctaildb.applicationscreens.fragment.checkforfilters

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.omusiiko.coctaildb.R
import com.omusiiko.coctaildb.core.base.BaseFragment
import com.omusiiko.coctaildb.applicationscreens.fragment.checkforfilters.viewmodel.CheckForFiltersViewModel

class CheckForFiltersFragment : BaseFragment(R.layout.fragment_check_for_filters) {

    private val viewModel by viewModels<CheckForFiltersViewModel>(factoryProducer = { this.viewModelFactory })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObservers()
        viewModel.checkForUpdateFilters()
    }

    private fun addObservers() {
        viewModel.navigateToDrinksFragment.observe(
            viewLifecycleOwner,
            Observer<Boolean> { navigateToDrinksFragment ->
                if (navigateToDrinksFragment) {
                    navigateToDrinks()
                }
            })

        viewModel.message.observe(
            viewLifecycleOwner,
            Observer<String> { message ->
                if (!message.isNullOrEmpty()) {
                    Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
                }
            })

        viewModel.error.observe(
            viewLifecycleOwner,
            Observer<Throwable> { error ->
                showErrorInDialogAlert(error)
            })
    }

    private fun navigateToDrinks() {
        findNavController().navigate(CheckForFiltersFragmentDirections.actionFragmentCheckForFiltersToFragmentDrinks())
    }

    private fun showErrorInDialogAlert(t: Throwable) {
        AlertDialog.Builder(requireActivity())
            .setTitle(getString(R.string.android_alert))
            .setMessage(
                t.toString()
            )
            .setPositiveButton(android.R.string.yes) { _, _ ->

            }
            .show()
    }
}