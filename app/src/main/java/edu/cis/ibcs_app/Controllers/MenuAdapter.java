package edu.cis.ibcs_app.Controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.cis.ibcs_app.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {
    ArrayList<String> namesData;
    ArrayList<String> descData;
    ArrayList<String> typeData;
    ArrayList<String> priceData;
    ArrayList<String> itemIdData;
    public MenuAdapter (ArrayList names, ArrayList descriptions, ArrayList types, ArrayList prices, ArrayList itemIds){
        namesData = names;
        descData = descriptions;
        typeData = types;
        priceData = prices;
        itemIdData = itemIds;
    }
    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cis_row_view, parent, false);
        MenuViewHolder holder = new MenuViewHolder(menuView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.nameTextView.setText(namesData.get(position));
        holder.descTextView.setText(descData.get(position));
        holder.typeTextView.setText(typeData.get(position));
        holder.priceTextView.setText("HK$" + priceData.get(position));
        holder.itemIdTextView.setText(itemIdData.get(position));
    }

    @Override
    public int getItemCount() {
        return namesData.size();
    }
}
