package com.skaffman.loftcoin;

import android.app.Application;

import com.skaffman.loftcoin.data.api.Api;
import com.skaffman.loftcoin.data.api.ApiInitializer;
import com.skaffman.loftcoin.data.prefs.Prefs;
import com.skaffman.loftcoin.data.prefs.PrefsImpl;

import timber.log.Timber;

public class App extends Application {

    private Prefs prefs;
    private Api api;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        prefs = new PrefsImpl(this);
        api = new ApiInitializer().init();
    }

    public Prefs getPrefs() {
        return prefs;
    }

    public Api getApi() {
        return api;
    }
}
