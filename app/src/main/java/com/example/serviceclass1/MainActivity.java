package com.example.serviceclass1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEtName;
    private Button mBtnSave;
    private Intent intent;

    private BroadcastReceiver serviceReceiver=new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,intent.getStringExtra("data"),Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        IntentFilter intentFilter=new IntentFilter("xyz.com.service");
        registerReceiver(serviceReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(serviceReceiver);
    }

    private void initViews() {
        mEtName=findViewById(R.id.etName);
        mBtnSave=findViewById(R.id.btnSave);

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 intent=new Intent(MainActivity.this,FileOperationService.class);
                intent.putExtra("name",mEtName.getText().toString());
                startService(intent);
            }
        });
    }
}