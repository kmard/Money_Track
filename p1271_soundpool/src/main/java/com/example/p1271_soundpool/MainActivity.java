package com.example.p1271_soundpool;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SoundPool.OnLoadCompleteListener {

    private static final String TAG = "myLogs";
    final int MAX_STREAMS = 5;

    SoundPool sp;
    int soundIdShot;
    int soundIdExplosion;

    int streamIDShot;
    int streamIDExplosion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(this);

        soundIdShot = sp.load(this, R.raw.shot, 1);
        Log.i(TAG, "soundShot : " + soundIdShot);

        try {
            soundIdExplosion = sp.load(getAssets().openFd("explosion.mp3"), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "soundIdExplosion: " + soundIdExplosion);
    }

    public void onClick(View view) {
        sp.play(soundIdShot, 1, 1, 0, 0, 1);
        sp.play(soundIdExplosion, 1, 1, 0, 0, 1);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        Log.i(TAG, "onLoadComplete: " + sampleId + " ,status = " + status);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
