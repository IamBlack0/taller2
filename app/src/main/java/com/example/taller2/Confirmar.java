package com.example.taller2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taller2.entidades.Cuenta;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class Confirmar extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar);


        //------------------PARA LA FECHA Y HORA--------------------
        TextView dateTimeTextView = findViewById(R.id.date_time);
        updateDateTime(dateTimeTextView);
        //--------------------------------------

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Cuenta cuenta = Cuenta.toClass(bundle);
            TextView txtNombreCuenta = findViewById(R.id.txtNombreCuenta);
            TextView txtNombreBanco = findViewById(R.id.txtNombreBanco);
            TextView txtTranferir = findViewById(R.id.txtTranferir);
            txtNombreCuenta.setText(cuenta.getTransferencia());
            txtNombreBanco.setText(cuenta.getBanco());
            txtTranferir.setText(String.format("%.2f", cuenta.getSuma()));
        }
    }

    public void ConfirmarTransacion(View v) {
        try {
            Intent i = new Intent(this, Exito.class);
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                i.putExtras(new Bundle(extras));
                startActivity(i);
                Toast.makeText(this, "Datos enviados correctamente.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error: No hay datos para enviar.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al enviar datos: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



    private void updateDateTime(TextView textView) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());
        textView.setText(currentDateAndTime);
    }
}
