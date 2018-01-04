package com.gelaraulia.geeksfarmstorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SharedPreferences2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences2);

        SharedPreferences get_shared_preference = getSharedPreferences("auth", MODE_PRIVATE);
        TextView tv_tokenAuth = (TextView)findViewById(R.id.tv_tokenAuth);
        tv_tokenAuth.setText(get_shared_preference.getString("token_auth",""));
    }
}
