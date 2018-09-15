package sandy.android.com.opengl_glsurfaceview_impl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class OpenGlWithGlSurfaceViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opengl_glsurfaceview_activity_main);

        GLSurfaceView glSurfaceView = findViewById(R.id.glv_main_demo);
        glSurfaceView.setEGLContextClientVersion(3); // 一定要设置
        glSurfaceView.setRenderer(new MyRenderer());
//        demoGlv.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
//        demoGlv.requestRender();
    }
}