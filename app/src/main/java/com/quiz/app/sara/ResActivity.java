package com.quiz.app.sara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import me.itangqi.waveloadingview.WaveLoadingView;

public class ResActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        int  score = getIntent().getIntExtra("score",0);
        int  total = getIntent().getIntExtra("total",0);
        String s = "You Answered "+score+" out of "+total;
        Log.e("TPA",s);
        float per = (float) score/total * 100;
        Log.e("TPA",""+per);
        int p = (int) per;

        mWaveLoadingView.setCenterTitle(s);
        mWaveLoadingView.setProgressValue(p);



    }
}
