package com.example.user.sqliteapp02.negocio;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.sqliteapp02.datos.AccesoDatos;

import java.util.ArrayList;

/**
 * Created by USER on 15/09/2016.
 */
public class Distrito extends AccesoDatos {
    private int codigoDepartamento;
    private int codigoProvincia;
    private int codigoDistrito;
    private String nombre;

    public static ArrayList<Distrito> listaDis = new ArrayList<Distrito>();

    public int getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(int codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public int getCodigoDistrito() {
        return codigoDistrito;
    }

    public void setCodigoDistrito(int codigoDistrito) {
        this.codigoDistrito = codigoDistrito;
    }

    public int getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(int codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void cargarDatosDistrito(int codigoDepartamento,int codigoProvincia){
        SQLiteDatabase bd = this.getReadableDatabase();
        String sql = "select * from distrito where codigo_departamento="+codigoDepartamento+" AND codigo_provincia="+codigoProvincia+" order by 4";
        Cursor resultado = bd.rawQuery(sql,null);

        listaDis.clear();

        while(resultado.moveToNext()){
            Distrito objPro = new Distrito();
            objPro.setCodigoDepartamento(resultado.getInt(0));
            objPro.setCodigoProvincia(resultado.getInt(1));
            objPro.setCodigoDistrito(resultado.getInt(2));
            objPro.setNombre(resultado.getString(3));
            listaDis.add(objPro);
        }
    }

    public String[] listaDistri(int codigoDepartamento,int codigoProvincia){
        cargarDatosDistrito(codigoDepartamento,codigoProvincia);

        String listaNombresDistritos[] = new String[listaDis.size()];

        for (int i = 0; i < listaDis.size(); i++) {
            Distrito item = listaDis.get(i);
            listaNombresDistritos[i] = item.getNombre();
        }

        return listaNombresDistritos;
    }
}
