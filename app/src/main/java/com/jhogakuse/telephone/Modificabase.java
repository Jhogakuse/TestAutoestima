package com.jhogakuse.telephone;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class Modificabase extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtContrasenia;
    private Button btnActualizar;
    private Button btnEliminar;
    private Button btnCancelar;

    public static String Modific_ID;
    public static String Modific_Name;
    public static String Modific_Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificabase);
        txtNombre = (EditText) findViewById(R.id.modificabase_txtNombre);
        txtContrasenia = (EditText) findViewById(R.id.modificabase_txtPassw);
        btnActualizar = (Button)findViewById(R.id.modificabase_btnModificar);
        btnEliminar = (Button)findViewById(R.id.modificabase_btnEliminar);
        btnCancelar = (Button)findViewById(R.id.modificabase_btnCancel);

        txtNombre.setText(Modific_Name);
        txtContrasenia.setText(Modific_Pass);

        btnActualizar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                btnActualizar_onClick();
            }
        });
        btnEliminar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                btnEliminar_onClick();
            }
        });
        btnCancelar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCancel_Onclick();
            }
        });
    }

    private void btnActualizar_onClick() {
        DbHelper cmd = new DbHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = cmd.getWritableDatabase();
        Validacion valido= new Validacion();
        if (db != null){
            try {
                //Recuperamos los valores de los campos de texto
                String nom = txtNombre.getText().toString();
                String pas = txtContrasenia.getText().toString();
                boolean user, sword;
                user=valido.validarUsuario(nom);
                sword=valido.validarPassword(pas);
                if ((user==true)&&(sword==true)) {
                    ContentValues valores = new ContentValues();
                    valores.put("nombre", nom);
                    valores.put("password", pas);
                    db.update("Usuarios", valores, "codigo=" + Modific_ID, null);
                    Intent aloja_intent = new Intent(this, Alojamiento.class);
                    startActivity(aloja_intent);
                    this.finish();
                } else {
                    Toast.makeText(getApplicationContext()," Usuario y contraseña incorrectos ",Toast.LENGTH_LONG).show();
                }
            } catch (Exception e){
                Toast.makeText(getApplicationContext(),"Error .(",Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }

    private void btnEliminar_onClick() {
        DbHelper cmd = new DbHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = cmd.getWritableDatabase();
        if(db != null) {
            try{
                //Recuperamos el código y eliminamos
                db.delete("Usuarios", "codigo=" + Modific_ID, null);
            } catch(Exception e){
                Toast.makeText(getApplicationContext(),"Error :(",Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
        Intent modify_intent = new Intent(getApplicationContext(), Alojamiento.class);
        startActivity(modify_intent);
        this.finish();
    }

    private void btnCancel_Onclick(){
        Intent modify_intent = new Intent(getApplicationContext(), Alojamiento.class);
        startActivity(modify_intent);
        this.finish();
    }
}
