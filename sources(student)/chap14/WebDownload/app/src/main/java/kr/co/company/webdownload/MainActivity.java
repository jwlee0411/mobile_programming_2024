package kr.co.company.webdownload;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    TextView tView;
    String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tView = (TextView) findViewById(R.id.text);
    }

    public void onClick(View v) {
        if (isNetworkAvailable()) {
            // 입력된 URL 가져오기
            EditText urlEdit = (EditText) findViewById(R.id.url);
            url = urlEdit.getText().toString();

            // 백그라운드 스레드 생성 및 실행
            Thread downloadThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 백그라운드에서 URL 다운로드
                        final String result = downloadUrl(url);

                        // UI 업데이트를 post() 메서드를 사용해 메인(UI) 스레드에서 실행
                        tView.post(new Runnable() {
                            @Override
                            public void run() {
                                tView.setText(result);
                                Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (Exception e) {
                        Log.d("Background Task", e.toString());
                    }
                }
            });
            downloadThread.start();
        } else {
            // 네트워크를 사용할 수 없을 때 메시지 표시
            Toast.makeText(getBaseContext(), "Network is not Available", Toast.LENGTH_SHORT).show();
        }
    }

    // 네트워크 연결 가능 여부 확인
    private boolean isNetworkAvailable() {
        boolean available = false;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable())
            available = true;
        return available;
    }

    // URL에서 데이터 다운로드
    private String downloadUrl(String strUrl) throws IOException {
        String s = null;
        byte[] buffer = new byte[1000];
        InputStream iStream = null;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            iStream.read(buffer);
            s = new String(buffer);
        } catch (Exception e) {
            Log.d("Exception download", e.toString());
        } finally {
            if (iStream != null) {
                iStream.close();
            }
        }
        return s;
    }
}