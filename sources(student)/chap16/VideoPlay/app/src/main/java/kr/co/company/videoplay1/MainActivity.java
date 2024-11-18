package kr.co.company.videoplay1;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        videoview = (VideoView) this.findViewById(R.id.videoview);
        MediaController mc = new MediaController(this){
            @Override
            public void show() {
                super.show(0);
            }
        };
        videoview.setMediaController(mc);
        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.trailer));
        videoview.start();
        videoview.requestFocus();
        mc.setEnabled(true);
        mc.show(0);
    }
}