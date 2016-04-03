package com.example.yeozuoheng.sunshine;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Yeo Yek Chong on 3/31/2016.
 */
public class LocationEditTextPreference extends EditTextPreference {
    static final private int DEFAULT_MINIMUN_LOCATION_LENGTH =2;
    private int mMinLength;

    public LocationEditTextPreference(Context context, AttributeSet attrs){
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LocationEditTextPreference,
                0, 0);
        try {
            mMinLength = a.getInteger(R.styleable.LocationEditTextPreference_minLength, DEFAULT_MINIMUN_LOCATION_LENGTH);
        } finally {
            a.recycle();
        }
    }
    @Override
    protected void showDialog (Bundle state){
        super.showDialog(state);
        EditText et = getEditText();
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            Dialog d = getDialog();
                if (d instanceof AlertDialog){
                    AlertDialog dialog = (AlertDialog) d;
                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    // Check if the EditText is empty
                    if (s.length() < mMinLength){
                        //Disable ok Button
                        positiveButton.setEnabled(false);
                    }else {
                        //Re-enable the button
                        positiveButton.setEnabled(true);
                    }

                }
            }
        });
    }

    }

