package com.planidear.appconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

  private EditText TextName, TxtEmail, TxtPassword;
  Button btnRegistro;
  String url2 = "https://planidear.com.ar/ConexionAndroid/register.php";

  String message;
  public static final String EXTRA_MESSAGE = "com.planidear.appconvert.MESSAGE";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registro);

    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);
//Inicio video
    VideoView videoView = findViewById(R.id.videoView);
    String videop = "android.resource://" + getPackageName() + "/" + R.raw.video1;
    Uri uri = Uri.parse(videop);
    videoView.setVideoURI(uri);
    MediaController mediaController = new MediaController(this);
    videoView.setMediaController(mediaController);
    mediaController.setAnchorView(videoView);

    //Final video

    TextName = findViewById(R.id.TextName);
    TxtEmail = findViewById(R.id.TxtEmail);
    TxtPassword = findViewById(R.id.TxtPassword);
    btnRegistro = findViewById(R.id.btnRegistro);

    btnRegistro .setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        registroUsuario(url2);
      }
    });
  }

  private void registroUsuario(String URL) {
    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String> () {
      @Override
      public void onResponse(String response){
        if(response.equals("Registrado")){
          Toast.makeText(RegistroActivity.this, "Registrado", Toast.LENGTH_LONG).show();
          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
          startActivity(intent);
          EditText editText = (EditText) findViewById(R.id.username);
          String message = editText.getText().toString();
          intent.putExtra(EXTRA_MESSAGE, message);
          startActivity(intent);


        }else{
          Toast.makeText(RegistroActivity.this, "Error al querer ingresar", Toast.LENGTH_LONG).show();
                   /* Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(intent);*/
        }

      }
    }, new Response.ErrorListener(){
      @Override
      public void onErrorResponse(VolleyError error) {
        Toast.makeText(RegistroActivity.this, error.toString(), Toast.LENGTH_LONG).show();
      }

    }){
      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put( "name", TextName.getText().toString().trim());
        params.put( "email", TxtEmail.getText().toString().trim());
        params.put( "password", TxtPassword.getText().toString().trim());
        return params;
      }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(RegistroActivity.this);
    requestQueue.add(stringRequest);
  }
}