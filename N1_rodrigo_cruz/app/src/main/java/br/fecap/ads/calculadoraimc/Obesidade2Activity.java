package br.fecap.ads.calculadoraimc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Obesidade2Activity extends AppCompatActivity {

    private TextView textResultadoIMC;
    private TextView textClassificacao;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obesidade2);

        textResultadoIMC = findViewById(R.id.textResultadoIMC);
        textClassificacao = findViewById(R.id.textClassificacao);
        btnVoltar = findViewById(R.id.btnVoltar);

        // Recebe os valores enviados pela MainActivity
        Intent intent = getIntent();
        String imc = intent.getStringExtra("IMC");
        String classificacao = intent.getStringExtra("CLASSIFICACAO");

        // Exibe os valores na tela
        textResultadoIMC.setText("Seu IMC: " + imc + " kg/m²");
        textClassificacao.setText("Classificação: " + classificacao);


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}