package com.example.user.sqliteapp02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.sqliteapp02.datos.AccesoDatos;
import com.example.user.sqliteapp02.negocio.Departamento;
import com.example.user.sqliteapp02.negocio.Distrito;
import com.example.user.sqliteapp02.negocio.Provincia;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spDepartamento,spProvincia,spDistrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spDepartamento = (Spinner) findViewById(R.id.spDepartamento);
        spProvincia = (Spinner) findViewById(R.id.spProvincia);
        spDistrito = (Spinner) findViewById(R.id.spDistrito);

        AccesoDatos.aplicacion = this;

        cargarDatosSpinnerDepartamento();

        spDepartamento.setOnItemSelectedListener(this);
        spProvincia.setOnItemSelectedListener(this);
    }

    private void cargarDatosSpinnerDepartamento(){
        String listaDepartamento[] = new Departamento().listaDespartamento();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listaDepartamento);

        spDepartamento.setAdapter(adapter);
    }

    private void cargarDatosSpinnerProvincia(int codigoDepartamento){
        String listaProvincia[] = new Provincia().listaProvincia(codigoDepartamento);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listaProvincia);

        spProvincia.setAdapter(adapter);
    }

    private void cargarDatosSpinnerDistrito(int codigoDepartamento, int codigoProvincia){
        String listaDistrito[] = new Distrito().listaDistri(codigoDepartamento,codigoProvincia);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listaDistrito);

        spDistrito.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int dep,dis;
        switch (adapterView.getId()){
            case R.id.spDepartamento:
                dep = Departamento.listaDep.get(i).getCodigoDepartamento();
                cargarDatosSpinnerProvincia(dep);
                break;
            case R.id.spProvincia:
                dep = Provincia.listaPro.get(i).getCodigoDepartamento();
                dis = Provincia.listaPro.get(i).getCodigoProvincia();
                cargarDatosSpinnerDistrito(dep,dis);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
