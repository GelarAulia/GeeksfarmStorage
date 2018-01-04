package com.gelaraulia.geeksfarmstorage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreferencesActivity extends AppCompatActivity {
    SharedPreferences set_shared_preference;
    SharedPreferences.Editor sp_editor;

    TextView tv_firstToken;
    EditText et_secondToken;
    Button btn_setToken, btn_toSP2, btn_toSP3;
    String token1, token2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        token1 = "fd@3jfD83#dfaksdfweqoru#LEWlkj";
        set_shared_preference = getSharedPreferences("auth",MODE_PRIVATE);
        sp_editor = set_shared_preference.edit();
        sp_editor.putString("token_auth", token1);
        sp_editor.commit();

        tv_firstToken = (TextView)findViewById(R.id.tv_tokenFirst);
        tv_firstToken.setText(token1);

        et_secondToken = (EditText)findViewById(R.id.et_tokenSecond);

        btn_setToken = (Button)findViewById(R.id.btn_setSecondToken);
        btn_setToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                token2 = et_secondToken.getText().toString();
                sp_editor.putString("token_auth2", token2);
                sp_editor.commit();
            }
        });

        btn_toSP2 = (Button)findViewById(R.id.btn_SP2);
        btn_toSP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SharedPreferencesActivity.this, SharedPreferences2Activity.class);
                startActivity(intent);
            }
        });
        btn_toSP3 = (Button)findViewById(R.id.btn_SP3);
        btn_toSP3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SharedPreferencesActivity.this, SharedPreference3Activity.class);
                startActivity(intent);
            }
        });
    }
}
