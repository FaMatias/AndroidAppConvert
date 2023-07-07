package com.planidear.appconvert;
//https://youtu.be/dp_ruQOP1sU
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

public class LongitudActivity extends AppCompatActivity {

    //para comunicarse con la parte grafica de la activity
    private Spinner spinner1, spinner2;
    private EditText etValor;
    private TextView tvResul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longitud);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
      getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);

//findViewById para conectarlo desde la grafica
etValor = (EditText) findViewById(R.id.txt_Valor1);
tvResul = (TextView) findViewById(R.id.tv_resultado);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        //creamos un array
String [] opciones1 = {"Metro","Centimetro","Milimetro","Km","Pulgada"};
String [] opciones2 = {"Centimetro","Milimetro","Km","Pulgada","Metro"};

//Para comunicar con el spinner:
        ArrayAdapter <String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item_long, opciones1 );
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
      Toast.makeText(LongitudActivity.this, "Debes ingresar un numero", Toast.LENGTH_LONG).show();
    }

      else if (seleccion1.equals("Metro") & seleccion2.equals("Centimetro")) {
      Double ConvMetro = valor_int * 100;
      //ya tenemos la cuenta
      //para pasar de entero a stringo :String.valueOf(ConvMetro)
      String resultado = String.valueOf(ConvMetro);

      //Metro
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Metro") & seleccion2.equals("Milimetro")) {
      Double ConvMetro = valor_int * 1000;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Metro") & seleccion2.equals("Km")) {
      Double ConvMetro = valor_int / 1000;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Metro") & seleccion2.equals("Metro")) {
      Double ConvMetro = valor_int * 1;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Metro") & seleccion2.equals("Pulgada")) {
      Double ConvMetro = valor_int * 39.3701;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);


  //Centimetro
    } else if (seleccion1.equals("Centimetro") & seleccion2.equals("Metro")) {
      Double ConvMetro = valor_int / 100;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Centimetro") & seleccion2.equals("Milimetro")) {
      Double ConvMetro = valor_int * 10;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Centimetro") & seleccion2.equals("Km")) {
      Double ConvMetro = valor_int / 100000;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Centimetro") & seleccion2.equals("Pulgada")) {
      Double ConvMetro = valor_int * 0.393701;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Centimetro") & seleccion2.equals("Centimetro")) {
      Double ConvMetro = valor_int * 1;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);

  //Milimetro
    } else if (seleccion1.equals("Milimetro") & seleccion2.equals("Metro")) {
      Double ConvMetro = valor_int / 1000;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Milimetro") & seleccion2.equals("Centimetro")) {
      Double ConvMetro = valor_int / 10;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Milimetro") & seleccion2.equals("Km")) {
      Double ConvMetro = valor_int / 1000000;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Milimetro") & seleccion2.equals("Pulgada")) {
      Double ConvMetro = valor_int * 0.03937;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Milimetro") & seleccion2.equals("Milimetro")) {
      Double ConvMetro = valor_int * 1;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    }
    //Km
    else if (seleccion1.equals("Km") & seleccion2.equals("Metro")) {
      Double ConvMetro = valor_int * 1000;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Km") & seleccion2.equals("Centimetro")) {
      Double ConvMetro = valor_int * 100000;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Km") & seleccion2.equals("Milimetro")) {
      Double ConvMetro = valor_int * 1000000;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Km") & seleccion2.equals("Pulgada")) {
      Double ConvMetro = valor_int * 39370.08;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Km") & seleccion2.equals("Km")) {
      Double ConvMetro = valor_int * 1;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    }

    //Pulgada
    else if (seleccion1.equals("Pulgada") & seleccion2.equals("Metro")) {
      Double ConvMetro = valor_int *0.0254;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Pulgada") & seleccion2.equals("Centimetro")) {
      Double ConvMetro = valor_int * 2.54;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Pulgada") & seleccion2.equals("Milimetro")) {
      Double ConvMetro = valor_int * 25.4;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Pulgada") & seleccion2.equals("Km")) {
      Double ConvMetro = valor_int * 0.0000254;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    } else if (seleccion1.equals("Pulgada") & seleccion2.equals("Pulgada")) {
      Double ConvMetro = valor_int * 1;
      String resultado = String.valueOf(ConvMetro);
      tvResul.setText(resultado);
    }
else {
        Toast.makeText(LongitudActivity.this, "ERROR DE VALOR", Toast.LENGTH_LONG).show();
      }


  }

}