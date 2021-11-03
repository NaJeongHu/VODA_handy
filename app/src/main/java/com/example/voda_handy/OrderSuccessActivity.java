package com.example.voda_handy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;

public class OrderSuccessActivity extends AppCompatActivity {

    private ImageView mIvBack;
    private TextView mTvStoreName, mTvMenuName;
    private Button mBtnToHome;

    private String storeName;
    private String menuName;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);

        Intent intent = getIntent();
        storeName = intent.getStringExtra("store");
        menuName = intent.getStringExtra("menu");
        init();
        sendToFirebase();
    }

    public void sendToFirebase() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("menus", menuName);
        hashMap.put("request", "고려사항 없음");
        hashMap.put("time", 40);
        hashMap.put("type", "매장");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("voda_handy")
                .child("Store").child("한식").child("AeUV4CEje9ZQgWSUFjV3ywO5Umk2")
                .child("OrderList").child("조리전");

        mDatabaseReference.orderByKey().limitToLast(1).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                HashMap<String, Object> value = (HashMap<String, Object>) task.getResult().getValue();
                Iterator<String> keys = value.keySet().iterator();
                String index = keys.next();
                int newIndex = Integer.parseInt(index) + 1;
                Log.d("orderDebug", "newIndex: " + newIndex);
                mDatabaseReference.child(newIndex + "").setValue(hashMap);
            }
        });
//        mDatabaseReference.push().setValue(hashMap);


    }

    public void init() {
        mIvBack = findViewById(R.id.btn_order_success_back);
        mTvStoreName = findViewById(R.id.tv_order_success);
        mTvMenuName = findViewById(R.id.tv_order_success_menu);
        mBtnToHome = findViewById(R.id.btn_order_success_home);

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderSuccessActivity.this, StoreListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mTvStoreName.setText(storeName + ", 주문이 완료되었습니다.");
        mTvMenuName.setText(menuName + " 주문");
    }
}