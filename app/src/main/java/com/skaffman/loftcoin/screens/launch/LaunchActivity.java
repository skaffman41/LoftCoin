package com.skaffman.loftcoin.screens.launch;

import android.os.Bundle;

import com.skaffman.loftcoin.App;
import com.skaffman.loftcoin.data.prefs.Prefs;
import com.skaffman.loftcoin.screens.start.StartActivity;
import com.skaffman.loftcoin.screens.welcome.WelcomeActivity;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Prefs prefs = ((App) getApplication()).getPrefs();

        if (prefs.isFirstLaunch()) {
            WelcomeActivity.start(this);
        } else {
            StartActivity.start(this);
        }

        finish();
    }
}
