package id.ac.ui.cs.mobileprogramming.salsahava.journ.util;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    private static final double FLASH_DURATION = 700;
    private double greenValue = 0;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(255, (float) greenValue, 255, 1f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClearColor(255, (float) greenValue, 117, 1f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        greenValue = ((Math.sin(System.currentTimeMillis() * 2 * Math.PI / FLASH_DURATION) * 0.5) + 0.5);
    }
}
