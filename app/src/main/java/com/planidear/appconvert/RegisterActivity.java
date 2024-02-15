package com.planidear.appconvert;

import static com.planidear.appconvert.MainActivity.EXTRA_MESSAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

  Button btn_register;
  EditText name, email, password;
  FirebaseFirestore mFirestore;
  FirebaseAuth mAuth;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    this.setTitle("Registro");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    mFirestore = FirebaseFirestore.getInstance();
    mAuth = FirebaseAuth.getInstance();

    name = findViewById(R.id.nombre);
    email = findViewById(R.id.correo);
    password = findViewById(R.id.contrasena);
    btn_register = findViewById(R.id.btn_registro);

    btn_register.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        registerUser();
      }
    });

    //Inicio video
    VideoView videoView = findViewById(R.id.videoView);
    String videop = "android.resource://" + getPackageName() + "/" + R.raw.video1;
    Uri uri = Uri.parse(videop);
    videoView.setVideoURI(uri);
    MediaController mediaController = new MediaController(this);
    videoView.setMediaController(mediaController);
    mediaController.setAnchorView(videoView);
    //Final video
  }

  private void registerUser() {
    String nombre = name.getText().toString().trim();
    String correo = email.getText().toString().trim();
    String contrasena = password.getText().toString().trim();

    if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
      Toast.makeText(RegisterActivity.this, "Por favor, rellene todos los campos", Toast.LENGTH_LONG).show();
      return;
    }

    mAuth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
              @Override
              public void onSuccess(AuthResult authResult) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("name", nombre);
                map.put("email", correo);
                map.put("password", contrasena);
                mFirestore.collection("user").document(id)
                        .set(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                          @Override
                          public void onSuccess(Void aVoid) {
                            Toast.makeText(RegisterActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            String message = nombre;
                            intent.putExtra(EXTRA_MESSAGE, message);
                            startActivity(intent);
                            finish();
                          }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                          @Override
                          public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, "Error al guardar los datos", Toast.LENGTH_LONG).show();
                          }
                        });
              }
            })
            .addOnFailureListener(new OnFailureListener() {
              @Override
              public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Error al crear el usuario", Toast.LENGTH_LONG).show();
              }
            });
  }
}

//Fin