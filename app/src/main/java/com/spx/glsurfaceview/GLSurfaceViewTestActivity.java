package com.spx.glsurfaceview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.spx.glsurfaceview.MyGLSurfaceView;

/**
 * 一个最简单的GlSurfaceView的展示例子
 */
public class GLSurfaceViewTestActivity extends Activity {

    private MyGLSurfaceView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
        Toast.makeText(this, "小帅哥, 快来用手滑啊", Toast.LENGTH_SHORT).show();
    }


}
