package com.tkachuk.dday.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.util.Log;

import com.tkachuk.dday.R;
import com.tkachuk.dday.ui.notification.MyAlarm;
import com.tkachuk.dday.util.MinutesAfterMidnightConverter;
import com.tkachuk.dday.util.TimePreference;
import com.tkachuk.dday.util.TimePreferenceDialogFragmentCompat;

import java.util.Calendar;


public class SettingsFragment extends PreferenceFragmentCompat{

    private SharedPreferences prefs;
    private Preference timePickerPreference;
    private SwitchPreferenceCompat switchPreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        timePickerPreference = findPreference(SettingsActivity.KEY_TIME_PICKER_PREF_NOTIFICATION);
        switchPreference = (SwitchPreferenceCompat) findPreference(SettingsActivity.KEY_SWITCH_PREF_NOTIFICATION);

        setTimeSummary();

        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(newValue.equals(true)){
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    int minutesAfterMidnight = prefs.getInt(SettingsActivity.KEY_TIME_PICKER_PREF_NOTIFICATION, 0);
                    Calendar calendar = MinutesAfterMidnightConverter.toCalendar(minutesAfterMidnight, getActivity());

                    MyAlarm.onAlarm(getActivity(), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
                    Log.d("draxvel", "on alarm "+minutesAfterMidnight);

                    timePickerPreference.setEnabled(true);
                }else {
                    timePickerPreference.setEnabled(false);
                    MyAlarm.offAlarm(getActivity());
                }
                return true;
            }
        });

        timePickerPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                int minutesAfterMidnight = prefs.getInt(SettingsActivity.KEY_TIME_PICKER_PREF_NOTIFICATION, 0);
                Calendar calendar = MinutesAfterMidnightConverter.toCalendar(minutesAfterMidnight, getActivity());
                MyAlarm.onAlarm(getActivity(), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
                Log.d("draxvel", "on alarm "+minutesAfterMidnight);
                return true;
            }
        });
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        // Try if the preference is one of our custom Preferences
        DialogFragment dialogFragment = null;
        if (preference instanceof TimePreference) {
            // Create a new instance of TimePreferenceDialogFragment with the key of the related
            // Preference
            dialogFragment = TimePreferenceDialogFragmentCompat
                    .newInstance(preference.getKey());
        }

        // If it was one of our custom Preferences, show its dialog
        if (dialogFragment != null) {
            dialogFragment.setTargetFragment(this, 0);
            if (this.getFragmentManager() != null) {
                dialogFragment.show(this.getFragmentManager(),
                        "android.support.v7.preference" +
                                ".PreferenceFragment.DIALOG");
            }
        }
        // Could not be handled here. Try with the super method.
        else {
            super.onDisplayPreferenceDialog(preference);
        }
    }

    private void setTimeSummary(){
        int minutesAfterMidnight = prefs.getInt(SettingsActivity.KEY_TIME_PICKER_PREF_NOTIFICATION, 0);
        Calendar calendar = MinutesAfterMidnightConverter.toCalendar(minutesAfterMidnight, getActivity());
        timePickerPreference.setSummary(calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE));
    }
}
