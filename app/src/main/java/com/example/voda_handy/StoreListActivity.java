package com.example.voda_handy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StoreListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ImageView mBackButton;
    private ArrayList<Store> stores;
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
        mBackButton = findViewById(R.id.btn_list_back);

        mBackButton.setOnClickListener(this);
        stores = new ArrayList<>();

    }

    // when user touch item
    private void addItemTouchListenerOnRecyclerView() {

        mRecyclerView.addOnItemTouchListener(new RecyclerViewOnItemClickListener(getApplicationContext(), mRecyclerView,
                new RecyclerViewOnItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        intent.putExtra("idToken", stores.get(position).getIdtoken().toString());
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

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("voda_handy");
        mDatabaseRef.child("Store").child("한식").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    for (DataSnapshot dataSnapshot : task.getResult().getChildren()) {
                        Store store = dataSnapshot.getValue(Store.class);
//                        store store = task.getResult().getValue(store.class);
                        if (store != null) {
                            stores.add(store);
                        }
                    }
                }
            }
        });

//        connectToAdapter();
    }

    private void connectToAdapter() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (stores != null || stores.isEmpty() == false || stores.size() != 0) {
//                            Collections.sort(arr,new Filtering_for_ganada());
                            adapter = new StoreRecyclerAdapter(stores, getApplicationContext());
                            mRecyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
//                        base_progressBar.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View view) {

    }
}