package sandy.android.com.opengl_egl_impl_2;


import android.opengl.GLES20;

import com.spx.opengl.core.ShaderUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;


/**
 *
 */

public class TestRenderer extends GLRenderer {
    private static final String TAG = "TestRenderer";
    private int program;
    private int vPosition;
    private int uColor;

    private FloatBuffer vertices;



    @Override
    public void onCreated() {
        //基于顶点着色器与片元着色器创建程序
        program = ShaderUtil.createProgram(ShaderUtil.verticesShader, ShaderUtil.fragmentShader);
        // 获取着色器中的属性引用id(传入的字符串就是我们着色器脚本中的属性名)
        vPosition = GLES20.glGetAttribLocation(program, "vPosition");
        uColor = GLES20.glGetUniformLocation(program, "uColor");

        vertices = ShaderUtil.getVertices();
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void onDrawFrame(GLSurface surface) {
        // 设置clear color颜色RGBA(这里仅仅是设置清屏时GLES20.glClear()用的颜色值而不是执行清屏)
        GLES20.glClearColor(1.0f, 0, 0, 1.0f);

        // 清除深度缓冲与颜色缓冲(清屏,否则会出现绘制之外的区域花屏)
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        // 使用某套shader程序
        GLES20.glUseProgram(program);
        // 为画笔指定顶点位置数据(vPosition)
        GLES20.glVertexAttribPointer(vPosition, 2, GLES20.GL_FLOAT, false, 0, vertices);
        // 允许顶点位置数据数组
        GLES20.glEnableVertexAttribArray(vPosition);
        // 设置属性uColor(颜色 索引,R,G,B,A)
        GLES20.glUniform4f(uColor, 0.0f, 1.0f, 0.0f, 1.0f);
        // 绘制
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 3);
    }

    @Override
    public void onDestroy() {

    }
}

