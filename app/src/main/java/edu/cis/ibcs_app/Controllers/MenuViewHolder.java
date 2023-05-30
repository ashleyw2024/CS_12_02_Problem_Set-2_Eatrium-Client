package edu.cis.ibcs_app.Controllers;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.cis.ibcs_app.R;

public class MenuViewHolder extends RecyclerView.ViewHolder {
    protected TextView typeTextView;
    protected TextView priceTextView;
    protected TextView nameTextView;
    protected TextView descTextView;
    protected TextView itemIdTextView;
    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        typeTextView = itemView.findViewById(R.id.typeTextView);
        nameTextView = itemView.findViewById(R.id.nameTextView);
        descTextView = itemView.findViewById(R.id.descTextView);
        priceTextView = itemView.findViewById(R.id.priceTextView);
        itemIdTextView = itemView.findViewById(R.id.itemIdTextView);
    }
}
