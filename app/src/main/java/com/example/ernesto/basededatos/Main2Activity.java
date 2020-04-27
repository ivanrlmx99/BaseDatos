package com.example.ernesto.basededatos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private ListView lv1;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lv1 = (ListView) findViewById(R.id.lv);
        btn=(Button) findViewById(R.id.btn_mostrar);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar();


            }
        });
    }


    public void mostrar(){
        ArrayList<String> ranking = new ArrayList<>();

        AdminSQLiteOpenHelper juego = new  AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = juego.getWritableDatabase();
        Cursor fila = bd.rawQuery("select codigo,descripcion, precio from articulos", null);
        if(fila.moveToFirst()){
            do{
                ranking.add(fila.getString(0) + " - " + fila.getString(1)+"-"+ fila.getString(2));

            }while(fila.moveToNext());
        }
        bd.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ranking);
        lv1.setAdapter(adapter);
        System.out.println(""+adapter);
    }
}
