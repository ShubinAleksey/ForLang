package com.example.forlang2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        delaySplashScreen();
    }

    private void delaySplashScreen() {
        Completable.timer(1, TimeUnit.SECONDS,
                        AndroidSchedulers.mainThread())
                .subscribe(() -> startActivity(new Intent(SplashScreenActivity.this, SignInActivity.class)));
    }
}
