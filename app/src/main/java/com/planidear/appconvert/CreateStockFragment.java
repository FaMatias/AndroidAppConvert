package com.planidear.appconvert;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CreateStockFragment extends DialogFragment {

  String id_stock;
  Button btn_add;
  EditText name, age, color, precio_vino;
  private FirebaseFirestore mfirestore;
  private FirebaseAuth mAuth;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments()!=null){
      id_stock = getArguments().getString("id_stock");
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_create_stock, container, false);
    mfirestore = FirebaseFirestore.getInstance();
    mAuth = FirebaseAuth.getInstance();

    name = v.findViewById(R.id.nombre);
    age = v.findViewById(R.id.edad);
    color = v.findViewById(R.id.color);
    precio_vino = v.findViewById(R.id.precio_vino);
    btn_add = v.findViewById(R.id.btn_add);

    if (id_stock==null || id_stock==""){
      btn_add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          String namestock = name.getText().toString().trim();
          String agestock = age.getText().toString().trim();
          String colorstock = color.getText().toString().trim();
          Double precio_vinostock = Double.parseDouble(precio_vino.getText().toString().trim());

          if(namestock.isEmpty() && agestock.isEmpty() && colorstock.isEmpty()){
            Toast.makeText(getContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
          }else{
            postStock(namestock, agestock, colorstock, precio_vinostock);
          }
        }
      });
    }else {
      getStock();
      btn_add.setText("update");
      btn_add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          String namestock = name.getText().toString().trim();
          String agestock = age.getText().toString().trim();
          String colorstock = color.getText().toString().trim();
          Double precio_vinostock = Double.parseDouble(precio_vino.getText().toString().trim());

          if(namestock.isEmpty() && agestock.isEmpty() && colorstock.isEmpty()){
            Toast.makeText(getContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
          }else{
            updateStock(namestock, agestock, colorstock, precio_vinostock);
          }
        }
      });
    }
    return v;
  }

  private void updateStock(String namestock, String agestock, String colorstock, Double precio_vinostock) {
    Map<String, Object> map = new HashMap<>();
    map.put("name", namestock);
    map.put("age", agestock);
    map.put("color", colorstock);
    map.put("wine_price", precio_vinostock);

    mfirestore.collection("stock").document(id_stock).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
      @Override
      public void onSuccess(Void unused) {
        Toast.makeText(getContext(), "Actualizado exitosamente", Toast.LENGTH_SHORT).show();
        getDialog().dismiss();
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Toast.makeText(getContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
        getDialog().dismiss();
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Toast.makeText(getContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void getStock(){
    mfirestore.collection("stock").document(id_stock).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
      @Override
      public void onSuccess(DocumentSnapshot documentSnapshot) {
        DecimalFormat format = new DecimalFormat("0.00");
//            format.setMaximumFractionDigits(2);
        String nameStock = documentSnapshot.getString("name");
        String ageStock = documentSnapshot.getString("age");
        String colorStock = documentSnapshot.getString("color");
        Double precio_vinostock = documentSnapshot.getDouble("wine_price");
        name.setText(nameStock);
        age.setText(ageStock);
        color.setText(colorStock);
        precio_vino.setText(format.format(precio_vinostock));
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
