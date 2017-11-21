package com.threecushion.editor360video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.threecushion.editor360video.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    //Play 액티비티 호출
    public void Playcall(View view)
    {
        Intent intent = new Intent(this, ListActivity.class);

        startActivity(intent);
    }
    public void Editcall()
    {

    }
    public void Sharecall()
    {

    }

}
