package me.shoptastic.app.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.shoptastic.app.Interface.Callback;
import me.shoptastic.app.R;
import me.shoptastic.app.data.firebase.ProductRepository;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.ui.ProductAddActivity;
import me.shoptastic.app.ui.ProductsActivity;
import me.shoptastic.app.ui.StoreProductsActivity;

public class EditProductAdapter extends RecyclerView.Adapter<EditProductAdapter.ViewHolder> {
    ArrayList<Product> productDomains;
    StoreProductsActivity view;

    public EditProductAdapter(StoreProductsActivity view, String store) {
        this.productDomains = ProductRepository.getInstance().getProducts(store, new Callback() {
            @Override
            public void callback() {
                notifyDataSetChanged();
            }
        });
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.view);
        View view = inflater.inflate(R.layout.viewholder_products, parent, false);
        return new EditProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final Product product = productDomains.get(position);
        holder.title.setText(product.getName());
        holder.fee.setText(String.valueOf(product.getPrice()));
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view, ProductAddActivity.class);
                intent.putExtra(ProductsActivity.productName, product.getName());
                intent.putExtra(ProductsActivity.productPrice, product.getPrice());
                intent.putExtra(ProductsActivity.productDescription, product.getDescription());
                intent.putExtra(ProductsActivity.productID, product.getId());
                intent.putExtra(ProductsActivity.productStore, product.getStoreName());
                view.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return productDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, fee;
        TextView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title2Txt);
            fee = itemView.findViewById(R.id.fee);
            addBtn = itemView.findViewById(R.id.addBtn);

        }

    }
}
