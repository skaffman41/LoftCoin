package com.skaffman.loftcoin.screens.start;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.skaffman.loftcoin.App;
import com.skaffman.loftcoin.R;
import com.skaffman.loftcoin.data.api.Api;
import com.skaffman.loftcoin.data.db.Database;
import com.skaffman.loftcoin.data.db.model.CoinEntityMapper;
import com.skaffman.loftcoin.data.db.model.CoinEntityMapperImpl;
import com.skaffman.loftcoin.data.prefs.Prefs;
import com.skaffman.loftcoin.screens.main.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity implements StartView {

    public static void start(Context context) {
        Intent starter = new Intent(context, StartActivity.class);
        context.startActivity(starter);
    }

    public static void startInNewTask(Context context) {
        Intent starter = new Intent(context, StartActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    private StartPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        Prefs prefs = ((App) getApplication()).getPrefs();
        Api api = ((App) getApplication()).getApi();
        Database database = ((App) getApplication()).getDatabase();
        CoinEntityMapper coinEntityMapper = new CoinEntityMapperImpl();

        presenter = new StartPresenterImpl(prefs, api, database, coinEntityMapper);

        presenter.attachView(this);

        presenter.loadRates();
    }

    @Override
    protected void onStart() {
        super.onStart();

        startAnimation();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @BindView(R.id.start_top_corner)
    ImageView topCorner;

    @BindView(R.id.start_bottom_corner)
    ImageView bottomCorner;

    private void startAnimation() {
        ObjectAnimator innerAnimator = ObjectAnimator.ofFloat(topCorner, "rotation", 0, 360);
        innerAnimator.setDuration(30000);
        innerAnimator.setRepeatMode(ValueAnimator.RESTART);
        innerAnimator.setRepeatCount(ValueAnimator.INFINITE);
        innerAnimator.setInterpolator(new LinearInterpolator());

        ObjectAnimator outerAnimator = ObjectAnimator.ofFloat(bottomCorner, "rotation", 0, -360);
        outerAnimator.setDuration(60000);
        outerAnimator.setRepeatMode(ValueAnimator.RESTART);
        outerAnimator.setRepeatCount(ValueAnimator.INFINITE);
        outerAnimator.setInterpolator(new LinearInterpolator());

        AnimatorSet set = new AnimatorSet();
        set.play(innerAnimator).with(outerAnimator);
        set.start();
    }

    @Override
    public void navigateToMainScreen() {
        MainActivity.start(this);
        finish();
    }
}
