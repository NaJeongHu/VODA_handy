package com.example.voda_handy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView mBackButton;
    private FloatingActionButton mFbBasket;
    private ArrayList<Menu> menu;
    private MenuRecyclerAdapter adapter;
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private String idToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
        addItemTouchListenerOnRecyclerView();
        getDataFromServer();
    }

    private void init() {

        Intent intent = getIntent();
        idToken = intent.getStringExtra("idToken");

        mRecyclerView = findViewById(R.id.recyclerview_list);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mBackButton = findViewById(R.id.btn_list_back);
        mFbBasket = findViewById(R.id.fb_tobasket);

        mFbBasket.setOnClickListener(this);
        mBackButton.setOnClickListener(this);
        menu = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("voda_handy");

    }

    // when user touch item
    private void addItemTouchListenerOnRecyclerView() {

        mRecyclerView.addOnItemTouchListener(new RecyclerViewOnItemClickListener(getApplicationContext(), mRecyclerView,
                new RecyclerViewOnItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(getApplicationContext(), DetailedMenuActivity.class);
                        intent.putExtra("menu", menu.get(position));
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    }

                    @Override
                    public void onItemLongClick(View v, int position) {

                    }
                }));
    }

    private void getDataFromServer() {
        // TODO : 파이어베이스 연동
        if (menu != null && menu.size() != 0) {
            menu.clear();
        }

        mDatabaseRef.child("Store").child("한식").child(idToken).child("MenuInfo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    String menuname = snapshot.getKey();
                    Menu menu1 = snapshot.getValue(Menu.class);
                    if (menu1.getExplanation() != null) {
                        menu1.setMenuname(menuname);
                        menu.add(menu1);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new MenuRecyclerAdapter(menu, getApplicationContext());
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_list_back:
                finish();
                break;

            case R.id.fb_tobasket:
                Intent intent = new Intent(this, ShoppingBasketActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}