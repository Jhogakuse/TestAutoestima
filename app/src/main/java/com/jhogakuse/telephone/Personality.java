package com.jhogakuse.telephone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Personality extends AppCompatActivity {
    TextView personalidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality);
        personalidad = (TextView) findViewById(R.id.personality_txt);
        int valor = Integer.parseInt(Muro.RES_TOTAL);
        if ((valor >= 40) && (valor <= 73)) {
            personalidad.setText("Autoestima baja(negativa):\n" +
                    "Desconfianza de capacidades y habilidades, hipersensibilidad,indecision, timidez, vergüenza, temores, " +
                    "autoexigencia,sentimientos de culpa,insatisfaccion,negativismo,intolerancia, deseo de complacer, " +
                    "egocentrismo artificial.");
        }
        if ((valor >= 74) && (valor <= 83)) {
            personalidad.setText("Autoestima baja(positiva):\n" +
                    "Reconocimiento de los defectos y proceso de recuperacion y/o reparación,aceptacion gradual " +
                    "de limitaciones, aceptacion gradual de habilidades y capacidades, intermitencia o episodios de " +
                    "autoestima alta");
        }
        if ((valor >= 84) && (valor <= 103)) {
            personalidad.setText("Autoestima alta (positiva):\n" +
                    "Confianza estable de sus capacidades y habilidades aceptacion de virtudes y defectos, " +
                    "defiende sus derechos, cree en los valores y los aplica, disfruta de la vida, es sensible " +
                    "a los demas,acepta criticas y maneja su tolerancia.");
        }
        if ((valor >= 104) && (valor <= 160)) {
            personalidad.setText("Autoestima alta (negativa)\n" +
                    "Sobre estimulación de capacidades y habilidades, egocentrismo, soberbia, exhibicionismo, ideas " +
                    "de poderlo, omnipotencia y control, busca el prestigio, solo se interesa de si mismo.");
        }
    }
}
