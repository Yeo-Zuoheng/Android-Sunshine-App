package com.example.yeozuoheng.sunshine;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;


public class SplashScreen extends FragmentActivity implements LocationDialog.LocationDialogListener{
    Intent intent;
    SharedPreferences pref  = null;
    @Override
    public void onDialogNeutralClick(DialogFragment dialog) {
        // User touched the dialog's positive button
        //set the location of given by the user to the string handling locations
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        pref = getSharedPreferences("com.example.yeozuoheng.sunshine", MODE_PRIVATE);
        intent = new Intent(SplashScreen.this,MainActivity.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!pref.contains("firstrun") | LocationDialog.userLocation == null) {
                showLocationDialog();
                pref.edit().putBoolean("firstrun", false).commit();
        }else{
            startActivity(intent);
            finish();
        }

    }
    private void showLocationDialog (){
        DialogFragment locationDialog = new LocationDialog();
        locationDialog.show(getSupportFragmentManager(), "LocationDialog");
    }
}
