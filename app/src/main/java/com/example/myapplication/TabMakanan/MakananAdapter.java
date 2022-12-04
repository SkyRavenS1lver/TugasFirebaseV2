package com.example.myapplication.TabMakanan;

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
import com.example.myapplication.TabMakanan.Activity.ReadDataMakanan;
import com.example.myapplication.Firebase.FirebaseController;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.ViewHolder> {
    ArrayList<Makanan> values;
    private final LayoutInflater inflater;

    public MakananAdapter(@NonNull Context context, ArrayList<Makanan> values) {
        this.values = values;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Makanan makanan =values.get(position);
        holder.brand.setText(makanan.getBrand());
        holder.name.setText(makanan.getName());
        holder.price.setText(String.valueOf(makanan.getPrice()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.itemView.getContext(),"Makanan "+ makanan.getName()+" Terhapus", Toast.LENGTH_SHORT).show();
                Model.makananArrayList.remove(makanan);
                ReadDataMakanan.refreshList();
                FirebaseController.deleteData(makanan);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(holder.itemView.getContext(), ProductDetail.class);
                intent.putExtra("judul", "Detail Data");
                intent.putExtra("jenis", "makanan");
                intent.putExtra("mode", "detail");
                Model.currentMakanan = makanan;
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
