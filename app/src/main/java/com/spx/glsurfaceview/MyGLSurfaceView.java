package com.spx.glsurfaceview;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by shaopengxiang on 2017/12/15.
 */

public class MyGLSurfaceView extends GLSurfaceView {
    private static final String TAG = "MyGLSurfaceView";
    private MyRenderer mMyRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);
        initReader();
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initReader();
    }

    /**
     * 在这里面设置了renderer对象
     */
    public void initReader() {
        mMyRenderer = new MyRenderer();
        setRenderer(mMyRenderer);
    }

    public boolean onTouchEvent(final MotionEvent event) {
        //由于MyRenderer对象运行在另一个线程中，这里采用跨线程的机制进行处理。使用queueEvent方法
        //当然也可以使用其他像Synchronized来进行UI线程和渲染线程进行通信。
        this.queueEvent(new Runnable() {

            @Override
            public void run() {
                mMyRenderer.setColor(event.getX() / getWidth(), event.getY() / getHeight(), 1.0f);
            }
        });

        return true;
    }

    /**
     * 好有意思的名字, renderer, 要是其他名字这么带感, 我估计我都学会opengl了.
     */
    private class MyRenderer implements GLSurfaceView.Renderer {

        private float mRed;
        private float mGreen;
        private float mBlue;

        @Override
        public void onDrawFrame(GL10 gl) {
            // 这里每个16ms会调用一次, 不要做太耗时的操作
//            Log.d(TAG, "onDrawFrame: ...");
            gl.glClearColor(mRed, mGreen, mBlue, 1.0f);
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int w, int h) {
            Log.d(TAG, "onSurfaceChanged: ...w:" + w + ", h:" + h);
            gl.glViewport(0, 0, w, h);
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            // TODO Auto-generated method stub
            Log.d(TAG, "onSurfaceCreated: ...gl:" + gl);
        }

        public void setColor(float r, float g, float b) {
            this.mRed = r;
            this.mGreen = g;
            this.mBlue = b;
        }

    }

}