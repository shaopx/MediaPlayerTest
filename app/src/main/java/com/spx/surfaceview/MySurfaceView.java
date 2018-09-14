package com.spx.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class MySurfaceView extends SurfaceView implements
        SurfaceHolder.Callback {

    private String TAG = "conowen";

    private SurfaceHolder surfaceHolder;
    private boolean ThreadFlag;
    private int counter;
    private Canvas canvas;

    private Thread mThread = new Thread(new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (ThreadFlag) {

                // 锁定画布，得到Canvas对象
                canvas = surfaceHolder.lockCanvas();

                // 设定Canvas对象的背景颜色
                canvas.drawColor(Color.GREEN);

                // 创建画笔
                Paint p = new Paint();
                // 设置画笔颜色
                p.setColor(Color.RED);
                // 设置文字大小
                p.setTextSize(40);

                // 创建一个Rect对象rect
                // public Rect (int left, int top, int right, int bottom)
                Rect rect = new Rect(100, 50, 100+(new Random().nextInt(400)),
                        50+(new Random().nextInt(400)));
                // 在canvas上绘制rect
                canvas.drawRect(rect, p);
                // 在canvas上显示时间
                // public void drawText (String text, float x, float y, Paint
                // paint)
                canvas.drawText("时间 = " + (counter++) + " 秒", 500, 200, p);

                if (canvas != null) {
                    // 解除锁定，并提交修改内容，更新屏幕
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    });

    public MySurfaceView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

        // 通过SurfaceView获得SurfaceHolder对象
        surfaceHolder = this.getHolder();

        // 为SurfaceHolder添加回调结构SurfaceHolder.Callback
        surfaceHolder.addCallback(this);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub
        Log.i(TAG, "surfaceChanged");

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        Log.i(TAG, "surfaceCreated");
        counter = 0;
        ThreadFlag = true;
        mThread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        Log.i(TAG, "surfaceDestroyed");
        ThreadFlag = false;

    }

}