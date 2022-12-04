package com.example.myapplication.TabMakanan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.DataModel.Model;
import com.example.myapplication.Firebase.FirebaseController;
import com.example.myapplication.GeneralActivity.ProductDetail;
import com.example.myapplication.TabMakanan.MakananAdapter;
import com.example.myapplication.R;
import com.example.myapplication.TabPackage.TabActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReadDataMakanan extends Fragment {
    public static TextView message;
    FloatingActionButton addData;
    MakananAdapter adapter;

    public ReadDataMakanan() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_read_data, container, false);
        message = view.findViewById(R.id.messages);
//        ((TextView)view.findViewById(R.id.judul)).setText("List Minuman");
        TabActivity.rvMakanan = view.findViewById(R.id.rvProduct);
        addData = view.findViewById(R.id.addData);
        dataChange();
        adapter = new MakananAdapter(view.getContext(), Model.makananArrayList);
        TabActivity.rvMakanan.setLayoutManager(new LinearLayoutManager(view.getContext()));
        TabActivity.rvMakanan.setAdapter(adapter);


        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProductDetail.class);
                intent.putExtra("mode", "add");
                intent.putExtra("jenis", "makanan");
                intent.putExtra("judul", "Tambah Data Makanan");
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
   public static void dataChange() {
       if (Model.makananArrayList.size() > 0){
           message.setVisibility(View.INVISIBLE);
       }
       else {message.setVisibility(View.VISIBLE);}
    }
    public static MakananAdapter getRvAdaper(){
        return (MakananAdapter) TabActivity.rvMakanan.getAdapter();
    }
    public static void refreshList(){
        getRvAdaper().notifyDataSetChanged();
        dataChange();
    }

}
