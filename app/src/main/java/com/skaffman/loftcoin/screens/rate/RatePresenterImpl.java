package com.skaffman.loftcoin.screens.rate;

import com.skaffman.loftcoin.data.api.Api;
import com.skaffman.loftcoin.data.api.model.RateResponse;
import com.skaffman.loftcoin.data.prefs.Prefs;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class RatePresenterImpl implements RatePresenter {

    Prefs prefs;
    Api api;

    @Nullable
    private RateView view;

    public RatePresenterImpl(Prefs prefs, Api api) {
        this.prefs = prefs;
        this.api = api;
    }

    @Override
    public void attachView(RateView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void getRate() {

        Call<RateResponse> call = api.rates(Api.CONVERT);

        call.enqueue(new Callback<RateResponse>() {
            @Override
            public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {
                RateResponse body = response.body();

                if (view != null && body != null) {
                    view.setCoins(body.data);
                    view.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<RateResponse> call, Throwable t) {
                Timber.e(t);

                if (view != null) {
                    view.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        getRate();
    }
}
