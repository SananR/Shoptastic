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
import me.shoptastic.app.data.firebase.StoreRepository;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.StoresViewHolder> {

    private final Context context;
    private final StoreRepository repository;
    private StoreClickListener clickListener;

    public StoresAdapter(Context ct, StoreClickListener clickListener) {
        this.context = ct;
        this.repository = StoreRepository.getInstance();
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public StoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.stores_row, parent, false);
        return new StoresViewHolder(view, this.clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StoresViewHolder holder, int position) {
        holder.storeTitle.setText(repository.getStores().get(position).getName());
        holder.storeDescription.setText(repository.getStores().get(position).getDescription());
        holder.storeIcon.setImageResource(R.drawable.img3);
    }

    @Override
    public int getItemCount() {
        return repository.getStores().size();
    }

    public class StoresViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView storeTitle, storeDescription;
        ImageView storeIcon;
        StoreClickListener clickListener;

        public StoresViewHolder(@NonNull View itemView, StoreClickListener clickListener) {
            super(itemView);
            this.storeTitle = itemView.findViewById(R.id.orderTitleTextView);
            this.storeDescription = itemView.findViewById(R.id.orderDescriptionTextView);
            this.storeIcon = itemView.findViewById(R.id.storeImageView);
            this.clickListener = clickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.clickListener.onStoreClick(getAdapterPosition());
        }
    }

    public interface StoreClickListener {
        void onStoreClick(int position);
    }

}
