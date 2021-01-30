package id.ac.ui.cs.mobileprogramming.salsahava.journ.ui;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.util.OpenGLRenderer;

public class OpenGLView extends GLSurfaceView {
    public OpenGLView(Context context) {
        super(context);
        init();
    }

    public OpenGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        setRenderer(new OpenGLRenderer());
    }
}
