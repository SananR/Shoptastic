package me.shoptastic.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.shoptastic.app.Interface.Callback;
import me.shoptastic.app.R;
import me.shoptastic.app.data.firebase.CartRepository;
import me.shoptastic.app.data.model.Order;
import me.shoptastic.app.data.model.Store;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    private final Context context;
    private final OrderClickListener clickListener;
    private final Store store;
    private final CartRepository repository;
    private final ArrayList<Order> orders;

    public OrdersAdapter(Context ct, OrderClickListener clickListener, Store s, ArrayList<Order> orders) {
        this.context = ct;
        this.clickListener = clickListener;
        this.repository = CartRepository.getInstance();
        this.store = s;
        this.orders = orders;
        repository.getStoreOrders(store.getName(), orders, new Callback() {
            @Override
            public void callback() {
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.orders_row, parent, false);
        return new OrdersViewHolder(view, this.clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        holder.orderTitle.setText(orders.get(position).getUser());
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
