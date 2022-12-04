package com.example.myapplication.Firebase;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.myapplication.TabMakanan.Activity.ReadDataMakanan;
import com.example.myapplication.TabMinuman.Activity.ReadDataMinuman;
import com.example.myapplication.TabMinuman.Minuman;
import com.example.myapplication.DataModel.Model;
import com.example.myapplication.TabMakanan.Makanan;
import com.example.myapplication.TabPackage.TabActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseController extends Application {
//    private String keyColumn = getString(R.string.productKey);

    private FirebaseDatabase database;
    public static DatabaseReference makananReferences;
    public static DatabaseReference minumanReferences;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        makananReferences = database.getReference(Makanan.class.getSimpleName());
        minumanReferences = database.getReference(Minuman.class.getSimpleName());
        getAllProduct();
    }

    public static void insertData(Makanan makanan){
        makananReferences.push().setValue(makanan);
    }
    public static void deleteData(Makanan makanan){
        makananReferences.child(makanan.getKey()).removeValue();
    }
    public static void updateData(Makanan makanan){
        makananReferences.child(makanan.getKey()).setValue(Makanan.convertMakanan(makanan));
    }

    public static void updateData(Minuman minuman){
        minumanReferences.child(minuman.getKey()).setValue(Minuman.convertMinuman(minuman));
    }
    public static void insertData(Minuman minuman){
        minumanReferences.push().setValue(minuman);
    }
    public static void deleteData(Minuman minuman){
        minumanReferences.child(minuman.getKey()).removeValue();
    }

    public void getAllProduct(){
        makananReferences.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Model.makananArrayList.clear();
                if (snapshot.hasChildren()){
                    for (DataSnapshot currentData : snapshot.getChildren()){
                        Makanan makanan = currentData.getValue(Makanan.class);
                        makanan.setKey(currentData.getKey());
                        Model.makananArrayList.add(makanan);
                    }
                }
                if(TabActivity.rvMakanan != null){
                    ReadDataMakanan.refreshList();}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        minumanReferences.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Model.minumanArrayList.clear();
                if (snapshot.hasChildren()){
                    for (DataSnapshot currentData : snapshot.getChildren()){
                        Minuman minuman = currentData.getValue(Minuman.class);
                        minuman.setKey(currentData.getKey());
                        Model.minumanArrayList.add(minuman);
                    }
                }
                if(TabActivity.rvMinuman != null){
                ReadDataMinuman.refreshList();}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
