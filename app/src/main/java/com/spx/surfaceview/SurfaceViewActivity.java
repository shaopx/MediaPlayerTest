package com.spx.surfaceview;

import android.app.Activity;
import android.os.Bundle;

public class SurfaceViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int type = getIntent().getIntExtra("type", 0);
        if (type == 0) {
            // 这个是最简单的surfaceview展示, 不响应用户, 只是自己更新view
            setContentView(new MySurfaceView(this));
        } else if (type == 1) {
            // 这个可以响应用户手指触摸事件
            setContentView(new DoodleViewSurface(this));
        }

    }
}
