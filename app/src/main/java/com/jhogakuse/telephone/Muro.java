package com.jhogakuse.telephone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Muro extends AppCompatActivity {
    private ListView lista_muro;
    public static String MURO_ID;
    public static String MURO_Name;
    public static String RES_A;
    public static String RES_B;
    public static String RES_C;
    public static String RES_D;
    public static String RES_TOTAL;
    public static boolean cargarUsuario;
    public static int avance;
    public static int cont_A_temporal;
    public static int cont_B_temporal;
    public static int cont_C_temporal;
    public static int cont_D_temporal;
    public static int cont_total_temporal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muro);

        if ( cargarUsuario == true) {
            cargar();
            cargarUsuario = false;
        }
        if (cargarUsuario == false) {
            boolean continuo;
            continuo = incompleto();
            if (continuo == true){
                cargar();
            }
            continuo = negativo();
            if (continuo == true){
                salvar();
            }
        }

        Validacion[] datos = new Validacion[]{
                        new Validacion("1","Bienvenid@", MURO_Name),
                        new Validacion("2","Realizar Test", "Realiza el test y conoce tu autoestima"),
                        new Validacion("3","Ver personalidad", "Ver tu tipo de autoestima"),
                        new Validacion("4","Ver puntaje", "Ver gráfica de preguntas"),
                        new Validacion("5","Cambiar contraseña", "Cambia tu contraseña"),
                        new Validacion("6","Cerrar seción", "Cierra tu sesión")};

        AdaptadorTitulares adaptador = new AdaptadorTitulares(this, datos);

        lista_muro = (ListView)findViewById(R.id.lista_menu);
        lista_muro.setAdapter(adaptador);

        lista_muro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                try {
                    TextView cmd_option;
                    int indice=0;
                    String num = null;
                    cmd_option = (TextView) view.findViewById(R.id.LblCmd);
                    num = cmd_option.getText().toString();
                    indice = Integer.parseInt(num);
                    switch (indice){
                        case 1:
                            Toast.makeText(getApplicationContext()," Hola "+MURO_Name,Toast.LENGTH_LONG).show();
                            break;
                        case 2:
                            pase_test();
                            Toast.makeText(getApplicationContext(),MURO_ID+" "+MURO_Name+" "+RES_A+" "+RES_B+" "+RES_C+" "+RES_D+" "+RES_TOTAL,Toast.LENGTH_LONG).show();
                            break;
                        case 3:
                            if ((RES_TOTAL.equals("0"))) {
                                Toast.makeText(getApplicationContext()," No haz iniciado el test,\ncuando lo hayas terminado\npodrás ver tu tipo de personlidad",Toast.LENGTH_LONG).show();
                            } else {
                                if (avance != 0) {
                                    Toast.makeText(getApplicationContext()," No haz terminado el test,\ncuando lo hayas terminado\npodrás ver tu tipo de personlidad",Toast.LENGTH_LONG).show();
                                } else {
                                    pase_personalidad();
                                }
                            }
                            break;
                        case 4:
                            if ((RES_TOTAL.equals("0"))) {
                                Toast.makeText(getApplicationContext()," No haz iniciado el test\ncuando lo hayas terminado\npodrás ver la gráfica de tus respuestas",Toast.LENGTH_LONG).show();
                            } else {
                                if (avance != 0) {
                                    Toast.makeText(getApplicationContext()," No haz terminado el test,\ncuando lo hayas terminado\npodrás ver tu tipo de personlidad",Toast.LENGTH_LONG).show();
                                } else {
                                    pase_canvas();
                                }
                            }
                            break;
                        case 5:
                            pase_cambio_password();
                            break;
                        case 6:
                            salvar();
                            borrarUsuario();
                            avance = 0;
                            cont_A_temporal = 0;
                            cont_B_temporal = 0;
                            cont_C_temporal = 0;
                            cont_D_temporal = 0;
                            cont_total_temporal = 0;
                            salir();
                            break;
                        default:
                            Toast.makeText(getApplicationContext()," Upss :( ",Toast.LENGTH_LONG).show();
                            break;
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "\"Error :(\"", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void pase_personalidad() {
        Intent intent = new Intent(this, Personality.class);
        startActivity(intent);
    }

    private boolean negativo() {
        SharedPreferences prefs = getSharedPreferences(MURO_Name, Context.MODE_PRIVATE);
        String _idNegativo = prefs.getString("ID", "-1");
        if (_idNegativo.equals("-1") ){
            return true;
        } else {
            return false;
        }
    }

    private boolean incompleto() {
        SharedPreferences prefs = getSharedPreferences(MURO_Name, Context.MODE_PRIVATE);
        int _avance = prefs.getInt("Avance", 0);
        /*int conteo_a = prefs.getInt("conteo_a", 0);
        int conteo_b = prefs.getInt("conteo_b", 0);
        int conteo_c = prefs.getInt("conteo_c", 0);
        int conteo_d = prefs.getInt("conteo_d", 0);
        int conteo_total = prefs.getInt("conteo_total", 0);*/
        if (_avance==0 /*&& conteo_a==0 && conteo_b==0 && conteo_c==0 && conteo_d==0 && conteo_total==0*/){
            return false;
        } else {
            return true;
        }
    }

    private void borrarUsuario() {
        SharedPreferences prefs = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = prefs.edit();
        editor.clear();
        editor.commit();
    }


    private void salvar() {
        SharedPreferences prefs = getSharedPreferences(MURO_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = prefs.edit();
        editor.putString("ID",MURO_ID);
        editor.putString("Name",MURO_Name);
        editor.putString("Res_A",RES_A);
        editor.putString("Res_B",RES_B);
        editor.putString("Res_C",RES_C);
        editor.putString("Res_D",RES_D);
        editor.putString("Res_Total",RES_TOTAL);
        editor.putInt("Avance", avance);
        editor.putInt("conteo_a",cont_A_temporal);
        editor.putInt("conteo_b",cont_B_temporal);
        editor.putInt("conteo_c",cont_C_temporal);
        editor.putInt("conteo_d",cont_D_temporal);
        editor.putInt("conteo_total",cont_total_temporal);
        editor.commit();
    }

    private void cargar() {
        SharedPreferences prefs = getSharedPreferences(MURO_Name, Context.MODE_PRIVATE);
        MURO_ID = prefs.getString("ID", "-1");
        MURO_Name = prefs.getString("Name",null);
        RES_A = prefs.getString("Res_A", "0");
        RES_B = prefs.getString("Res_B", "0");
        RES_C = prefs.getString("Res_C", "0");
        RES_D = prefs.getString("Res_D", "0");
        RES_TOTAL = prefs.getString("Res_Total", "0");
        avance = prefs.getInt("Avance", 0);
        cont_A_temporal = prefs.getInt("conteo_a", 0);
        cont_B_temporal = prefs.getInt("conteo_b", 0);
        cont_C_temporal = prefs.getInt("conteo_c", 0);
        cont_D_temporal = prefs.getInt("conteo_d", 0);
        cont_total_temporal = prefs.getInt("conteo_total", 0);
        Toast.makeText(getApplicationContext(),MURO_ID+"  "+MURO_Name+"  "+RES_A+"  "+RES_B+"  "+RES_C+"  "+RES_D+"  "+RES_TOTAL+"  "+avance+"  "+cont_A_temporal
                +"  "+cont_B_temporal+"  "+cont_C_temporal+"  "+cont_D_temporal+"  "+cont_total_temporal,Toast.LENGTH_SHORT).show();
    }

    private void pase_test() {
        Intent intent = new Intent(this, Test.class);
        //Iniciamos la nueva actividad
        startActivity(intent);
    }

    private void pase_canvas(){
        //Creamos el Intent
        Intent intent = new Intent(this, canvas.class);
        //Iniciamos la nueva actividad
        startActivity(intent);
    }

    private void pase_cambio_password(){
        //Creamos el Intent
        Intent intent = new Intent(this, CambioPasword.class);
        //Iniciamos la nueva actividad
        startActivity(intent);
    }

    private void salir(){
        //Creamos el Intent
        Intent intent = new Intent(this, MainActivity.class);
        // Iniciamos la nueva actividad
        startActivity(intent);
        this.finish();
    }

    class AdaptadorTitulares extends ArrayAdapter<Validacion> {
        Activity context;
        Validacion[] datos;

        AdaptadorTitulares(Activity context, Validacion[] datos) {
            super(context,R.layout.format_lista,datos);
            this.context = context;
            this.datos = datos;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.format_lista, null);

            TextView lblNumber = (TextView)item.findViewById(R.id.LblCmd);
            lblNumber.setText(datos[position].getCmd());

            TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
            lblTitulo.setText(datos[position].getTitulo());

            TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
            lblSubtitulo.setText(datos[position].getSubtitulo());

            return(item);
        }
    }
}
