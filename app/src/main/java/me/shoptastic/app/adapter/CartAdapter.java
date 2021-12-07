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

import me.shoptastic.app.Interface.ChangeNumberItemListener;
import me.shoptastic.app.R;
import me.shoptastic.app.data.model.Order;
import me.shoptastic.app.data.model.Product;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    private final ArrayList<Product> productDomains;
    private Order order;
    private final ChangeNumberItemListener changeNumberItemsListener;

    public CartAdapter (ArrayList<Product> productDomains, Context context, ChangeNumberItemListener changeNumberItemListener){
        this.productDomains = productDomains;
        this.changeNumberItemsListener = changeNumberItemListener;
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
        holder.feeEachItem.setText(String.valueOf(productDomains.get(position).getPrice()));
        holder.totalEachItem.setText(String.valueOf((productDomains.get(position).getNumberInCart() * productDomains.get(position).getPrice())));
        holder.num.setText(String.valueOf(productDomains.get(position).getNumberInCart()));

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                order.plusNumberProduct(productDomains, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v){
                order.minusNumberProduct(productDomains, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount(){
        return productDomains.size();
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
