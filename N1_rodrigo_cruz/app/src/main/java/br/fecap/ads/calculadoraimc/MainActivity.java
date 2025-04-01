package br.fecap.ads.calculadoraimc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btnSet;
    private Button btnReset;
    private EditText campoAltura;
    private EditText campoPeso;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnSet = findViewById(R.id.btnSet);
        btnReset = findViewById(R.id.btnReset);
        campoAltura = findViewById(R.id.editTextAltura);
        campoPeso = findViewById(R.id.editTextPeso);
        textResultado = findViewById(R.id.textResultado);


        btnReset.setOnClickListener(v -> {
            campoAltura.setText("");
            campoPeso.setText("");
            textResultado.setText("");
        });
    }

    public void calculaIMC(View view) {

        String alturaStr = campoAltura.getText().toString();
        String pesoStr = campoPeso.getText().toString();


        if (alturaStr.isEmpty() || pesoStr.isEmpty()) {
            textResultado.setText("Preencha todos os campos!");
            return;
        }


        double numAltura = Double.parseDouble(alturaStr);
        double numPeso = Double.parseDouble(pesoStr);
        double numImc = numPeso / (numAltura * numAltura);


        DecimalFormat df = new DecimalFormat("##.##");
        String imcFormatado = df.format(numImc);


        String classificacao;
        if (numImc < 18.5) {
            classificacao = "Abaixo do peso";
        } else if (numImc < 25) {
            classificacao = "Peso normal";
        } else if (numImc < 30) {
            classificacao = "Sobrepeso";
        } else if (numImc < 35) {
            classificacao = "Obesidade grau 1";
        } else if (numImc < 40) {
            classificacao = "Obesidade grau 2";
        } else {
            classificacao = "Obesidade grau 3";
        }


        textResultado.setText(imcFormatado + " kg/m²");


        if (numImc < 18.5) {
            Intent intent = new Intent(this, AbaixoDoPesoActivity.class);
            intent.putExtra("IMC", imcFormatado);
            intent.putExtra("CLASSIFICACAO", classificacao);
            startActivity(intent);
        }
    }
}