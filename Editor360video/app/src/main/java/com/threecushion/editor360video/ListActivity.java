package com.threecushion.editor360video;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.view.View;
import android.content.Intent;

import java.io.File;
import java.util.ArrayList;


public class ListActivity extends Activity {


    ListView listView;
    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        listView = (ListView)findViewById(R.id.PlayList);

        final ArrayList<File> myVideos = findVideos(Environment.getExternalStorageDirectory());

            items = new String[myVideos.size()];
            for (int i = 0; i < myVideos.size(); i++) {
                items[i] = myVideos.get(i).getName().toString();
            }

            ArrayAdapter<String> adp = new ArrayAdapter<String>(getApplicationContext(), R.layout.video_layout, R.id.videoTitle, items);
            listView.setAdapter(adp);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), Player.class).putExtra("pos", position).putExtra("videoslist", myVideos));

            }

        });
    }
    public ArrayList<File> findVideos(File root) {
        ArrayList<File> a1 = new ArrayList<File>();
        File[] files = root.listFiles();

            for (File singleFile : files) {
                if (singleFile.isDirectory() && !singleFile.isHidden()) {
                    a1.addAll(findVideos(singleFile));

                } else {
                    if (singleFile.getName().endsWith(".mp4")) {
                        a1.add(singleFile);
                    }
                }
            }
            return a1;
    }

}
