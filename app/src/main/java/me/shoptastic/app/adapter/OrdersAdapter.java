package me.shoptastic.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;

import me.shoptastic.app.R;
import me.shoptastic.app.data.firebase.CartRepository;
import me.shoptastic.app.data.firebase.StoreRepository;
import me.shoptastic.app.data.model.Order;
import me.shoptastic.app.data.model.Store;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    private final Context context;
    private OrderClickListener clickListener;
    private Store store;
    private CartRepository repository;
    private ArrayList<Order> orders;

    public OrdersAdapter(Context ct, OrderClickListener clickListener, Store s) {
        this.context = ct;
        this.clickListener = clickListener;
        this.repository = CartRepository.getInstance();
        this.store = s;
        this.orders = new ArrayList<>();
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.orders_row, parent, false);
        repository.getStoreOrders(store.getName(), this.orders);
        return new OrdersViewHolder(view, this.clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        holder.orderTitle.setText("Placeholder Name");
        holder.orderDescription.setText(orders.get(position).getTotalQuantity() + " Items");
        holder.orderPrice.setText("$"+orders.get(position).getSumPrice());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView orderTitle, orderDescription, orderPrice;
        OrderClickListener clickListener;

        public OrdersViewHolder(@NonNull View itemView, OrderClickListener clickListener) {
            super(itemView);
            this.orderTitle = itemView.findViewById(R.id.orderTitleTextView);
            this.orderDescription = itemView.findViewById(R.id.orderDescriptionTextView);
            this.orderPrice = itemView.findViewById(R.id.orderPriceTextView);
            this.clickListener = clickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.clickListener.onOrderClick(getAdapterPosition());
        }
    }

    public interface OrderClickListener {
        void onOrderClick(int position);
    }

}
