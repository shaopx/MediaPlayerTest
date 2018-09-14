package sandy.android.com.opengl_glsurfaceview_impl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class OpenGlWithGlSurfaceViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(sandy.android.com.opengl_glsurfaceview_impl.R.layout.opengl_glsurfaceview_activity_main);

        GLSurfaceView demoGlv = (GLSurfaceView) findViewById(sandy.android.com.opengl_glsurfaceview_impl.R.id.glv_main_demo);
        demoGlv.setEGLContextClientVersion(2); // 一定要设置
        demoGlv.setRenderer(new MyRenderer());
        demoGlv.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
//        demoGlv.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
//        demoGlv.requestRender();
    }
}