package com.gelaraulia.geeksfarmstorage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener{
    DatabaseHelper myDB;

    EditText et_name, et_publisher, et_price, et_id;
    Button btn_save, btn_list, btn_update, btn_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        myDB = new DatabaseHelper(this);

        et_name = (EditText)findViewById(R.id.et_gameName);
        et_publisher = (EditText)findViewById(R.id.et_gamePublish);
        et_price = (EditText)findViewById(R.id.et_gamePrice);
        et_id = (EditText)findViewById(R.id.et_gameId);

        btn_save = (Button)findViewById(R.id.btn_gameSave);
        btn_list = (Button)findViewById(R.id.btn_gameList);
        btn_update = (Button)findViewById(R.id.btn_gameUpdate);
        btn_delete = (Button)findViewById(R.id.btn_gameDel);

        btn_save.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_gameSave:
                boolean res = myDB.save_game(
                        et_name.getText().toString(),
                        et_publisher.getText().toString(),
                        et_price.getText().toString());
                if(res){
                    Toast.makeText(SQLiteActivity.this,"Success Add Game",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SQLiteActivity.this,"Failed Add Game",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_gameList:
                Cursor games = myDB.list_game();
                if(games.getCount() == 0){
                    alert_message("Mesaage","No data game found");
                    return;
                }
                StringBuffer sb = new StringBuffer();
                while(games.moveToNext()){
                    sb.append("Id : "+games.getString(0)+"\n");
                    sb.append("Name : "+games.getString(1)+"\n");
                    sb.append("Publisher : "+games.getString(2)+"\n");
                    sb.append("Price : "+games.getString(3)+"\n");
                }
                alert_message("Games List",sb.toString());
                break;
            case R.id.btn_gameUpdate:
                boolean res2 = myDB.update_game(
                        et_id.getText().toString(),
                        et_name.getText().toString(),
                        et_publisher.getText().toString(),
                        et_price.getText().toString());
                if(res2){
                    Toast.makeText(SQLiteActivity.this,"Success Update Game",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SQLiteActivity.this,"Failed Update Game",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_gameDel:
                Integer res3 = myDB.delete_game(et_id.getText().toString());
                if(res3 > 0){
                    Toast.makeText(SQLiteActivity.this,"Success Delete Game",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SQLiteActivity.this,"Failed Update Game",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void alert_message(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
