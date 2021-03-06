package com.example.edgar.add_loan;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class v_agregar extends AppCompatActivity {

   ListView abrir;
    Conexion2 bd;
    ImageButton insert;
    EditText txt1,txt2,txt3,txt4;
    DatePicker date;
    String fechaCadena="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_agregar);
        abrir=(ListView) findViewById(R.id.listView1);
        bd= new Conexion2(this,"Prestamos",null,1);
        txt1=(EditText) findViewById(R.id.editText);
        txt2=(EditText) findViewById(R.id.editText2);
        txt3=(EditText) findViewById(R.id.editText4);
        txt4=(EditText) findViewById(R.id.editText3);
        date=(DatePicker) findViewById(R.id.datePicker);

    }
    public void enviar (View v){

        String fep="";
        fep += date.getYear();
         int n=date.getMonth();
        int x=date.getDayOfMonth();
        n++;
        if (n >= 10) {
            fep += "/" + n;
        } else {
            fep += "/0" + n;
        }
        if (x >= 10) {
            fep += "/" + x;
        } else {
            fep += "/0" + x;
        }
























        try {




            SQLiteDatabase base= bd.getWritableDatabase();
            String SQL= "INSERT INTO TablaPrestamo VALUES(NULL,'"+txt1.getText()+"','"+txt2.getText()+"','"+txt3.getText()+
                    "',"+txt4.getText()+",'"+fechaActual()+"'"+",'"+fep+"','P')";
            base.execSQL(SQL);
            base.close();
            Toast.makeText(v_agregar.this,"Se inserto correctamente",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(v_agregar.this, Activity_mostrar.class));
            finish();


        }
        catch (SQLException e){

            Toast.makeText(v_agregar.this,e.getMessage()+"Existen campos vacios",Toast.LENGTH_SHORT).show();

        }

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

    /**
     * Created by edgar on 6/05/16.
     */

}
