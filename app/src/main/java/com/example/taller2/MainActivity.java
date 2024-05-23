package com.example.taller2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText usuario, contra;

    RadioButton rbtCaptcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InicializarControles();
    }

    private void InicializarControles() {
        usuario = findViewById(R.id.txtNombreUsuario);
        contra = findViewById(R.id.txtContra);
        rbtCaptcha = findViewById(R.id.rbtCaptcha);
    }

    public void GuardarCuenta(View v) {
        try {
            if (rbtCaptcha.isChecked()) {
                Intent i = new Intent(this, Transferir.class);
                String nombreUsuario = usuario.getText().toString();
                i.putExtra("usuario", nombreUsuario);
                i.putExtra("saldoInicial", 1000.00);
                startActivity(i);
            } else {
                Toast.makeText(this, "Por favor, seleccione el CAPTCHA.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Errorcito => " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
