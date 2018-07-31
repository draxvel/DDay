package com.tkachuk.dday.util;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TimePicker;

import com.tkachuk.dday.R;

public class TimePreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {

    private TimePicker mTimePicker;

    public static TimePreferenceDialogFragmentCompat newInstance(
            String key) {
        final TimePreferenceDialogFragmentCompat
                fragment = new TimePreferenceDialogFragmentCompat();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);

        return fragment;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        mTimePicker = view.findViewById(R.id.time_picker);

            // Exception when there is no TimePicker
            if (mTimePicker == null) {
                throw new IllegalStateException("Dialog view must contain" +
                        " a TimePicker with id 'time_picker'");
            }

            // Get the time from the related Preference
            Integer minutesAfterMidnight = null;
            DialogPreference preference = getPreference();
            if (preference instanceof TimePreference) {
                minutesAfterMidnight =
                        ((TimePreference) preference).getTime();
            }

            // Set the time to the TimePicker
            if (minutesAfterMidnight != null) {
                int hours = minutesAfterMidnight / 60;
                int minutes = minutesAfterMidnight % 60;
                boolean is24hour = DateFormat.is24HourFormat(getContext());

                mTimePicker.setIs24HourView(is24hour);
                mTimePicker.setCurrentHour(hours);
                mTimePicker.setCurrentMinute(minutes);
            }
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            // Get the current values from the TimePicker
            int hours;
            int minutes;
            if (Build.VERSION.SDK_INT >= 23) {
                hours = mTimePicker.getHour();
                minutes = mTimePicker.getMinute();
            } else {
                hours = mTimePicker.getCurrentHour();
                minutes = mTimePicker.getCurrentMinute();
            }

            // Generate value to save
            int minutesAfterMidnight = MinutesAfterMidnightConverter.toMinutesAfterMidnight(hours, minutes);

            // Save the value
            DialogPreference preference = getPreference();
            if (preference instanceof TimePreference) {
                TimePreference timePreference = ((TimePreference) preference);
                // This allows the client to ignore the user value.
                if (timePreference.callChangeListener(minutesAfterMidnight)) {
                    // Save the value
                    timePreference.setTime(minutesAfterMidnight);
                    timePreference.setSummary(hours+":"+minutes);
                }
            }
        }
    }
}
