package me.shoptastic.app.adapter;

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
import me.shoptastic.app.ui.Cart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private final CartRepository repository = CartRepository.getInstance();
    private final Order order = repository.getOrder();
    private final Cart context;

    public CartAdapter(Cart ct) {
        this.context = ct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.viewholder_cart, parent, false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        Product product = order.get(position);
        updateUI(holder, product);
        if (!order.getCheckout()) {
            holder.plusItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    repository.addProduct(product);
                    updateUI(holder, product);
                }
            });

            holder.minusItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    repository.removeProduct(product);
                    updateUI(holder, product);
                }
            });
        } else {
            holder.plusItem.setVisibility(View.GONE);
            holder.minusItem.setVisibility(View.GONE);
        }
    }

    public void checkout() {
        notifyDataSetChanged();
    }

    private void updateUI(@NonNull ViewHolder holder, Product product) {
        holder.title.setText(product.getName());
        holder.feeEachItem.setText(String.valueOf(product.getPrice()));
        holder.totalEachItem.setText(String.valueOf((product.getPrice() * order.getQuantity(product))));
        holder.num.setText(String.valueOf(order.getQuantity(product)));
        context.calculateCart();
    }

    @Override
    public int getItemCount() {
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
