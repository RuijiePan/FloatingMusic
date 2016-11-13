package com.jiepier.floatmusic.ui;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jiepier.floatmusic.R;
import com.jiepier.floatmusic.widget.RotateView;

public class MainActivity extends AppCompatActivity {

    private RotateView rotateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rotateView = (RotateView) findViewById(R.id.rotateView);
        rotateView.setCdImage(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        rotateView.startRoll();
    }
}
