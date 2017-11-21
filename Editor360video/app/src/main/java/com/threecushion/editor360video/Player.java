package com.threecushion.editor360video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;


public class Player extends Activity {

    public  ArrayList<File> myVideos;
    public  int position;

    private MyGLSurfaceView myGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        myVideos = (ArrayList) b.getParcelableArrayList("videoslist");
        position = b.getInt("pos",0);
        //myGLSurfaceView.setattr(myVideos,position);

        myGLSurfaceView = (MyGLSurfaceView) findViewById(R.id.glSurfaceView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        myGLSurfaceView.onResume();
    }
}