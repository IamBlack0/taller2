package com.example.taller2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taller2.entidades.Cuenta;

import java.util.List;

import kotlin.collections.ArrayDeque;

public class Transferir extends AppCompatActivity {

    TextView txtSaldo;
    EditText Suma;
    EditText Transferencia;
    Spinner banco;
    double saldoInicial;
    String nombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferir);
        this.InicializarControles();

        Intent intent = getIntent();
        nombreUsuario = intent.getStringExtra("usuario");
        saldoInicial = intent.getDoubleExtra("saldoFinal", 1000.00);  // Obtener el saldo inicial o actualizado

        txtSaldo.setText(String.format("%.2f", saldoInicial));
    }

    private void InicializarControles() {
        Suma = findViewById(R.id.txtLaSuma);
        Transferencia = findViewById(R.id.txtTransferencia);
        banco = findViewById(R.id.SpinnerBanco);
        txtSaldo = findViewById(R.id.txtSaldo);
        this.LlenarSpinner();
    }

    private List<String> ObtenerBanco() {
        List<String> bancos = new ArrayDeque<>();
        bancos.add("Banco Nacional de Panamá");
        bancos.add("Scotiabank");
        bancos.add("Banco General");
        bancos.add("Banistmo");
        bancos.add("BBVA");
        return bancos;
    }

    private void LlenarSpinner() {
        List<String> banco = this.ObtenerBanco();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, banco);
        this.banco.setAdapter(adapter);
    }

    public void GuardarTransferencia(View v) {
        try {
            Intent i = new Intent(this, Confirmar.class);
            if (banco.getSelectedItem() != null) {
                double suma = Double.parseDouble(Suma.getText().toString());
                if (saldoInicial >= suma) {
                    double saldoRestante = saldoInicial - suma;
                    Cuenta cuenta = new Cuenta(
                            "",
                            Transferencia.getText().toString(),
                            banco.getSelectedItem().toString(),
                            saldoRestante,
                            suma
                    );
                    i.putExtra("tipo", 2);
                    i.putExtras(cuenta.toBundle());
                    i.putExtra("usuario", nombreUsuario);
                    i.putExtra("saldoInicial", saldoInicial);
                    startActivityForResult(i, 1);
                } else {
                    Toast.makeText(this, "Saldo insuficiente", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Por favor, seleccione un banco", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Errorcito => " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                double saldoFinal = data.getDoubleExtra("saldoFinal", saldoInicial);
                saldoInicial = saldoFinal;
                txtSaldo.setText(String.format("%.2f", saldoInicial));
            }
        }
    }
}
