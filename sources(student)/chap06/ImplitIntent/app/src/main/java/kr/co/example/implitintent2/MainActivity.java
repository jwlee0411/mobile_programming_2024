package kr.co.example.implitintent2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick1(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.google.com"));
        if (intent != null)
            startActivity(intent);
    }
    public void onClick2(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:(+82)12345789"));
        if (intent != null)
            startActivity(intent);
    }
    public void onClick3(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:37.30,127.2?z=10"));
        if (intent != null)
            startActivity(intent);
    }
    public void onClick4(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("content://contacts/people/"));
        if (intent != null)
            startActivity(intent);
    }


}