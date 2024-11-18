package kr.co.company.mobileshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<Product> cartItems = MainActivity.cartItems;

    public CartAdapter(List<Product> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 장바구니 아이템을 표시하는 뷰 홀더 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product product = cartItems.get(position);
        // 뷰 홀더에 제품 정보를 바인딩하고 "삭제" 버튼 클릭 이벤트 처리
        holder.bind(product);
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            // "삭제" 버튼 클릭 시 해당 아이템을 장바구니에서 제거하는 메서드 호출
            public void onClick(View view) {
                removeItem(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productName;
        private TextView productPrice;
        private Button removeButton;
        private ImageView productImage;

        public ViewHolder(View itemView) {
            super(itemView);
            // 아이템 뷰에서 필요한 뷰 요소들을 찾아와서 뷰 홀더에 할당
            productName = itemView.findViewById(R.id.cartItemName);
            productPrice = itemView.findViewById(R.id.cartItemPrice);
            removeButton = itemView.findViewById(R.id.removeFromCartButton);
            productImage = itemView.findViewById(R.id.cartItemProductImage);
        }

        public void bind(Product product) {
            // 제품 정보를 뷰 홀더의 각 뷰 요소에 설정
            productName.setText(product.getName());
            productPrice.setText("" + product.getPrice() + "원");
            productImage.setImageResource(product.getImageResId());

        }
    }

    public void removeItem(int position) {
        // 장바구니에서 아이템을 제거하고 이를 RecyclerView에 알림
        if (position >= 0 && position < cartItems.size()) {
            cartItems.remove(position);
            notifyItemRemoved(position);
        }
    }
}

