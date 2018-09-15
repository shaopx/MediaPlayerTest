package sandy.android.com.opengl_glsurfaceview_impl;


import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.spx.opengl.core.ShaderUtil;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 *
 */
public class MyRenderer implements GLSurfaceView.Renderer {
    private int program;
    private int vPosition;
    private int uColor;


    /**
     * 当GLSurfaceView中的Surface被创建的时候(界面显示)回调此方法，一般在这里做一些初始化
     *
     * @param gl10      1.0版本的OpenGL对象，这里用于兼容老版本，用处不大
     * @param eglConfig egl的配置信息(GLSurfaceView会自动创建egl，这里可以先忽略)
     */
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        // 初始化着色器
        // 基于顶点着色器与片元着色器创建程序
        program = ShaderUtil.createProgram(ShaderUtil.verticesShader, ShaderUtil.fragmentShader);
        // 获取着色器中的属性引用id(传入的字符串就是我们着色器脚本中的属性名)
        vPosition = GLES30.glGetAttribLocation(program, "vPosition");
        uColor = GLES30.glGetUniformLocation(program, "uColor");

        // 设置clear color颜色RGBA(这里仅仅是设置清屏时GLES20.glClear()用的颜色值而不是执行清屏)
        GLES30.glClearColor(1.0f, 0, 0, 1.0f);
    }

    /**
     * 当GLSurfaceView中的Surface被改变的时候回调此方法(一般是大小变化)
     *
     * @param gl10   同onSurfaceCreated()
     * @param width  Surface的宽度
     * @param height Surface的高度
     */
    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        // 设置绘图的窗口(可以理解成在画布上划出一块区域来画图)
        GLES30.glViewport(0, 0, width, height);
    }

    /**
     * 当Surface需要绘制的时候回调此方法
     * 根据GLSurfaceView.setRenderMode()设置的渲染模式不同回调的策略也不同：
     * GLSurfaceView.RENDERMODE_CONTINUOUSLY : 固定一秒回调60次(60fps)
     * GLSurfaceView.RENDERMODE_WHEN_DIRTY   : 当调用GLSurfaceView.requestRender()之后回调一次
     *
     * @param gl10 同onSurfaceCreated()
     */
    @Override
    public void onDrawFrame(GL10 gl10) {
        // 获取图形的顶点坐标
        FloatBuffer vertices = ShaderUtil.getVertices();

        // 清屏
        GLES30.glClear(GLES30.GL_DEPTH_BUFFER_BIT | GLES30.GL_COLOR_BUFFER_BIT);

        // 使用某套shader程序
        GLES30.glUseProgram(program);
        // 为画笔指定顶点位置数据(vPosition)
        GLES30.glVertexAttribPointer(vPosition, 2, GLES30.GL_FLOAT, false, 0, vertices);
        // 允许顶点位置数据数组
        GLES30.glEnableVertexAttribArray(vPosition);
        // 设置属性uColor(颜色 索引,R,G,B,A)
        GLES30.glUniform4f(uColor, 0.0f, 1.0f, 0.0f, 1.0f);
        // 绘制
        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, 3);
    }


}

