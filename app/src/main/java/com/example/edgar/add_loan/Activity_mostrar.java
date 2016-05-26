package com.example.edgar.add_loan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class Activity_mostrar extends AppCompatActivity {

    LinearLayout layin;
    ImageButton abrir;
    ListViewAdapter adapter;
    Conexion2 bd;
    AlertDialog levelDialog;
    final CharSequence[] items = {"  Entregado ","  Eliminar"};

    String[] l= null;
    int[] ima=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        abrir=(ImageButton) findViewById(R.id.imageButton);





        bd = new Conexion2(this, "Prestamos", null, 1);

        String[] matriz = null;



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
                    l[i]=cursor.getString(1)+"("+cursor.getString(3)+")";



                    String checar=cursor.getString(7);

                    if(checar.equals("P")){

                        ima[i]= R.drawable.azul;}
                   if(checar.equals("A")){

                        ima[i]=R.drawable.rojo; }

                    if(checar.equals("D")){

                        ima[i]=R.drawable.verde;

                          }


                    i++;






                } while (cursor.moveToNext());

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

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                //    Toast.makeText(Activity_mostrar.this,"UPDATE TablaPretasmo SET estado ='D' WHERE id="+(i+1)+"",Toast.LENGTH_LONG).show();

             caso(i);



                return false;

            }
        });




    }

   public void actua(){


       bd = new Conexion2(this, "Prestamos", null, 1);

       String[] matriz = null;



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


                   l[i]=cursor.getString(1)+"("+cursor.getString(3)+")";



                   String checar=cursor.getString(7);

                   if(checar.equals("P")){

                       ima[i]= R.drawable.azul;}
                   if(checar.equals("A")){

                       ima[i]=R.drawable.rojo; }

                   if(checar.equals("D")){

                       ima[i]=R.drawable.verde;

                   }


                   i++;






               } while (cursor.moveToNext());

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


   }


int y=0;
    public void caso(int c){
         y=c;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Opciones:");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {


                switch(item)
                {
                    case 0:
                        try {



                            int t=y+1;
                            SQLiteDatabase base = bd.getWritableDatabase();
                            String SQL ="UPDATE TablaPrestamo SET estado='D' WHERE id=" + t + "";
                            base.execSQL(SQL);
                            base.close();
                            actua();
                        }
                        catch (SQLException e){

                            Toast.makeText(Activity_mostrar.this,e.getMessage(),Toast.LENGTH_LONG).show();

                        }

                        break;
                    case 1:

                        try {



                            int t=y+1;
                            SQLiteDatabase base = bd.getWritableDatabase();
                            String SQL ="DELETE FROM TablaPrestamo  WHERE id=" + t + "";
                            base.execSQL(SQL);
                            base.close();
                            actua();
                        }
                        catch (SQLException e){

                            Toast.makeText(Activity_mostrar.this,e.getMessage(),Toast.LENGTH_LONG).show();

                        }

                        break;




                }
                levelDialog.dismiss();
            }
        });
        levelDialog = builder.create();
        levelDialog.show();




    }

    public int validar(String año,String mes, String dia,String año2,String mes2, String dia2){


        return  1;


    }


}

