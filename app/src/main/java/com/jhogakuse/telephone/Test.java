package com.jhogakuse.telephone;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test extends AppCompatActivity {
    Spinner respuesta_01,respuesta_02;
    TextView pregunta_01, pregunta_02;
    Button btnenviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        inicializarcomponentes();
    }

    private void inicializarcomponentes() {
        respuesta_01 = (Spinner) findViewById(R.id.test_spn_answer_01);
        respuesta_02 = (Spinner) findViewById(R.id.test_spn_answer_02);
        pregunta_01 = (TextView) findViewById(R.id.test_lbl_question_01);
        pregunta_02 = (TextView) findViewById(R.id.test_lbl_question_02);
        btnenviar = (Button) findViewById(R.id.test_enviar);

        String[] items = new String[] {"Siempre","Casi siempre","Algunas veces","Nunca"};

        List lst = new ArrayList();
        lst = Arrays.asList(items);
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lst);
        respuesta_01.setAdapter(adp);
        respuesta_02.setAdapter(adp);
        play();
        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aumento();
                play();
            }
        });
    }

    private void play() {
        switch (Muro.avance) {
            case 0:
                pregunta_01.setText("1.- Me siento alegre: ");
                pregunta_02.setText("2.- Me siento incomodo con la gente que no conozco: ");
                break;        
            case 1:
                pregunta_01.setText("3.-Me siento dependiente de otros: ");
                pregunta_02.setText("4.-Los retos representan una amenaza para mi persona: ");
                break;
            case 2:
                pregunta_01.setText("5.-Me siento triste: ");
                pregunta_02.setText("6.-Me siento comodo con la gente que no conozco: ");
                break;
            case 3:
                pregunta_01.setText("7.-Cuando las cosas salen mal es mi culpa:  ");
                pregunta_02.setText("8.-Siento que soy agradable a los demas: ");
                break;
            case 4:
                pregunta_01.setText("9.-Es bueno cometer errores: ");
                pregunta_02.setText("10.-Si las cosas salen bien se deben a mis esfuerzos: ");
                break;
            case 5:
                pregunta_01.setText("11.-Resulto desagradable a los demas:");
                pregunta_02.setText("12.- Es de sabios rectificar:");
                break;
            case 6:
                pregunta_01.setText("13.- Me siento el ser menos importante del grupo:");
                pregunta_02.setText("14.- Hacer lo que los demas quieren es necesario para sentirme aceptado:");
                break;
            case 7:
                pregunta_01.setText("15.- Me siento el ser mas importante del mundo:");
                pregunta_02.setText("16.- Todo me sale mal:");
                break;
            case 8:
                pregunta_01.setText("17.- Siento que le mundo entero se rie de mi:");
                pregunta_02.setText("18.- Acepto  de buen grado la critica constructiva:");
                break;
            case 9:
                pregunta_01.setText("19.- Yo me rio del mundo entero:");
                pregunta_02.setText("20.- A mi  todo me resbala:");
                break;
            case 10:
                pregunta_01.setText("21.- Me siento content@ con mi estatura:");
                pregunta_02.setText("22.- Todo me sale bien");
                break;
            case 11:
                pregunta_01.setText("23.- Puedo hablar abiertamente de mis sentimientos");
                pregunta_02.setText("24.- Siento que mi estatura no es la correcta:");
                break;
            case 12:
                pregunta_01.setText("25.- Solo acepto las alabanzas que me hagan:");
                pregunta_02.setText("26.- Me divierte reirme de mis errores:");
                break;
            case 13:
                pregunta_01.setText("27.- Mis sentimientos me los reservo exclusivamente para mi:");
                pregunta_02.setText("28.- Yo soy perfect@:");
                break;
            case 14:
                pregunta_01.setText("29.- Me alegra cuando otros fracasan en sus intentos:");
                pregunta_02.setText("30.- Me gustaria cambiar mi apariencia fisica:");
                break;
            case 15:
                pregunta_01.setText("31.- Evito nuevas experiencias:");
                pregunta_02.setText("32.- Realmente soy timid@:");
                break;
            case 16:
                pregunta_01.setText("33.- Acepto los retos sin pensarlo:");
                pregunta_02.setText("34.- Encuentro excusas para no aceptar los cambios:");
                break;
            case 17:
                pregunta_01.setText("35.- Siento que los demás dependen de mi:");
                pregunta_02.setText("36.- Los demas cometen muchos mas errores que yo:");
                break;
            case 18:
                pregunta_01.setText("37.- Me considero sumamente agresiv@:");
                pregunta_02.setText("38.- Me aterran los cambios:");
                break;
            case 19:
                pregunta_01.setText("39.- Me encanta la aventura");
                pregunta_02.setText("40.- Me alegra cuando otros alcanzan el exito en sus intentos");
                break;
            case 20:
                //Se salvan las respuestas
                Muro.RES_A = Integer.toString(Muro.cont_A_temporal);
                Muro.RES_B = Integer.toString(Muro.cont_B_temporal);
                Muro.RES_C = Integer.toString(Muro.cont_C_temporal);
                Muro.RES_D = Integer.toString(Muro.cont_D_temporal);
                //Se hacen las respectivas operaciones
                Muro.cont_A_temporal = Muro.cont_A_temporal * 4;
                Muro.cont_B_temporal = Muro.cont_B_temporal * 3;
                Muro.cont_C_temporal = Muro.cont_C_temporal * 2;
                Muro.cont_D_temporal = Muro.cont_D_temporal * 1;
                //Se salva el total
                Muro.cont_total_temporal = Muro.cont_A_temporal + Muro.cont_B_temporal + Muro.cont_C_temporal + Muro.cont_D_temporal;
                Muro.RES_TOTAL = Integer.toString(Muro.cont_total_temporal);
                //Se limpian los conteos
                Muro.cont_A_temporal = 0;
                Muro.cont_B_temporal = 0;
                Muro.cont_C_temporal = 0;
                Muro.cont_D_temporal = 0;
                Muro.cont_total_temporal = 0;
                Muro.avance = 0;
                //Se salvan las preferencias de usuario y la BD
                salvar();
                modificarRespuestasBD();
                pase_personalidad();
                break;
            default:
                Toast.makeText(getApplicationContext()," Error de 40 preguntas .( ", Toast.LENGTH_LONG).show();
        }
    }

    private void pase_personalidad() {
        Intent intent = new Intent(this, Personality.class);
        startActivity(intent);
        this.finish();
    }

    private void aumento() {
        String opcion_1 = respuesta_01.getSelectedItem().toString();
        String opcion_2 = respuesta_02.getSelectedItem().toString();
        caso_respuesta(opcion_1);
        caso_respuesta(opcion_2);
        Muro.avance++;
        salvar();
    }

    private void salvar() {
        SharedPreferences prefs = getSharedPreferences(Muro.MURO_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = prefs.edit();
        editor.putString("Res_A",Muro.RES_A);
        editor.putString("Res_B",Muro.RES_B);
        editor.putString("Res_C",Muro.RES_C);
        editor.putString("Res_D",Muro.RES_D);
        editor.putString("Res_Total",Muro.RES_TOTAL);
        editor.putInt("Avance", Muro.avance);
        editor.putInt("conteo_a",Muro.cont_A_temporal);
        editor.putInt("conteo_b",Muro.cont_B_temporal);
        editor.putInt("conteo_c",Muro.cont_C_temporal);
        editor.putInt("conteo_d",Muro.cont_D_temporal);
        editor.putInt("conteo_total",Muro.cont_total_temporal);
        editor.commit();
    }

    private void modificarRespuestasBD() {
        DbHelper cmd = new DbHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = cmd.getWritableDatabase();
        if (db != null){
            try {
                ContentValues valores = new ContentValues();
                valores.put("res_A", Muro.RES_A);
                valores.put("res_B", Muro.RES_B);
                valores.put("res_C", Muro.RES_C);
                valores.put("res_D", Muro.RES_D);
                valores.put("total", Muro.RES_TOTAL);
                db.update("Usuarios", valores, "codigo=" + Muro.MURO_ID, null);
            } catch (Exception e){
                Toast.makeText(getApplicationContext(),"Error .(",Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }

    private void caso_respuesta(String opcion) {
        switch (opcion) {
            case "Siempre":
                Muro.cont_A_temporal++;
                break;
            case "Casi siempre":
                Muro.cont_B_temporal++;
                break;
            case "Algunas veces":
                Muro.cont_C_temporal++;
                break;
            case "Nunca":
                Muro.cont_D_temporal++;
                break;
            default:
                Toast.makeText(getApplicationContext()," Error de respuesta .( ", Toast.LENGTH_LONG).show();
        }
    }
}
