package kr.co.company.mobileshop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> products;
    private List<Product> cartItems = MainActivity.cartItems; // MainActivity의 장바구니 아이템 목록을 가져옵니다.
    private Context context;

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // XML 레이아웃 파일을 가져와서 ViewHolder를 생성합니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product product = products.get(position);
        holder.bind(product);

        // 제품 아이템을 클릭했을 때 상세 정보 화면으로 이동하는 이벤트를 처리합니다.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("product", product); // 선택한 제품을 상세 화면으로 전달합니다.
                context.startActivity(intent); // 상세 정보 화면으로 이동합니다.
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productName;
        private TextView productPrice;
        private ImageView productImage;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);
        }

        public void bind(Product product) {
            // 제품 정보를 ViewHolder에 연결합니다.
            productName.setText(product.getName());
            productPrice.setText("" + product.getPrice() + "원");
            productImage.setImageResource(product.getImageResId());
        }
    }
}
