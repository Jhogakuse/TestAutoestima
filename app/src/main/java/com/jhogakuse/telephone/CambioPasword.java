package com.jhogakuse.telephone;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CambioPasword extends AppCompatActivity {
    private EditText old_password,first_password,second_password;
    private Button cambio;
    private TextView password_old_label,password_first_label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_pasword);
        old_password = (EditText) findViewById(R.id.cambio_OldPassword);
        first_password = (EditText) findViewById(R.id.cambio_txtPassword_1);
        second_password = (EditText) findViewById(R.id.cambio_txtPassword_2);
        cambio = (Button) findViewById(R.id.cambio_btnCambio);
        password_old_label = (TextView) findViewById(R.id.cambio_alert_oldpassword);
        password_first_label = (TextView) findViewById(R.id.cambio_alertPassword_1);

        cambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambio_password();
            }
        });
    }

    private void cambio_password() {
        try{
            String password_old=old_password.getText().toString();
            String password_001=first_password.getText().toString();
            String password_002=second_password.getText().toString();
            Validacion valido=new Validacion();
            boolean old, sword;
            old=valido.validarPassword(password_old);
            sword=valido.validarPassword(password_001);
            if ((old==true)&&(sword==true)){
                if (password_001.equals(password_002)==true){
                    Consultar_contrasenia(password_old, password_001);
                } else {
                    Toast.makeText(getApplicationContext(), " Las nuevas contraseñas no coinciden ", Toast.LENGTH_LONG).show();
                    password_first_label.setText(" Las nuevas contraseñas no coinciden ");
                }
            } else {
                Toast.makeText(getApplicationContext(), " Contraseñas no permitidas " +
                        "deben contener al menos 3 letras Mayusculas y/o minusculas, " +
                        "también se permiten los números", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext()," Error :( ",Toast.LENGTH_SHORT).show();
        }
    }

    private void Consultar_contrasenia(String old, String nueva) {
        String registro = null;
        String antigua=null, reciente=null;
        boolean bpassw=false;
        antigua = old;
        reciente = nueva;
        DbHelper cmd = new DbHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = cmd.getReadableDatabase();
        Cursor cursor;
        if(db != null) {
            //Método rawQuery()
            cursor  = db.rawQuery("SELECT password FROM Usuarios WHERE codigo = '"+Muro.MURO_ID+"'", null);
            if (cursor.moveToFirst()) {
                    registro = cursor.getString(0);
                    bpassw = registro.equals(antigua);
                    if (bpassw==true){
                        ModificarPassword(reciente);
                    } else {
                        password_old_label.setText(" La contraseña actual no coincide con la que ingresaste ");
                        password_first_label.setText("");
                        Toast.makeText(getApplicationContext()," La contraseña actual no coincide con la que ingresaste ",Toast.LENGTH_LONG).show();
                    }
            }
        }
        db.close();
    }

    private void ModificarPassword(String password) {
        DbHelper cmd = new DbHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = cmd.getWritableDatabase();
        if (db != null){
            try {
                ContentValues valores = new ContentValues();
                valores.put("password", password);
                db.update("Usuarios", valores, "codigo=" + Muro.MURO_ID, null);
                Toast.makeText(getApplicationContext()," Contraseña cambiada satisfactoriamente. ",Toast.LENGTH_LONG).show();
                this.finish();
            } catch (Exception e){
                Toast.makeText(getApplicationContext(),"Error .(",Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }
}
