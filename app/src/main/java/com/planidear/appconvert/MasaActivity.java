package com.planidear.appconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MasaActivity extends AppCompatActivity {

  //para comunicarse con la parte grafica de la activity
  private Spinner spinner1, spinner2;
  private EditText etValor;
  private TextView tvResul;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_masa);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);

    //findViewById para conectarlo desde la grafica
    etValor = (EditText) findViewById(R.id.txt_Valor1);
    tvResul = (TextView) findViewById(R.id.tv_resultado);
    spinner1 = (Spinner) findViewById(R.id.spinner);
    spinner2 = (Spinner) findViewById(R.id.spinner2);
    //creamos un array
    String [] opciones1 = {"Tonelada", "Kilogramo", "Gramo", "Libra", "Onza"};
    String [] opciones2 = {"Kilogramo","Gramo","Libra","Onza", "Tonelada"};

//Para comunicar con el spinner:
    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item_long, opciones1 );
    //para conectar el array con el spinner
    spinner1.setAdapter(adapter1);
//R.layout.spinner_item_long para darle nuestro estilo
    ArrayAdapter <String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item_long, opciones2 );
    //para conectar el array con el spinner
    spinner2.setAdapter(adapter2);
  }

  //para mostar y ocultar el menu
  //https://youtu.be/yE_NFOzpiGQ
  public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.overflow, menu);
    return true;
  }
  //Metodo para asignar las funiones acorrespondiente a las opcines
  public  boolean onOptionsItemSelected(MenuItem item){
    int id = item.getItemId();//para recuperar el item que se esta seleccionando
    if(id == R.id.itemLong){
      Toast.makeText(this, "Longitud", Toast.LENGTH_LONG).show();
      //para abrir el activity
      Intent intent = new Intent(getApplicationContext(), LongitudActivity.class);
      startActivity(intent);
    }else if(id == R.id.itemMasa){
      Toast.makeText(this, "Masa", Toast.LENGTH_LONG).show();
      Intent intent = new Intent(getApplicationContext(), MasaActivity.class);
      startActivity(intent);
    }else if(id == R.id.itemVolum){
      Toast.makeText(this, "Volumen", Toast.LENGTH_LONG).show();
      Intent intent = new Intent(getApplicationContext(), VolumenActivity.class);
      startActivity(intent);
    }else if(id == R.id.itemArea){
      Toast.makeText(this, "Area", Toast.LENGTH_LONG).show();
      Intent intent = new Intent(getApplicationContext(), AreaActivity.class);
      startActivity(intent);
    }

    return super.onOptionsItemSelected(item);

  }

  //metodo del boton
  //View view la libre ria que utilizamos
  public void Convertir(View view) {
    //para recuperra el valor
    String valor_string = etValor.getText().toString();

    //cambiar los valores a entero
    Double valor_int = Double.parseDouble(valor_string);

    String seleccion1 = spinner1.getSelectedItem().toString();
    String seleccion2 = spinner2.getSelectedItem().toString();
//seleccion1.equals compara cadena de texto

    if (valor_int ==0 ) {
      Toast.makeText(MasaActivity.this, "Debes ingresar un numero", Toast.LENGTH_LONG).show();

      //Tonelada
    } else if (seleccion1.equals("Tonelada") & seleccion2.equals("Gramo")) {
      Double ConvMetro = valor_int * 1000000;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Tonelada") & seleccion2.equals("Kilogramo")) {
      Double ConvMetro = valor_int * 1000;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Tonelada") & seleccion2.equals("Libra")) {
      Double ConvMetro = valor_int * 2204.62;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Tonelada") & seleccion2.equals("Onzas")) {
      Double ConvMetro = valor_int * 35273.94;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Tonelada") & seleccion2.equals("Tonelada")) {
      Double ConvMetro = valor_int * 1;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);


      //Gramo
    } else if (seleccion1.equals("Gramo") & seleccion2.equals("Tonelada")) {
      Double ConvMetro = valor_int * 0.000001;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Gramo") & seleccion2.equals("Kilogramo")) {
      Double ConvMetro = valor_int * 0.001;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Gramo") & seleccion2.equals("Libra")) {
      Double ConvMetro = valor_int * 0.00220462;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Gramo") & seleccion2.equals("Onzas")) {
      Double ConvMetro = valor_int *  0.035274;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Gramo") & seleccion2.equals("Gramo")) {
      Double ConvMetro = valor_int * 1;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);

      //Kilogramo
    } else if (seleccion1.equals("Kilogramo") & seleccion2.equals("Libra")) {
      Double ConvMetro = valor_int * 2.2046;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Kilogramo") & seleccion2.equals("Onzas")) {
      Double ConvMetro = valor_int * 35.2739;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Kilogramo") & seleccion2.equals("Tonelada")) {
      Double ConvMetro = valor_int / 1000;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Kilogramo") & seleccion2.equals("Gramo")) {
      Double ConvMetro = valor_int * 1000;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Kilogramo") & seleccion2.equals("Kilogramo")) {
      Double ConvMetro = valor_int * 1;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    }
    //Libra
    else if (seleccion1.equals("Libra") & seleccion2.equals("Onzas")) {
      Double ConvMetro = valor_int * 16;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Libra") & seleccion2.equals("Tonelada")) {
      Double ConvMetro = valor_int * 0.00045359;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Libra") & seleccion2.equals("Gramo")) {
      Double ConvMetro = valor_int * 453.59;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Libra") & seleccion2.equals("Kilogramo")) {
      Double ConvMetro = valor_int * 0.453592;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Libra") & seleccion2.equals("Libra")) {
      Double ConvMetro = valor_int * 1;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    }

    //Onzas
    else if (seleccion1.equals("Onzas") & seleccion2.equals("Tonelada")) {
      Double ConvMetro = valor_int * 0.0000283495;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Onzas") & seleccion2.equals("Gramo")) {
      Double ConvMetro = valor_int * 28.3495;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Onzas") & seleccion2.equals("Kilogramo")) {
      Double ConvMetro = valor_int * 0.02835;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Onzas") & seleccion2.equals("Libra")) {
      Double ConvMetro = valor_int * 0.0625;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Onzas") & seleccion2.equals("Onzas")) {
      Double ConvMetro = valor_int * 1;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    }
    else {
      Toast.makeText(MasaActivity.this, "ERROR DE VALOR", Toast.LENGTH_LONG).show();
    }


  }

}

