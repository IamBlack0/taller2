package com.example.taller2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taller2.entidades.Cuenta;

public class Exito extends AppCompatActivity {

    private double saldoFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exito);

        Intent intent = getIntent();
        if (intent != null) {
            String nombreUsuario = intent.getStringExtra("usuario");
            TextView txtUsuario = findViewById(R.id.txtUsuario);
            if (nombreUsuario != null && !nombreUsuario.isEmpty()) {
                txtUsuario.setText(nombreUsuario);
            } else {
                txtUsuario.setText("Usuario no disponible");
            }

            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Cuenta cuenta = Cuenta.toClass(bundle);
                if (cuenta != null) {
                    TextView txtSaldoFinal = findViewById(R.id.txtSaldoFinal);
                    TextView txtNombreCuenta1 = findViewById(R.id.txtNombreCuenta1);
                    TextView txtSumaFinal = findViewById(R.id.txtSumaFinal);
                    txtNombreCuenta1.setText(cuenta.getTransferencia());
                    txtSumaFinal.setText(String.format("%.2f", cuenta.getSuma()));
                    txtSaldoFinal.setText(String.format("%.2f", cuenta.getSaldo()));
                    saldoFinal = cuenta.getSaldo();
                } else {
                    Toast.makeText(this, "Error al deserializar los datos de la cuenta.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "No se recibieron datos de la cuenta.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void Regresar(View v) {
        try {
            Intent i = new Intent(this, Transferir.class);
            i.putExtra("saldoFinal", saldoFinal);
            i.putExtra("usuario", getIntent().getStringExtra("usuario"));
            startActivity(i);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Errorcito => " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
