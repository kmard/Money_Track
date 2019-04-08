package com.example.p000g_player;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.jgabrielfreitas.core.BlurImageView;

public class MainActivity extends AppCompatActivity {

    private BlurImageView blur_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blur_img = (BlurImageView) findViewById(R.id.warren);
        blur_img.setBlur(1);
    }
}
