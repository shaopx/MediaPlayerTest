package com.spx.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Toast;

import com.spx.mediaplayertest.R;

/**
 * copy from http://www.jb51.net/article/80260.htm
 * 一个涂鸦的画板
 */
public class DoodleViewSurface extends SurfaceView implements Callback, Runnable {


    private static final String TAG = "DoodleViewSurface";

    /**
     * 控制游戏循环
     **/
    boolean mIsRunning = false;

    /**
     * 每50帧刷新一次屏幕
     **/
    public static final int TIME_IN_FRAME = 50;

    private int paintColor = android.graphics.Color.WHITE;

    private float paintWidth = 2f;//默认画笔宽度

    private Style paintStyle = Style.STROKE;//默认画笔风格

    private int paintAlph = 255;//默认不透明

    private Path mPath;//轨迹

    private Paint mPaint;//画笔

    private float startX = 0.0f;//初始x

    private float startY = 0.0f;//初始Y

    private SurfaceHolder surfaceHolder;

    public Canvas mCanvas;

    public boolean first = true;

    Bitmap bg;

    public DoodleViewSurface(Context context) {
        this(context, null);
    }

    public DoodleViewSurface(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoodleViewSurface(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // TODO Auto-generated constructor stub
        this.setFocusable(true);//设置当前view拥有触摸事件

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mPath = new Path();
        initPaint();

        bg = BitmapFactory.decodeResource(getResources(), R.drawable.pic1).copy(Bitmap.Config.ARGB_8888, true);//白色的画板
    }

    @Override
    protected void onFinishInflate() {
        Log.d(TAG, "onFinishInflate: ....");
        super.onFinishInflate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ....");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow: ....");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow: ....");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: ....");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout: ....");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: ...");
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        Log.d(TAG, "onWindowVisibilityChanged: ...");
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.d(TAG, "onFocusChanged: ...");
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.d(TAG, "onWindowFocusChanged: ...");
    }

    /**
     * @param
     * @return void 返回类型
     * @throws
     * @Title: initPaint
     * @Description: TODO(初始化画笔)
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setColor(paintColor);//画笔颜色
        mPaint.setAlpha(paintAlph);//画笔透明度
        mPaint.setStyle(paintStyle);//设置画笔风格
        mPaint.setStrokeWidth(paintWidth);//设置画笔宽度
    }

    public void doDraw() {
        mCanvas = surfaceHolder.lockCanvas();
        // 设定Canvas对象的背景颜色
        mCanvas.drawColor(Color.parseColor("#33000000"));
        mCanvas.drawPath(mPath, mPaint);//绘制
        surfaceHolder.unlockCanvasAndPost(mCanvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //手接触屏幕时触发
                doTouchDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                //手滑动时触发
                doTouchMove(event);
                break;

            case MotionEvent.ACTION_UP:
                //手抬起时触发

                break;


            default:
                break;
        }
        return true;
    }

    /**
     * @param @param event 设定文件
     * @return void 返回类型
     * @throws
     * @Title: doTouchDown
     * @Description: TODO(手触摸到屏幕时需要做的事情)
     */
    private void doTouchDown(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();
        startX = touchX;
        startY = touchY;
        mPath.reset();
        mPath.moveTo(touchX, touchY);
    }

    /**
     * @param @param event 设定文件
     * @return void 返回类型
     * @throws
     * @Title: doTouchMove
     * @Description: TODO(手在屏幕上滑动时要做的事情)
     */
    private void doTouchMove(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();

        float dx = Math.abs(touchX - startX);//移动的距离

        float dy = Math.abs(touchY - startX);//移动的距离

        if (dx > 3 || dy > 3) {
            float cX = (touchX + startX) / 2;
            float cY = (touchY + startY) / 2;
            mPath.quadTo(startX, startY, cX, cY);

            startX = touchX;
            startY = touchY;

        }

    }

    public void setPaintColor(int paintColor) {
        this.paintColor = paintColor;
        initPaint();
    }

    public void setPaintWidth(float paintWidth) {
        this.paintWidth = paintWidth;
        initPaint();
    }

    public void setPaintStyle(Style paintStyle) {
        this.paintStyle = paintStyle;
        initPaint();
    }

    public void setPaintAlph(int paintAlph) {
        this.paintAlph = paintAlph;
        initPaint();
    }


    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (mIsRunning) {

            /** 取得更新游戏之前的时间 **/
            long startTime = System.currentTimeMillis();
            /** 在这里加上线程安全锁 **/
            synchronized (surfaceHolder) {
                doDraw();
            }

            /** 取得更新游戏结束的时间 **/
            long endTime = System.currentTimeMillis();

            /** 计算出游戏一次更新的毫秒数 **/
            int diffTime = (int) (endTime - startTime);

            /** 确保每次更新时间为50帧 **/
            while (diffTime <= TIME_IN_FRAME) {
                diffTime = (int) (System.currentTimeMillis() - startTime);
                /** 线程等待 **/
                Thread.yield();
            }

        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated: ....");
        // TODO Auto-generated method stub
        mCanvas = surfaceHolder.lockCanvas();
        mCanvas.drawBitmap(bg, 0, 0, null);
        surfaceHolder.unlockCanvasAndPost(mCanvas);

        Toast.makeText(getContext(), "请用手滑动", Toast.LENGTH_SHORT).show();

        mIsRunning = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub
        Log.d(TAG, "surfaceChanged: ....width:"+width+" height:"+height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed: ....");
        // TODO Auto-generated method stub
        mIsRunning = false;
    }


}