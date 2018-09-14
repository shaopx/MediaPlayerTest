package com.spx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.spx.camera.CameraActivity;
import com.spx.camera.recorder.VideoRecorderActivity;
import com.spx.glsurfaceview.CubeFlingActivity;
import com.spx.glsurfaceview.GLSurfaceViewTestActivity;
import com.spx.mediaplayertest.R;
import com.spx.surfaceview.SurfaceViewActivity;

import sandy.android.com.opengl_egl_impl.EglImpl1Activity;
import sandy.android.com.opengl_egl_impl_2.EglImp2Activity;
import sandy.android.com.opengl_glsurfaceview_impl.OpenGlWithGlSurfaceViewActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSurfaceViewActivity(View view) {
        Log.d(TAG, "startSurfaceViewActivity: ...");
        Intent intent = new Intent(this, SurfaceViewActivity.class);
        intent.putExtra("type", 0);
        startActivity(intent);
    }

    public void startGameSurfaceViewActivity(View view) {
        Log.d(TAG, "startGameSurfaceViewActivity: ...");
        Intent intent = new Intent(this, SurfaceViewActivity.class);
        intent.putExtra("type", 1);
        startActivity(intent);
    }

    public void startGLSurfaceViewActivity(View view) {
        Log.d(TAG, "startGLSurfaceViewActivity: ...");
        Intent intent = new Intent(this, GLSurfaceViewTestActivity.class);
        startActivity(intent);
    }

    public void startGLSurfaceViewGestureActivity(View view) {
        Log.d(TAG, "startGLSurfaceViewGestureActivity: ...");
        Intent intent = new Intent(this, CubeFlingActivity.class);
        startActivity(intent);
    }

    public void startCameraActivity(View view) {
        Log.d(TAG, "startCameraActivity: ...");
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void startCameraRecorderActivity(View view) {
        Log.d(TAG, "startCameraRecorderActivity: ...");
        Intent intent = new Intent(this, VideoRecorderActivity.class);
        startActivity(intent);
    }

    public void startGLSurfaceViewOpenglActivity(View view) {
        Intent intent = new Intent(this, OpenGlWithGlSurfaceViewActivity.class);
        startActivity(intent);
    }

    public void startEGLImpl1Activity(View view) {
        Intent intent = new Intent(this, EglImpl1Activity.class);
        startActivity(intent);
    }

    public void startEGLImpl2Activity(View view) {
        Intent intent = new Intent(this, EglImp2Activity.class);
        startActivity(intent);
    }
}
