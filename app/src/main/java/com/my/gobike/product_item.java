package com.my.gobike;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class product_item extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView productname, txtdescription, productprice;
    public ImageView b_image;
    public itemclicklistener listner;

    public product_item(@NonNull View itemView) {
        super(itemView);
        b_image = itemView.findViewById(R.id.product_image);
        productname = itemView.findViewById(R.id.product_name);
        txtdescription = itemView.findViewById(R.id.product_image);
        productprice = itemView.findViewById(R.id.product_price);
    }
    public void setitemclicklistener(itemclicklistener listner){

        this.listner= listner;
    }

    @Override
    public void onClick(View v) {
        listner.onClick(v,getAdapterPosition(), false );

    }
}
