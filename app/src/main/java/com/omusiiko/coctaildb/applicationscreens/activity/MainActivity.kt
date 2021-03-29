package com.omusiiko.coctaildb.applicationscreens.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.omusiiko.coctaildb.R
import com.omusiiko.coctaildb.core.base.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermissions()

        initNavController()
    }

    private fun initNavController() {
        navController =
            Navigation.findNavController(this, R.id.host_fragment)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    this,
                    R.string.please_give_me_permission,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.android_alert))
                .setMessage(
                    R.string.application_needs_permissions
                )
                .setPositiveButton(android.R.string.yes) { _, _ ->
                    Toast.makeText(this, R.string.please_give_me_permission, Toast.LENGTH_SHORT)
                        .show()
                }
                .show()
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.INTERNET), PERMISSIONS_REQUEST
            )
        }
    }

    companion object {
        const val PERMISSIONS_REQUEST = 123
    }
}