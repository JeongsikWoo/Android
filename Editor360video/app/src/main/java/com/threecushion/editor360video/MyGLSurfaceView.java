package com.threecushion.editor360video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by kim on 2016-04-17.
 */

@SuppressLint("ViewConstructor")
public class MyGLSurfaceView extends GLSurfaceView {

    protected Resources mResources;
    private SphereVideoRenderer mRenderer;
    private MediaPlayer mMediaPlayer;
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320 / 3.8f;
    private float mPreviousX;
    private float mPreviousY;

    public ArrayList<File> myVideos;
    public int position;
    public String filepath;

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        mMediaPlayer = new MediaPlayer();
        mResources = context.getResources();

        //filepath= myVideos.get(position).toString();

        try {
            mMediaPlayer.setDataSource("/sdcard/videoplayback2.mp4");

        } catch (Exception e) {
            Log.e("123", e.getMessage(), e);
        }

        mRenderer = new SphereVideoRenderer(context, mMediaPlayer);
        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
    }



    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            //when we move our finger
            case MotionEvent.ACTION_MOVE:
                // if we touch spherical part of screen or rectangular
                if (y < mRenderer.getScreenHeight() * (1 - 1.f / 5.f)) {
                    float dx = x - mPreviousX;
                    float dy = y - mPreviousY;


                    mRenderer.setAngleX(
                            mRenderer.getAngleX() +
                                    (dx * TOUCH_SCALE_FACTOR));
                    mRenderer.setAngleY(
                            mRenderer.getAngleY() +
                                    (dy * TOUCH_SCALE_FACTOR));
                    requestRender();
                } else {
                    mRenderer.setAngleX( -x / (float) mRenderer.getScreenWidth() * 360 - 90);
                    float relativeYCoordinate = y - mRenderer.getScreenHeight() * (1 - 1.f / 5.f);
                    mRenderer.setAngleY(90 - (180.f / (mRenderer.getScreenHeight() / 5.f))  * relativeYCoordinate);
                    requestRender();
                }
                break;

            //if we touch screen
            case MotionEvent.ACTION_DOWN :
                //here is only rectangular part of screen available
                if (y >= mRenderer.getScreenHeight() * (1 - 1.f / 5.f)) {
                    mRenderer.setAngleX(-x / (float) mRenderer.getScreenWidth() * 360 - 90);

                    float relativeYCoordinate = y - mRenderer.getScreenHeight() * (1 - 1.f / 5.f);
                    mRenderer.setAngleY(90 - (180.f / (mRenderer.getScreenHeight() / 5.f))  * relativeYCoordinate);
                    requestRender();
                }
                break;
        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }

    @Override
    public void onResume() {
        queueEvent(new Runnable(){
            public void run() {
                mRenderer.setMediaPlayer(mMediaPlayer);
            }});
        super.onResume();
    }
}
