package com.example.listview01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listview01.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 데이터 배열 생성
        String[] data = {"Apple", "Apricot", "Avocado", "Banana", "Blackberry",
                "Blueberry", "Cherry", "Coconut", "Cranberry",
                "Grape Raisin", "Honeydew", "Jackfruit", "Lemon", "Lime",
                "Mango", "Watermelon"};

        // ArrayAdapter를 사용하여 데이터를 ListView에 연결
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);


        // ListView에 항목 클릭 리스너 추가
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭한 항목의 텍스트를 가져와서 토스트 메시지로 표시
                String selectedItem = data[position];
                Toast.makeText(getApplicationContext(), "선택한 항목: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
