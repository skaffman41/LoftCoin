package com.skaffman.loftcoin.screens.rate;

import com.skaffman.loftcoin.data.api.model.Coin;

import java.util.List;

public interface RateView {

    void setCoins(List<Coin> coins);

    void setRefreshing(Boolean refreshing);

    void showCurrencyDialog();
}
