package com.jhogakuse.telephone;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    private EditText nombre_user,password_first,password_second;
    private Button Registro;
    private TextView nombre_user_label,password_first_label,password_second_label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //Obtenemos los valores a los controles
        nombre_user=(EditText) findViewById(R.id.registro_txtNombre);
        password_first=(EditText) findViewById(R.id.registro_txtPassword_1);
        password_second=(EditText) findViewById(R.id.registro_txtPassword_2);
        Registro=(Button)findViewById(R.id.registro_btnRegistrar);
        nombre_user_label=(TextView) findViewById(R.id.registro_alertNombre);
        password_first_label=(TextView) findViewById(R.id.registro_alertPassword_1);
        password_second_label=(TextView) findViewById(R.id.registro_alertPassword_2);

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generar_usuario();
            }
        });
    }

    private void generar_usuario() {
        try{
            String name=nombre_user.getText().toString();
            String password_001=password_first.getText().toString();
            String password_002=password_second.getText().toString();
            Validacion valido=new Validacion();
            boolean user, sword;
            user=valido.validarUsuario(name);
            sword=valido.validarPassword(password_001);
            if ((user==true)&&(sword==true)){
                if (password_001.equals(password_002)==true){
                    Consultar_registro(name, password_001);
                } else {
                    Toast.makeText(getApplicationContext()," Las contraseñas no coinciden ",Toast.LENGTH_LONG).show();
                    password_first_label.setText(" Las contraseñas no coinciden ");
                }
            } else {
                Toast.makeText(getApplicationContext(), " Usuario y contraseña no permitidos " +
                        "debe contener al menos 3 letras Mayusculas y/o minusculas sin espacios" +
                        " y la contraseña puede contener numeros", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext()," Error :( ",Toast.LENGTH_SHORT).show();
        }
    }

    private void Insertar_usuario(String n, String p) {
        DbHelper cmd = new DbHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = cmd.getWritableDatabase();
        String nombre = n;

        try {
            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("nombre", n);
            nuevoRegistro.put("password", p);
            nuevoRegistro.put("res_A",0);
            nuevoRegistro.put("res_B",0);
            nuevoRegistro.put("res_C",0);
            nuevoRegistro.put("res_D",0);
            nuevoRegistro.put("total",0);
            db.insert("Usuarios", null, nuevoRegistro);

        } catch (Exception e){
            Toast.makeText(getApplicationContext()," Error al insertar registro :( ",Toast.LENGTH_SHORT).show();
        }
        db.close();
        Toast.makeText(getApplicationContext()," Usuario registrado con éxito ",Toast.LENGTH_LONG).show();
        //Muro.MURO_Name = nombre;
        //pase_muro();
        this.finish();
    }

    private void Consultar_registro(String n, String p){
        String ind=null,namae=null;
        String user=null, pass=null;
        boolean bname=false,bpassw=false;
        user = n;
        pass = p;
        DbHelper cmd = new DbHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = cmd.getReadableDatabase();
        Cursor cursor;
        if(db != null) {
            //Método rawQuery()
            cursor  = db.rawQuery("SELECT nombre FROM Usuarios WHERE nombre = '"+user+"'", null);
            if (cursor.moveToFirst()) {
                try {
                    namae = cursor.getString(0);
                    bname=namae.equals(user);
                    if (bname==true){
                        Toast.makeText(getApplicationContext()," Nombre de usuario ya registrado ",Toast.LENGTH_LONG).show();
                        nombre_user_label.setText(" Nombre de usuario ya registrado ");
                    }
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Error...",Toast.LENGTH_SHORT).show();
                }
            } else{
                Insertar_usuario(user,pass);
            }
        }
        db.close();
    }

    private void pase_muro(){
        //Creamos el Intent
        Intent intent = new Intent(this, Muro.class);
        //Iniciamos la nueva actividad
        startActivity(intent);
    }
}
