package com.example.p1261_mediaplayer;

import android.content.ContentUris;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private static final String TAG = "myLogs";

    final String DATA_HTTP = "http://dl.dropboxusercontent.com/u/6197740/explosion.mp3";
    final String DATA_STREAM = "http://online.radiorecord.ru:8101/rr_128";
    final String DATA_SD = Environment.
            getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "/music.mp3";
    final Uri DATA_URI = ContentUris.withAppendedId(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, 13359);

    MediaPlayer mediaPlayer;
    AudioManager am;
    CheckBox chbLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        chbLoop = (CheckBox) findViewById(R.id.chbLoop);
        chbLoop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mediaPlayer != null)
                    mediaPlayer.setLooping(isChecked);
            }
        });
    }

    public void onClickStart(View view) {

        releaseMP();

        try {
            switch (view.getId()) {
                case R.id.btnStartHttp:
                    Log.i(TAG, "Start HTTP : ");
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_HTTP);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    Log.i(TAG, "prepareAsync: ");
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepareAsync();
                    break;
                case R.id.btnStartStream:
                    Log.i(TAG, "start Stream : ");
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_STREAM);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    Log.i(TAG, "prepareAsync: ");
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepareAsync();
                    break;
                case R.id.btnStartSD:
                    Log.i(TAG, "start SD : ");
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_SD);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    break;
                case R.id.btnStartUri:
                    Log.i(TAG, "start Uri : ");
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(this, DATA_URI);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    break;
                case R.id.btnStartRaw:
                    Log.i(TAG, "start Raw ");
                    mediaPlayer = MediaPlayer.create(this, R.raw.kalimba);
                    mediaPlayer.start();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mediaPlayer == null)
            return;

        mediaPlayer.setLooping(chbLoop.isChecked());
        mediaPlayer.setOnCompletionListener(this);
    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void onClick(View view) {
        if (mediaPlayer == null)
            return;
        switch (view.getId()) {
            case R.id.btnPause:
                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                break;
            case R.id.btnResume:
                if (!mediaPlayer.isPlaying())
                    mediaPlayer.start();
                break;
            case R.id.btnStop:
                mediaPlayer.start();
                break;
            case R.id.btnBackward:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 3000);
                break;
            case R.id.btnForward:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 3000);
                break;
            case R.id.btnInfo:
                Log.i(TAG, "Playing : " + mediaPlayer.isPlaying());
                Log.i(TAG, "Time : " + mediaPlayer.getCurrentPosition() + " / "
                        + mediaPlayer.getDuration());
                Log.i(TAG, "Looping : " + mediaPlayer.isLooping());
                Log.i(TAG, "Volume : " + am.getStreamVolume(AudioManager.STREAM_MUSIC));
                break;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.i(TAG, "onCompletion: ");
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.i(TAG, "onPrepared: ");
        mp.start();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMP();
    }
}
