package com.example.graphictext;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

class MyView extends View {
    public MyView(Context context) {
        super(context);
        setBackgroundColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(100);

        Typeface t;
        t = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);
        paint.setTypeface(t);
        canvas.drawText("DEFAULT 폰트", 10, 200, paint);

        t = Typeface.create(Typeface.SERIF, Typeface.ITALIC);
        paint.setTypeface(t);
        canvas.drawText("SERIF 폰트", 10, 300, paint);

        t = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
        paint.setTypeface(t);
        canvas.drawText("SANS_SERIF 폰트", 10, 400, paint);
    }
}

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView w = new MyView(this);
        setContentView(w);
    }
}