package com.example.edgar.add_loan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

public class v_agregar extends AppCompatActivity {

   ListView abrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_agregar);
        abrir=(ListView) findViewById(R.id.listView1);
   }
}
