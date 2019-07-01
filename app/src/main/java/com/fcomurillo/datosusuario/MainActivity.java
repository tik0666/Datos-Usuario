package com.fcomurillo.datosusuario;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView tvFecha;
    EditText txtNombre;
    EditText txtTelefono;
    EditText txtEmail;
    EditText txtDescripcion;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtEmail = findViewById(R.id.txtEmail);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        tvFecha = findViewById(R.id.tvFecha);

        tvFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month += 1;

                String date = day + "/" + month + "/" + year;
                tvFecha.setText(date);
            }
        };

    }

    public void siguiente(View v){
        Intent intent = new Intent(MainActivity.this, ConfirmarDatos.class);
        intent.putExtra(getResources().getString(R.string.pNombreC), txtNombre.getText().toString());
        intent.putExtra(getResources().getString(R.string.pFechaN), tvFecha.getText().toString());
        intent.putExtra(getResources().getString(R.string.pTelefono), txtTelefono.getText().toString());
        intent.putExtra(getResources().getString(R.string.pEmail), txtEmail.getText().toString());
        intent.putExtra(getResources().getString(R.string.pDescripcion), txtDescripcion.getText().toString());
        startActivityForResult(intent, 1);
        //startActivity(intent);
        //finish();
    }
}
