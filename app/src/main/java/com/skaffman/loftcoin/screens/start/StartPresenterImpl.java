package com.skaffman.loftcoin.screens.start;

import com.skaffman.loftcoin.data.api.Api;
import com.skaffman.loftcoin.data.db.Database;
import com.skaffman.loftcoin.data.db.model.CoinEntityMapper;
import com.skaffman.loftcoin.data.prefs.Prefs;

import androidx.annotation.Nullable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class StartPresenterImpl implements StartPresenter {

    private Prefs prefs;
    private Api api;
    private Database database;
    private CoinEntityMapper coinEntityMapper;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Nullable
    private StartView view;

    public StartPresenterImpl(Prefs prefs, Api api, Database database, CoinEntityMapper coinEntityMapper) {
        this.prefs = prefs;
        this.api = api;
        this.database = database;
        this.coinEntityMapper = coinEntityMapper;
    }

    @Override
    public void attachView(StartView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        disposables.dispose();
        this.view = null;
    }

    @Override
    public void loadRates() {

        Disposable disposable = api.rates(Api.CONVERT)
                .subscribeOn(Schedulers.io())
                .map(rateResponse -> rateResponse.data)
                .map(coins -> coinEntityMapper.map(coins))
                .doOnNext(coinEntities -> database.saveCoins(coinEntities))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(coinEntities -> {
                            if (view != null) {
                                view.navigateToMainScreen();
                            }
                        }, t -> Timber.e(t)
                );

        disposables.add(disposable);

    }
}
