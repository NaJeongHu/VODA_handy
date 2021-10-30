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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StoreListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView mBackButton;
    private FloatingActionButton mFloatingActionButton;
    private ArrayList<StoreInfo> stores;
    private StoreRecyclerAdapter adapter;
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        init();
        addItemTouchListenerOnRecyclerView();
        getDataFromServer();
    }

    private void init() {

        mRecyclerView = findViewById(R.id.recyclerview_list);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mBackButton = findViewById(R.id.btn_list_back);
        mFloatingActionButton = findViewById(R.id.fb_tobasket);

        mBackButton.setOnClickListener(this);
        mFloatingActionButton.setOnClickListener(this);
        stores = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("voda_handy");
    }

    // when user touch item
    private void addItemTouchListenerOnRecyclerView() {

        mRecyclerView.addOnItemTouchListener(new RecyclerViewOnItemClickListener(getApplicationContext(), mRecyclerView,
                new RecyclerViewOnItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        intent.putExtra("idToken", stores.get(position).getIdToken());
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
        if (stores != null && stores.size() != 0) {
            stores.clear();
        }

        mDatabaseRef.child("Store").child("한식").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    // 반복문으로 데이터 LIST 추출
                    String idToken = snapshot.getKey();
                    mDatabaseRef.child("Store").child("한식").child(idToken).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                            for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                                StoreInfo storeInfo = snapshot.getValue(StoreInfo.class);
                                if (storeInfo.getStorename() != null) {
                                    storeInfo.setIdToken(idToken);
                                    stores.add(storeInfo);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 디비를 가져오던 중 에러 발생 시 에러문 출력
                Log.e("StoreListActivity", String.valueOf(error.toException()));
            }
        });

        adapter = new StoreRecyclerAdapter(stores, getApplicationContext());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_list_back:
                finish();
                break;

            case R.id.fb_tobasket:
                if(ShoppingList.getShoppingList().getMenus() == null) {
                    Toast.makeText(this, "메뉴를 선택해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(this, ShoppingBasketActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}