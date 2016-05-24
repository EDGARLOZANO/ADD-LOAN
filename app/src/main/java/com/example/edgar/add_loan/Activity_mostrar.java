package com.example.edgar.add_loan;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_mostrar extends AppCompatActivity {
    ArrayList<ListView> che;
    LinearLayout layin;
    ImageButton abrir;
    ListViewAdapter adapter;

    String[] l= null;
    int[] ima=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        abrir=(ImageButton) findViewById(R.id.imageButton);

        Conexion2 bd;



        che= new ArrayList<ListView>();
        bd = new Conexion2(this, "Prestamos", null, 1);

        String[] matriz = null;


       // layin= (LinearLayout) findViewById(R.id.layo);

        try {
            SQLiteDatabase base = bd.getReadableDatabase();
            String sql = "SELECT * FROM TablaPrestamo";

            Cursor cursor = base.rawQuery(sql, null);
            matriz = new String[cursor.getCount()];
            l = new String[cursor.getCount()];
            ima= new int[cursor.getCount()];
            int i = 0;
            if (cursor.moveToFirst()) {
                do {
                    matriz[i] = cursor.getString(0);

                    //ListView c= new ListView(this);
                    l[i]=cursor.getString(1)+"'("+cursor.getString(3)+")'";

                    ima[i]= R.drawable.azul;

                    String checar=cursor.getString(7);

                    if(checar.equals("P")){

                        ima[i]= R.drawable.azul;}
                    else{

                        ima[i]=R.drawable.agregar;

                    }


                    i++;





                        //che.add(c);
                       //layin.addView(lista);
                      Toast.makeText(Activity_mostrar.this,"-entra1", Toast.LENGTH_LONG).show();




                } while (cursor.moveToNext());
                //         SimpleCursorAdapter adp1 = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item,cursor,new String[] {"DESC"},    new int[] {android.R.id.text1},0);
                //       materias.setAdapter(adp1);
            } else {
                Toast.makeText(Activity_mostrar.this,"no existe", Toast.LENGTH_LONG).show();

            }
            cursor.close();
            base.close();
        } catch (SQLiteException sqle) {
            //imprime el error
        }

        final ListView lista = (ListView) findViewById(R.id.listView);
        adapter = new ListViewAdapter(this, l, ima);
        lista.setAdapter(adapter);
        //layin.addView(lista);
    }
}
