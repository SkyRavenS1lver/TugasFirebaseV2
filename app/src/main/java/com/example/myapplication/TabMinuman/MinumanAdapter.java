package com.example.myapplication.TabMinuman;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataModel.Model;
import com.example.myapplication.GeneralActivity.ProductDetail;
import com.example.myapplication.TabMinuman.Activity.ReadDataMinuman;
import com.example.myapplication.Firebase.FirebaseController;
import com.example.myapplication.R;
import com.example.myapplication.TabPackage.TabActivity;

import java.util.ArrayList;

public class MinumanAdapter extends RecyclerView.Adapter<MinumanAdapter.ViewHolder> {
    ArrayList<Minuman> values;
    private final LayoutInflater inflater;

    public MinumanAdapter(@NonNull Context context, ArrayList<Minuman> values) {
        this.values = values;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MinumanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_rv_item, parent, false);
        return new MinumanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MinumanAdapter.ViewHolder holder, int position) {
        final Minuman minuman = values.get(position);
        holder.brand.setText(minuman.getBrand());
        holder.name.setText(minuman.getName());
        holder.price.setText(String.valueOf(minuman.getPrice()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.itemView.getContext(),"Makanan "+ minuman.getName()+" Terhapus", Toast.LENGTH_SHORT).show();
                Model.minumanArrayList.remove(minuman);
                ReadDataMinuman.refreshList();
                FirebaseController.deleteData(minuman);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(holder.itemView.getContext(), ProductDetail.class);
                intent.putExtra("judul", "Detail Data");
                intent.putExtra("jenis", "minuman");
                intent.putExtra("mode", "detail");
                Model.currentMinuman = minuman;
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (values == null){
            return 0;
        };
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        TextView brand;
        ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
            brand = itemView.findViewById(R.id.productBrand);
            delete = itemView.findViewById(R.id.deleteButton);
        }
    }
}
