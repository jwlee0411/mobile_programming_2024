package kr.co.company.mobileshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    static List<Product> products;
    static List<Product> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 제품 및 장바구니 리스트 초기화
        products = new ArrayList<>();
        cartItems = new ArrayList<>();

        // RecyclerView 설정
        recyclerView = findViewById(R.id.productRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ProductAdapter(products, this));

        // 여기에서 products 리스트에 제품을 추가하세요.
        products.add(new Product(1, "제품 1", 700000, "이 제품은 샘플 제품 1입니다.", R.drawable.product1));
        products.add(new Product(2, "제품 2", 1200000, "이 제품은 샘플 제품 2입니다.", R.drawable.product2));
        products.add(new Product(3, "제품 3", 600000, "이 제품은 샘플 제품 3입니다.", R.drawable.product3));
        // 다른 제품들 추가

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 옵션 메뉴를 생성하고 메뉴 아이템을 추가합니다.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_menu, menu);
        return true;
    }

    public void show_cart(MenuItem item) {
        // "장바구니 보기" 메뉴 항목을 클릭한 경우, 장바구니 화면으로 이동하도록 코드를 추가
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}
