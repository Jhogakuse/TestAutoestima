package com.jhogakuse.telephone;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Alojamiento extends AppCompatActivity {
    private EditText txtName;
    private EditText txtPassword;
    private Button btnInsertar;


    //private DataBaseManager manager;
    private Cursor cursor;
    private ListView lista;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alojamiento);

        //Obtenemos las referencias a los controles
        btnInsertar = (Button)findViewById(R.id.aloja_btnInsertar);
        lista = (ListView) findViewById(R.id.aloja_listView);
        txtName = (EditText) findViewById(R.id.aloja_txtNombre);
        txtPassword = (EditText) findViewById(R.id.aloja_txtPassw);

        //Se crea la base de datos de la clase DataBaseManager
        //DataBaseManager manager = new DataBaseManager(this);

        /*btnBuscar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.aloja_button) {
                    new BuscarTask().execute();
                }
            }
        });*/

        btnInsertar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                btnAgregar_onClick();
            }
        });


        /* Ordena a lista */
        btnConsultar_onClick();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                try {
                    TextView cmd_lista, nombre_lista, pas_lista;

                    cmd_lista = (TextView) view.findViewById(R.id.LblCmd);
                    nombre_lista = (TextView) view.findViewById(R.id.LblTitulo);
                    pas_lista = (TextView) view.findViewById(R.id.LblSubTitulo);

                    Modificabase.Modific_ID = cmd_lista.getText().toString();
                    Modificabase.Modific_Name = nombre_lista.getText().toString();
                    Modificabase.Modific_Pass = pas_lista.getText().toString();

                    Intent modify_intent = new Intent(getApplicationContext(), Modificabase.class);
                    startActivity(modify_intent);
                    salir();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "\"Error :(\"", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void btnAgregar_onClick() {
        DbHelper cmd = new DbHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = cmd.getWritableDatabase();
        Validacion valido= new Validacion();
        if(db != null) {
            //Recuperamos los valores de los campos de texto
            String pas = txtPassword.getText().toString();
            String nom = txtName.getText().toString();
            boolean user, sword;
            user=valido.validarUsuario(nom);
            sword=valido.validarPassword(pas);
            if ((user==true)&&(sword==true)){
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("nombre", nom);
                nuevoRegistro.put("password", pas);
                nuevoRegistro.put("res_A",0);
                nuevoRegistro.put("res_B",0);
                nuevoRegistro.put("res_C",0);
                nuevoRegistro.put("res_D",0);
                nuevoRegistro.put("total",0);
                db.insert("Usuarios", null, nuevoRegistro);
            } else {
                Toast.makeText(getApplicationContext()," Usuario y contraseña incorrectos ",Toast.LENGTH_LONG).show();
            }
        }
        db.close();
        txtPassword.setText("");
        txtName.setText("");
        btnConsultar_onClick();
    }

    private void btnConsultar_onClick() {
        DbHelper cmd = new DbHelper(this, "DBUsuarios", null, 1);
        SQLiteDatabase db = cmd.getReadableDatabase();
        if(db != null) {
            //Alternativa 1: método rawQuery()
            cursor  = db.rawQuery("SELECT codigo as _id, nombre as name, password as passw FROM Usuarios", null);

            try {
            String[] from = new String[]{"_id","name","passw"};
            int[] to = new int[]{R.id.LblCmd,R.id.LblTitulo,R.id.LblSubTitulo};
            adapter = new SimpleCursorAdapter(this,R.layout.format_lista,cursor,from, to);
            lista.setAdapter(adapter);
            } catch (Exception e){
                Toast.makeText(getApplicationContext(),"Error...",Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }

    private void salir(){
        this.finish();
    }

    /*class AdaptadorTitulares extends ArrayAdapter<DataBaseManager> {

        Activity context;
        DataBaseManager[][]datos;

        AdaptadorTitulares(Activity context, DataBaseManager[][] datos) {
            super(context, R.layout.format_lista, datos);
            this.context = context;
            this.datos = datos;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.format_lista, null);

            TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
            lblTitulo.setText(datos[position].getTitulo());

            TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
            lblSubtitulo.setText(datos[position].getSubtitulo());

            return(item);
        }
    }*/

    /*private class BuscarTask extends AsyncTask <Void, Void, Void> {
        String nombre = txtName.getText().toString();

        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(),"Buscando...\n"+nombre,Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //cursor = manager.buscarContacto(nombre);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //adapter.changeCursor(cursor);
            Toast.makeText(getApplicationContext(),"Finalizado...",Toast.LENGTH_SHORT).show();
        }
    }*/
}
