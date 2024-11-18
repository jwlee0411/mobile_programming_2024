package kr.co.company.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textViewRandomNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewRandomNumber = findViewById(R.id.textViewRandomNumber);
    }
    public void generateRandomNumber(View view) {
// 난수 생성
        Random random = new Random( );
        int randomNumber = random.nextInt(100); // 0부터 99까지의 난수 생성
// 텍스트 뷰에 난수 표시
        textViewRandomNumber.setText("난수: " + randomNumber);
    }
}