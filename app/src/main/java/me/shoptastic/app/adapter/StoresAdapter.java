package me.shoptastic.app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import me.shoptastic.app.R;
import me.shoptastic.app.data.firebase.StoreRepository;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.StoresViewHolder> {

    private final Context context;
    private final StoreRepository repository;

    public StoresAdapter(Context ct) {
        this.context = ct;
        this.repository = StoreRepository.getInstance();
        Log.d("test", "THIS MANY " + this.repository.getStores().size());
    }

    @NonNull
    @Override
    public StoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.stores_row, parent, false);
        return new StoresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoresViewHolder holder, int position) {
        holder.storeTitle.setText(this.repository.getStores().get(position).getName());
        Log.d("test", this.repository.getStores().get(position).getDescription());
        holder.storeDescription.setText(this.repository.getStores().get(position).getDescription());
        holder.storeIcon.setImageResource(R.drawable.img3);
    }

    @Override
    public int getItemCount() {
        return this.repository.getStores().size();
    }

    public class StoresViewHolder extends RecyclerView.ViewHolder {

        TextView storeTitle, storeDescription;
        ImageView storeIcon;

        public StoresViewHolder(@NonNull View itemView) {
            super(itemView);
            this.storeTitle = itemView.findViewById(R.id.storeTitleTextView);
            this.storeDescription = itemView.findViewById(R.id.storeDescriptionTextView);
            this.storeIcon = itemView.findViewById(R.id.storeImageView);
        }
    }


}
