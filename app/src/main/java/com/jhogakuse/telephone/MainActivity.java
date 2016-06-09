
package com.jhogakuse.telephone;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;
import android.view.ViewGroup;


public class MainActivity extends AppCompatActivity {
    Button Hey;
    Button Registro;
    EditText Usuario, Password;
    TextView alerta;
    private static final int MNU_OPC1 = 1;
    private static final int MNU_OPC2 = 2;
    private static final int MNU_OPC3 = 3;

    private static final int SMNU_OPC1 = 31;
    private static final int SMNU_OPC2 = 32;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Hey = (Button) findViewById(R.id.main_btnEnviar);
        Registro = (Button) findViewById(R.id.main_btnRegistrar);
        Usuario = (EditText) findViewById(R.id.main_user);
        Password = (EditText) findViewById(R.id.main_password);
        alerta = (TextView) findViewById(R.id.main_alert);

        cargar();
        Hey.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                admin();
            }
        });
        Registro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pase_registro();
            }
        });
    }

    private  void admin(){
        String user=null, pass=null;
        user = Usuario.getText().toString();
        pass = Password.getText().toString();
        if ((user.equals("_root"))&&(pass.equals("toor"))){
            Password.setText("");
            Usuario.setText("");
            pase_alojamiento();
        } else{
            login();
        }
    }

    private void pase_alojamiento() {
        Intent intent = new Intent(this,Alojamiento.class);
        startActivity(intent);
    }

    private void pase_db(){
        //Creamos el Intent
        Intent intent = new Intent(MainActivity.this, Alojamiento.class);
        //Iniciamos la nueva actividad
        startActivity(intent);
    }

    private void pase_muro(){
        //Creamos el Intent
        Intent intent = new Intent(MainActivity.this, Muro.class);
        //Iniciamos la nueva actividad
        startActivity(intent);
        this.finish();
    }

    private void pase_registro(){
        //Creamos el Intent
        Intent intent = new Intent(MainActivity.this, Registro.class);
        //Iniciamos la nueva actividad
        startActivity(intent);
    }

    private void login() {
        String ind = null, namae = null, senia = null, res_A = null, res_B = null, res_C = null, res_D = null, total = null;
        String user = null, pass = null;
        boolean bname = false, bpassw = false;
        user = Usuario.getText().toString();
        pass = Password.getText().toString();
        DbHelper cmd = new DbHelper(this, "DBUsuarios", null, 1);
        SharedPreferences prefs = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        SQLiteDatabase db = cmd.getReadableDatabase();
        Cursor cursor;
        if(db != null) {
            //Método rawQuery()
            cursor  = db.rawQuery("SELECT codigo, nombre, password, res_A, res_B, res_C, res_D, total FROM Usuarios WHERE nombre = '"+user+"' AND password = '"+pass+"'", null);
            if (cursor.moveToFirst()) {
            try {
                ind = cursor.getString(0);
                namae = cursor.getString(1);
                senia = cursor.getString(2);
                res_A = cursor.getString(3);
                res_B = cursor.getString(4);
                res_C = cursor.getString(5);
                res_D = cursor.getString(6);
                total = cursor.getString(7);
                bname=namae.equals(user);
                bpassw=senia.equals(pass);
                if (bname==true && bpassw==true){
                    Password.setText("");
                    Usuario.setText("");
                    Muro.MURO_ID = ind;
                    Muro.MURO_Name = namae;
                    Muro.RES_A = res_A;
                    Muro.RES_B = res_B;
                    Muro.RES_C = res_C;
                    Muro.RES_D = res_D;
                    Muro.RES_TOTAL = total;
                    editor = prefs.edit();
                    editor.putString("ID",ind);
                    editor.putString("Name",namae);
                    editor.commit();
                    pase_muro();
                }
            } catch (Exception e){
                Toast.makeText(getApplicationContext(),"Error...",Toast.LENGTH_SHORT).show();
            }
            } else{
                if (bname!=true && bpassw!=true){
                    //Toast.makeText(getApplicationContext()," Usuario y contraseña incorrectos ",Toast.LENGTH_LONG).show();
                    alerta.setText("Usuario y contraseña incorrectos.");
                }
            }
        }
        db.close();
    }

    private void cargar() {
        SharedPreferences prefs = getSharedPreferences("Usuario",Context.MODE_PRIVATE);
        String _id = prefs.getString("ID", "-1");
        String _name = prefs.getString("Name", null);
        Toast.makeText(getApplicationContext(),_id+"  "+_name,Toast.LENGTH_SHORT).show();
        if ((_id != "-1") && (_name != null) ) {
            Muro.MURO_ID = _id;
            Muro.MURO_Name = _name;
            Muro.cargarUsuario = true;
            pase_muro();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, MNU_OPC1, Menu.NONE, "Opcion1")
        		.setIcon(android.R.drawable.ic_menu_preferences);
        /*menu.add(Menu.NONE, MNU_OPC2, Menu.NONE, "Opcion2")
        		.setIcon(android.R.drawable.ic_menu_compass);

        SubMenu smnu = menu.
        		addSubMenu(Menu.NONE, MNU_OPC3, Menu.NONE, "Opcion3")
        			.setIcon(android.R.drawable.ic_menu_agenda);
        smnu.add(Menu.NONE, SMNU_OPC1, Menu.NONE, "Opcion 3.1");
        smnu.add(Menu.NONE, SMNU_OPC2, Menu.NONE, "Opcion 3.2");*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MNU_OPC1:
                //lblMensaje.setText("Opcion 1 pulsada!");
                pase_db();
                return true;
            /*case MNU_OPC2:
                //lblMensaje.setText("Opcion 2 pulsada!");
                return true;
            case MNU_OPC3:
                //lblMensaje.setText("Opcion 3 pulsada!");;
                return true;
            case SMNU_OPC1:
                //lblMensaje.setText("Opcion 3.1 pulsada!");;
                pase_muro();
                return true;
            case SMNU_OPC2:
                //lblMensaje.setText("Opcion 3.2 pulsada!");;
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
