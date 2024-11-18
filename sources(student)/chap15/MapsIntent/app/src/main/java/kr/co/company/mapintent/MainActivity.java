package kr.co.company.mapintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse(String.format("geo:%f,%f?z=10", 37.30, 127.2));
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
