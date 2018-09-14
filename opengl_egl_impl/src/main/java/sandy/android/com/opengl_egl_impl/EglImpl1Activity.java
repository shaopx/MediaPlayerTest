package sandy.android.com.opengl_egl_impl;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class EglImpl1Activity extends Activity {
    private GLRenderer glRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.egl_impl_1_activity);

        SurfaceView sv = (SurfaceView)findViewById(R.id.sv_main_demo);
        glRenderer = new GLRenderer();
        glRenderer.start();

        sv.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
                glRenderer.render(surfaceHolder.getSurface(),width,height);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });
    }



    @Override
    protected void onDestroy() {
        glRenderer.release();
        glRenderer = null;
        super.onDestroy();
    }
}
