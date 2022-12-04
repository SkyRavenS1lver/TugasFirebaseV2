package com.example.myapplication.TabMinuman.Activity;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataModel.Model;
import com.example.myapplication.GeneralActivity.ProductDetail;
import com.example.myapplication.TabMinuman.MinumanAdapter;
import com.example.myapplication.R;
import com.example.myapplication.TabPackage.TabActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ReadDataMinuman extends Fragment {
    public static TextView message;
    FloatingActionButton addData;
    MinumanAdapter adapter;

    public ReadDataMinuman() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_read_data, container, false);
        message = view.findViewById(R.id.messages);
//        ((TextView)container.findViewById(R.id.judul)).setText("List Minuman");
        TabActivity.rvMinuman = view.findViewById(R.id.rvProduct);
        addData = view.findViewById(R.id.addData);
        dataChange();
        adapter = new MinumanAdapter(view.getContext(), Model.minumanArrayList);
        TabActivity.rvMinuman.setLayoutManager(new LinearLayoutManager(view.getContext()));
        TabActivity.rvMinuman.setAdapter(adapter);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProductDetail.class);
                intent.putExtra("mode", "add");
                intent.putExtra("jenis", "minuman");
                intent.putExtra("judul", "Tambah Data Minuman");
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
       if (Model.minumanArrayList.size() > 0){
           message.setVisibility(View.INVISIBLE);
       }
       else {message.setVisibility(View.VISIBLE);}
    }
    public static MinumanAdapter getRvAdaper(){
        return (MinumanAdapter) TabActivity.rvMinuman.getAdapter();
    }
    public static void refreshList(){
        getRvAdaper().notifyDataSetChanged();
        dataChange();
    }

}
