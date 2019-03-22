package com.skaffman.loftcoin.data.prefs;

public interface Prefs {
    boolean isFirstLaunch();

    void setFirstLaunch(boolean firstLaunch);
}
