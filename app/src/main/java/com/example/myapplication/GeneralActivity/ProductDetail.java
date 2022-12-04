package com.example.myapplication.GeneralActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DataModel.Model;
import com.example.myapplication.Firebase.FirebaseController;
import com.example.myapplication.TabMakanan.Makanan;
import com.example.myapplication.TabMinuman.Minuman;
import com.example.myapplication.R;
import com.example.myapplication.TabPackage.TabActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class ProductDetail extends AppCompatActivity {
    FloatingActionButton edit,confirm,cancel,back;
    EditText name,brand,price,description;
    TextView judul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        back = findViewById(R.id.backButton);
        edit = findViewById(R.id.editData);
        confirm = findViewById(R.id.confirmButton);
        cancel = findViewById(R.id.cancelButton);
        name = findViewById(R.id.productName);
        brand = findViewById(R.id.productBrand);
        price = findViewById(R.id.productPrice);
        description = findViewById(R.id.productContent);
        Intent getter = getIntent();
        Intent intent = new Intent(ProductDetail.this, TabActivity.class);
        judul = findViewById(R.id.Judul);
        judul.setText(getter.getStringExtra("judul"));
        String mode = getter.getStringExtra("mode");
        String jenis = getter.getStringExtra("jenis");
        if(jenis.equals("makanan")){
        Makanan makanan = Model.currentMakanan;
            description.setVisibility(View.VISIBLE);

            if(mode.equals("detail")) {
            changeState(false);
            String pName = makanan.getName();
            String pBrand = makanan.getBrand();
            String pPrice = String.valueOf(makanan.getPrice());
            String pDesc = makanan.getDesc();
            name.setText(pName);
            brand.setText(pBrand);
            price.setText(pPrice);
            description.setText(pDesc);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    judul.setText("Edit Data");
                    changeState(true);
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            makanan.setName(name.getText().toString());
                            makanan.setPrice(Integer.parseInt(price.getText().toString()));
                            makanan.setBrand(brand.getText().toString());
                            makanan.setDesc(description.getText().toString());
                            FirebaseController.updateData(makanan);
                            changeState(false);
                            judul.setText("Detail Data");

                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            changeState(false);
                            name.setText(makanan.getName());
                            brand.setText(makanan.getBrand());
                            price.setText(String.valueOf(makanan.getPrice()));
                            description.setText(makanan.getDesc());
                            judul.setText("Detail Data");
                        }
                    });
                }
            });
        }
        else if(mode.equals("add")){
            changeState(true);
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Makanan newMakanan = new Makanan();
                    newMakanan.setName(name.getText().toString());
                    newMakanan.setPrice(Integer.parseInt(price.getText().toString()));
                    newMakanan.setBrand(brand.getText().toString());
                    newMakanan.setDesc(description.getText().toString());
                    FirebaseController.insertData(newMakanan);
                    startActivity(intent);
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }
        }
        if(jenis.equals("minuman")){
            Minuman minuman = Model.currentMinuman;
            description.setVisibility(View.INVISIBLE);
        if(mode.equals("detail")) {
            changeState(false);
            String pName = minuman.getName();
            String pBrand = minuman.getBrand();
            String pPrice = String.valueOf(minuman.getPrice());
            name.setText(pName);
            brand.setText(pBrand);
            price.setText(pPrice);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    judul.setText("Edit Data");
                    changeState(true);
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            minuman.setName(name.getText().toString());
                            minuman.setPrice(Integer.parseInt(price.getText().toString()));
                            minuman.setBrand(brand.getText().toString());
                            FirebaseController.updateData(minuman);
                            changeState(false);
                            judul.setText("Detail Data");

                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            changeState(false);
                            name.setText(minuman.getName());
                            brand.setText(minuman.getBrand());
                            price.setText(String.valueOf(minuman.getPrice()));
                            judul.setText("Detail Data");
                        }
                    });
                }
            });
        }
        else if(mode.equals("add")){
            changeState(true);
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Minuman newMinuman = new Minuman();
                    newMinuman.setName(name.getText().toString());
                    newMinuman.setPrice(Integer.parseInt(price.getText().toString()));
                    newMinuman.setBrand(brand.getText().toString());
                    FirebaseController.insertData(newMinuman);
                    startActivity(intent);

                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }
    }
    }
    public void changeState(Boolean bool){
        if (bool){
            edit.setVisibility(View.INVISIBLE);
            back.setVisibility(View.INVISIBLE);
            confirm.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.VISIBLE);
        }
        else{
            edit.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
            confirm.setVisibility(View.INVISIBLE);
            cancel.setVisibility(View.INVISIBLE);
        }
        name.setFocusable(bool);
        name.setFocusableInTouchMode(bool);
        brand.setFocusable(bool);
        brand.setFocusableInTouchMode(bool);
        price.setFocusable(bool);
        price.setFocusableInTouchMode(bool);
        description.setFocusable(bool);
        description.setFocusableInTouchMode(bool);
    }
}
