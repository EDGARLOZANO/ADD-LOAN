package com.example.edgar.add_loan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Principal extends AppCompatActivity{

    ListViewAdapter adapter;

    String[] titulo = new String[]{
            "Agregar",
            "Mostrar",
            "Registros",};

    int[] imagenes = {
            R.drawable.agregar,
            R.drawable.buscar,
            R.drawable.regis,};

    String[] l= null;
    int[] ima=null;
    Conexion2 bd;

    String fechaCadena="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        final ListView lista = (ListView) findViewById(R.id.listView1);
        adapter = new ListViewAdapter(this, titulo, imagenes);
        lista.setAdapter(adapter);

        actua();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        startActivity(new Intent(Principal.this, v_agregar.class));
                        break;
                    case 1:
                        startActivity(new Intent(Principal.this, Activity_mostrar.class));
                        break;

                }


            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "presiono LARGO " + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    public void actua() {


        bd = new Conexion2(this, "Prestamos", null, 1);

        String[] matriz = null;


        try {
            SQLiteDatabase base = bd.getReadableDatabase();
            String sql = "SELECT * FROM TablaPrestamo";

            Cursor cursor = base.rawQuery(sql, null);
            matriz = new String[cursor.getCount()];
            l = new String[cursor.getCount()];
            ima = new int[cursor.getCount()];
            int i = 0;
            if (cursor.moveToFirst()) {
                do {
                    matriz[i] = cursor.getString(0);


                    l[i] = cursor.getString(1) + "(" + cursor.getString(3) + ")";

                    l[i]=cursor.getString(1)+"("+cursor.getString(3)+")";
                    String f=cursor.getString(6);
                    String [] Arrayf=f.split("/");
                    String [] Arrayf2=fechaActual().split("/");

                    int a=validar(Arrayf[0],Arrayf[1],Arrayf[2],Arrayf2[0],Arrayf2[1],Arrayf2[2]);

                    if(a==1){
                           int t=i+1;
                        SQLiteDatabase base1 = bd.getWritableDatabase();
                        String SQL ="UPDATE TablaPrestamo SET estado='A' WHERE id=" + t +"";
                        base1.execSQL(SQL);
                        base1.close();

                    }
                    i++;


                } while (cursor.moveToNext());

            } else {
                Toast.makeText(Principal.this, "no existe", Toast.LENGTH_LONG).show();

            }
            cursor.close();
            base.close();
        } catch (SQLiteException sqle) {
            //imprime el error
        }

    }

    public int validar(String año,String mes, String dia,String año2,String mes2, String dia2){

        int a=Integer.parseInt(año);
        int m=Integer.parseInt(mes);
        int d=Integer.parseInt(dia);
        int a1=Integer.parseInt(año2);
        int m1=Integer.parseInt(mes2);
        int d1=Integer.parseInt(dia2);

        if(a>=a1 && m>=m1 && d>d1){


            Toast.makeText(Principal.this,"teamo",Toast.LENGTH_LONG).show();

            return 1;



        }

        Toast.makeText(Principal.this,"teamo2",Toast.LENGTH_LONG).show();
        return  0;


    }



    private String fechaActual(){
        String fechaActual="";
        Calendar c = GregorianCalendar.getInstance();
        int año = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH)+1;
        int dia = c.get(Calendar.DAY_OF_MONTH);

        fechaCadena=año+"/";
        if(mes>=10){
            fechaCadena+=mes+"/";
        }else{
            fechaCadena +="0"+mes+"/";
        }
        if(dia>=10){
            fechaCadena +=dia;
        }else{
            fechaCadena +="0"+dia;
        }
        return fechaCadena;
    }

}
