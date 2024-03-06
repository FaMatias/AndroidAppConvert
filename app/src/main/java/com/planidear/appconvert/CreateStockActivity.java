package com.planidear.appconvert;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CreateStockActivity extends AppCompatActivity {

  ImageView photo_stock;
  Button btn_add;
  Button btn_cu_photo, btn_r_photo;
  LinearLayout linearLayout_image_btn;
  EditText name, age, color, precio_vino;
  private FirebaseFirestore mfirestore;
  private FirebaseAuth mAuth;

  StorageReference storageReference;
  String storage_path = "stock/*";

  private static final int COD_SEL_STORAGE = 200;
  private static final int COD_SEL_IMAGE = 300;

  private Uri image_url;
  String photo = "photo";
  String idd;

  ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_stock);
    this.setTitle("Vino");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    progressDialog = new ProgressDialog(this);
    String id = getIntent().getStringExtra("id_stock");
    mfirestore = FirebaseFirestore.getInstance();
    mAuth = FirebaseAuth.getInstance();
    storageReference = FirebaseStorage.getInstance().getReference();

    linearLayout_image_btn = findViewById(R.id.images_btn);
    name = findViewById(R.id.nombre);
    age = findViewById(R.id.edad);
    color = findViewById(R.id.color);
    precio_vino = findViewById(R.id.precio_vino);
    photo_stock = findViewById(R.id.stock_photo);
    btn_cu_photo = findViewById(R.id.btn_photo);
    btn_r_photo = findViewById(R.id.btn_remove_photo);
    btn_add = findViewById(R.id.btn_add);

    btn_cu_photo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        uploadPhoto();
      }
    });

    btn_r_photo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("photo", "");
        mfirestore.collection("stock").document(idd).update(map);
        Toast.makeText(CreateStockActivity.this, "Foto eliminada", Toast.LENGTH_SHORT).show();
      }
    });

    if (id == null || id == ""){
      linearLayout_image_btn.setVisibility(View.GONE);
      btn_add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          String namestock = name.getText().toString().trim();
          String agestock = age.getText().toString().trim();
          String colorstock = color.getText().toString().trim();
          Double precio_vinostock = Double.parseDouble(precio_vino.getText().toString().trim());

          if(namestock.isEmpty() && agestock.isEmpty() && colorstock.isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
          }else{
            postStock(namestock, agestock, colorstock, precio_vinostock);
          }
        }
      });
    }else{
      idd = id;
      btn_add.setText("Update");
      getStock(id);
      btn_add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          String namestock = name.getText().toString().trim();
          String agestock = age.getText().toString().trim();
          String colorstock = color.getText().toString().trim();
          Double precio_vinostock = Double.parseDouble(precio_vino.getText().toString().trim());

          if(namestock.isEmpty() && agestock.isEmpty() && colorstock.isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
          }else{
            updateStock(namestock, agestock, colorstock, precio_vinostock, id);
          }
        }
      });
    }
  }

  private void uploadPhoto() {
    Intent i = new Intent(Intent.ACTION_PICK);
    i.setType("image/*");

    startActivityForResult(i, COD_SEL_IMAGE);
  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if(resultCode == RESULT_OK){
      if (requestCode == COD_SEL_IMAGE){
        image_url = data.getData();
        subirPhoto(image_url);
      }
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  private void subirPhoto(Uri image_url) {
    progressDialog.setMessage("Actualizando foto");
    progressDialog.show();
    String rute_storage_photo = storage_path + "" + photo + "" + mAuth.getUid() +""+ idd;
    StorageReference reference = storageReference.child(rute_storage_photo);
    reference.putFile(image_url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
      @Override
      public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
        while (!uriTask.isSuccessful());
        if (uriTask.isSuccessful()){
          uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
              String download_uri = uri.toString();
              HashMap<String, Object> map = new HashMap<>();
              map.put("photo", download_uri);
              mfirestore.collection("stock").document(idd).update(map);
              Toast.makeText(CreateStockActivity.this, "Foto actualizada", Toast.LENGTH_SHORT).show();
              progressDialog.dismiss();
            }
          });
        }
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Toast.makeText(CreateStockActivity.this, "Error al cargar foto", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void updateStock(String namestock, String agestock, String colorstock, Double precio_vinostock, String id) {
    Map<String, Object> map = new HashMap<>();
    map.put("name", namestock);
    map.put("age", agestock);
    map.put("color", colorstock);
    map.put("Wine_price", precio_vinostock);

    mfirestore.collection("stock").document(id).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
      @Override
      public void onSuccess(Void unused) {
        Toast.makeText(getApplicationContext(), "Actualizado exitosamente", Toast.LENGTH_SHORT).show();
        finish();
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Toast.makeText(getApplicationContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void postStock(String namestock, String agestock, String colorstock, Double precio_vinostock) {
    String idUser = mAuth.getCurrentUser().getUid();
    DocumentReference id = mfirestore.collection("stock").document();

    Map<String, Object> map = new HashMap<>();
    map.put("id_user", idUser);
    map.put("id", id.getId());
    map.put("name", namestock);
    map.put("age", agestock);
    map.put("color", colorstock);
    map.put("wine_price", precio_vinostock);

    mfirestore.collection("stock").document(id.getId()).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
      @Override
      public void onSuccess(Void unused) {
        Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
        finish();
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Toast.makeText(getApplicationContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void getStock(String id){
    mfirestore.collection("stock").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
      @Override
      public void onSuccess(DocumentSnapshot documentSnapshot) {
        DecimalFormat format = new DecimalFormat("0.00");
        String nameStock = documentSnapshot.getString("name");
        String ageStock = documentSnapshot.getString("age");
        String colorStock = documentSnapshot.getString("color");
        Double precio_vinostock = documentSnapshot.getDouble("wine_price");
        String photoStock = documentSnapshot.getString("photo");

        name.setText(nameStock);
        age.setText(ageStock);
        color.setText(colorStock);
        precio_vino.setText(format.format(precio_vinostock));
        try {
          if(!photoStock.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Cargando foto", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP,0,200);
            toast.show();
            Picasso.with(CreateStockActivity.this)
                    .load(photoStock)
                    .resize(150, 150)
                    .into(photo_stock);
          }
        }catch (Exception e){
          Log.v("Error", "e: " + e);
        }
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Toast.makeText(getApplicationContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return false;
  }
}
