package com.jhogakuse.telephone;

import android.widget.Toast;

/**
 * Created by ekt on 20/05/2016.
 */
public class Validacion {
    private String cmd;
    private String titulo;
    private String subtitulo;

    /*public Validacion(String tit, String sub){
        this.titulo = tit;
        this.subtitulo = sub;
    }*/

    public Validacion(String cmd,String tit, String sub){
        this.cmd=cmd;
        this.titulo = tit;
        this.subtitulo = sub;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setSubtitulo(String subtitulo){
        this.subtitulo = subtitulo;
    }

    public String getCmd(){
        return cmd;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getSubtitulo(){
        return subtitulo;
    }

    public Validacion(){}

    public boolean validarPassword(String str) {
        boolean status = false;

        if ((str.equals("") == false) && (!str.isEmpty())) {
            if (str.matches("[-:._//A-Za-z0-9\\s]{3,20}")) {
                status = true;
            }
        }
        return status;
    }

    public boolean validarUsuario(String str) {
        boolean status = false;

        if ((str.equals("") == false) && (!str.isEmpty())) {
            if (str.matches("[A-Za-z0-9\\s]{3,20}")) {
                status = true;
            }
        }
        return status;
    }

    public Integer validateInt(String str) throws NumberFormatException {
        Integer id = null;
        if (str.matches("[0-9\\s]{3,30}")){
        //if (str.matches("[0-9]*{3,30}")) {
            try {
                id = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                throw e;
            }
        }

        return id;
    }
    //#De la antigua app#

    /*public static final String TABLE_NAME = "Usuarios";
    public static final String CN_ID = "_id";
    public static final String CN_NAME = "nombre";
    public static final String CN_PHONE = "telefono";

    public static final String CREATE_TABLE = "create table "+TABLE_NAME+" ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_PHONE + " text);";

    private DbHelper helper;
    SQLiteDatabase db;
    public DataBaseManager(Context context) {
        helper = new DbHelper(context);
        db = helper .getWritableDatabase();
    }

    private ContentValues generarContentValues (String nombre, String telefono) {
        ContentValues valores = new ContentValues();
        valores.put (CN_NAME,nombre);
        valores.put (CN_PHONE,telefono);
        return valores;
    }

    public void insertar (String nombre, String telefono) {
        db.insert(TABLE_NAME,null, generarContentValues(nombre,telefono));
    }

    public void insertar2 (String nombre, String telefono) {
        db.execSQL("insert into "+TABLE_NAME+" values (null,'"+nombre+"',"+telefono+")");
    }

    public void eliminar (String nombre) {
        db.delete(TABLE_NAME, CN_NAME+"=?", new String[] {nombre});
    }

    public void eliminarMultiple (String nom1, String nom2) {
        db.delete(TABLE_NAME, CN_NAME+"IN (?,?)", new String[] {nom1,nom2});
    }

    public void modificarTelefono (String nombre, String nuevoTelefono) {
        db.update(TABLE_NAME, generarContentValues(nombre,nuevoTelefono), CN_NAME+"=?", new String[] {nombre});
    }

    public Cursor cargarCursorContactos () {
        String[] columnas = new String[] {CN_ID,CN_NAME,CN_PHONE};
        return db.query(TABLE_NAME, columnas, null, null, null, null, null);
    }

    public Cursor buscarContacto(String nombre) {
        String[] columnas = new String[] {CN_ID,CN_NAME,CN_PHONE};
        try{
            Thread.sleep(4000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return db.query(TABLE_NAME, columnas, CN_NAME+"=?", new String[] {nombre}, null, null, null);
    }*/
}
