package kr.co.example.alertradio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    private String selectedDrink = "커피"; // 기본 선택값
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageview = findViewById(R.id.imageView);
        Button showDialogButton = findViewById(R.id.show_dialog_button);
        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDrinkSelectionDialog();
            }
        });
    }

    private void showDrinkSelectionDialog() {
        final String[] drinks = {"커피", "티", "밀크"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("음료 선택")
                .setSingleChoiceItems(drinks, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedDrink = drinks[which];
                        if( which==0)  imageview.setImageResource(R.drawable.tea);
                        else if(which==1)  imageview.setImageResource(R.drawable.tea);
                        else imageview.setImageResource(R.drawable.milk);
                    }
                })
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 선택한 음료에 대한 추가 작업 수행
                        // 이 예제에서는 선택한 음료를 로그에 출력
                        // 실제 액션을 수행하거나 화면에 표시하는 방식으로 변경 가능
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }
}
