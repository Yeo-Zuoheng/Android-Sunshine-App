package com.example.yeozuoheng.sunshine;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;

public class LocationDialog extends DialogFragment{

    public static String userLocation;

    public interface LocationDialogListener {
        public void onDialogNeutralClick(DialogFragment dialog);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder locationDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        locationDialog.setView(inflater.inflate(R.layout.country_dialog, null))
                .setNeutralButton(R.string.dialog_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int id) {
                        Dialog dialog = (Dialog) dialogInterface;
                        EditText editText = (EditText)dialog.findViewById(R.id.dialog_location_editfield);
                        userLocation = editText.getText().toString();
                        Log.e(LocationDialog.class.getSimpleName(),userLocation);

                        mListener.onDialogNeutralClick(LocationDialog.this);
                    }
                });
        return locationDialog.create();

    }


    // Use this instance of the interface to deliver action events
    LocationDialogListener mListener;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (LocationDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
