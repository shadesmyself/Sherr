package com.un.sherr.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationServices
import com.un.sherr.base.BaseActivity
import javax.inject.Inject

class MapManagement @Inject constructor() {

    //if gps enabled
    fun isMapsEnabled(activity: Activity): Boolean {
        val manager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps(activity)
            return false
        }
        return true
    }

    //if device is able to use google services
    fun isServicesOK(activity: Activity): Boolean {
        Log.d(BaseActivity.TAG, "isServicesOK: checking google services version")
        val available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(activity)
        if (available == ConnectionResult.SUCCESS) { //everything is fine and the user can make map requests
            Log.d(BaseActivity.TAG, "isServicesOK: Google Play Services is working")
            return true
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) { //an error occured but we can resolve it
            Log.d(BaseActivity.TAG, "isServicesOK: an error occured but we can fix it")
            val dialog: Dialog = GoogleApiAvailability.getInstance().getErrorDialog(activity, available, Constants.ERROR_DIALOG_REQUEST)
            dialog.show()
        } else {
            Toast.makeText(activity, "You can't make map requests", Toast.LENGTH_SHORT).show()
        }
        return false
    }

    private fun buildAlertMessageNoGps(activity: Activity) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setMessage("This application requires GPS to work properly, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                val enableGpsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                activity.startActivityForResult(enableGpsIntent, Constants.PERMISSIONS_REQUEST_ENABLE_GPS)
            }
            .setNegativeButton("No") { _, _ -> }
        val alert: AlertDialog = builder.create()
        alert.show()
    }

    fun getLastKnownLocation(activity: Activity) {
        Log.d(BaseActivity.TAG, "getLastKnownLocation: called.")
        if (!Utils.checkMapPermission(activity)) { return }

        LocationServices.getFusedLocationProviderClient(activity)
            .lastLocation.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val location: Location? = task.result
            }
        }
    }
}