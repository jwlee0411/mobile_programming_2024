package kr.co.company.audioplay;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startResAudio(View v) {
        mp = MediaPlayer.create(this, R.raw.old_pop);
        mp.start();
    }

    public void stopResAudio(View v) {
        if (mp != null) {
            mp.stop();
            mp.release();
        }
        mp = null;
    }

}