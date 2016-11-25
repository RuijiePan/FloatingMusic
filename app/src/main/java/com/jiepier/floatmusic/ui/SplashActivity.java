package com.jiepier.floatmusic.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.jiepier.floatmusic.R;
import com.jiepier.floatmusic.service.PlayService;

/**
 * Created by JiePier on 16/11/13.
 */
public class SplashActivity extends AppCompatActivity {

	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// no title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash_layout);


		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
				&& ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
					100);
			return;
		}else {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					startActivity(new Intent(SplashActivity.this, MainActivity.class));
					finish();
				}
			}, 2000);
		}
	}

	@Override
	public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 100) {
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				// Permission granted.
				startService(new Intent(this,PlayService.class));
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
				finish();
			} else {
				// User refused to grant permission.
				Toast.makeText(this,"请先给予读读写权限，大哥",Toast.LENGTH_LONG).show();
			}
		}
	}
}
