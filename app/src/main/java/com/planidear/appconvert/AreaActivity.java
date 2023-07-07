package com.planidear.appconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AreaActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_area);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);
  }
  public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.overflow, menu);
    return true;
  }
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

}