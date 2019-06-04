package com.example.p1281_audiofocus;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    AudioManager audioManager;

    AFListener afListenerMusic;
    AFListener afListenerSound;

    MediaPlayer mpMusic;
    MediaPlayer mpSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }

    public void onClickMusic(View view) {

        mpMusic = new MediaPlayer();

        try {
            mpMusic.setDataSource("mnt/sdcard/Music/music.mp3");
            mpMusic.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mpMusic.setOnCompletionListener(this);

        afListenerMusic = new AFListener(mpMusic, "Music");
        int requestResult = audioManager.requestAudioFocus(afListenerMusic,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        Log.i(TAG, "Music request focus, result : " + requestResult);

        mpMusic.start();
    }

    public void onClickSound(View view) {
    }
}
