package kr.co.company.mp3player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> songList = new ArrayList<String>();
    ListView listview;
    Button bPlay, bStop;
    String curSong;
    MediaPlayer mp = null;
    String songPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        songPath = Environment.getExternalStorageDirectory().getPath() + "/";
        Log.i("KKK", songPath);
        FileFilter filter = file -> !file.isDirectory() && file.getName().endsWith(".mp3");
        File[] files = new File(songPath).listFiles(filter);
        if (files != null) {
            for (File file : files) {
                songList.add(file.getName());
            }
        }

        listview = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, songList);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listview.setAdapter(adapter);
        listview.setItemChecked(0, true);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int arg2, long arg3) {
                curSong = songList.get(arg2);
            }
        });
        if (songList.size() != 0)
            curSong = songList.get(0);
    }

    public void play(View v) {
        try {
            mp = new MediaPlayer();
            mp.setDataSource(songPath + curSong);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            Log.i("KKK", e.toString());
        }
    }

    public void stop(View v) {
        if (mp != null) {
            mp.stop();
            mp.release();
        }
        mp = null;
    }
}