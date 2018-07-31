package com.tkachuk.dday.ui.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    public static final String
            KEY_SWITCH_PREF_NOTIFICATION = "switch_preference_notification";

    public static final String
            KEY_TIME_PICKER_PREF_NOTIFICATION = "time_picker_preference_notification";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
