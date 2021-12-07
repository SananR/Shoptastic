package me.shoptastic.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import me.shoptastic.app.R;
import me.shoptastic.app.data.firebase.CartRepository;
import me.shoptastic.app.data.model.Order;
import me.shoptastic.app.data.model.Product;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private final Order order = CartRepository.getInstance().getOrder();
    private final Context context;

    public CartAdapter(Context ct) {
        this.context = ct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.activity_cart, parent, false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        Product product = order.get(position);
        holder.title.setText(product.getName());
        holder.feeEachItem.setText(String.valueOf(product.getPrice()));
        holder.totalEachItem.setText(String.valueOf((product.getPrice() * order.getQuantity(product))));
        holder.num.setText(String.valueOf(order.getQuantity(product)));

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.addProduct(product);
                holder.num.setText(String.valueOf(order.getQuantity(product)));
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.addProduct(product);
                holder.num.setText(String.valueOf(order.getQuantity(product)));
            }
        });
    }

    @Override
    public int getItemCount(){
        return order.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        TextView totalEachItem, num;
        ImageView plusItem, minusItem;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title2Txt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);
            plusItem = itemView.findViewById(R.id.plusBtnCart);
            minusItem = itemView.findViewById(R.id.minusBtnCart);
        }

    }
}
