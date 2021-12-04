package me.shoptastic.app.Adapter;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.shoptastic.app.R;
import me.shoptastic.app.ShowDetailActivity;
import me.shoptastic.app.data.model.Product;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    ArrayList<Product> productDomains;

    public CartAdapter (ArrayList<Product> productDomains){
        this.productDomains = productDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);

        return new ViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.title.setText(productDomains.get(position).getName());
        holder.fee.setText(String.valueOf(productDomains.get(position).getPrice()));

        holder.addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", (Parcelable) productDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }

        });
    }
    @Override
    public int getItemCount(){
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, fee;
        TextView addBtn;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            fee = itemView.findViewById(R.id.fee);

        }

    }
}
