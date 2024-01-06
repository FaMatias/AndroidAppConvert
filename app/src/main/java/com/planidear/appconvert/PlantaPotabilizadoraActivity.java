package com.planidear.appconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.planidear.appconvert.PlantaPotabilizadora.R;
import com.planidear.appconvert.PlantaPotabilizadora;

public class PlantaPotabilizadoraActivity extends AppCompatActivity {

    // Declarar las variables para los componentes gráficos
    private EditText editTextCaudal;
    private EditText editTextCloro;
    private EditText editTextSulfato;
    private EditText editTextPolielectrolitonoionico;
    private EditText editTextVelocidad;
    private Button buttonCalcular;
    private TextView textViewResultados;

    // Declarar la variable para la instancia de la clase PlantaPotabilizadora
    private PlantaPotabilizadora plantaPotabilizadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantapotabilizadora);

        // Obtener los componentes gráficos por su id
        editTextCaudal = findViewById(R.id.editTextCaudal);
        editTextCloro = findViewById(R.id.editTextCloro);
        editTextSulfato = findViewById(R.id.editTextSulfato);
        editTextPolielectrolitonoionico = findViewById(R.id.editTextPolielectrolitonoionico);
        editTextVelocidad = findViewById(R.id.editTextVelocidad);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        textViewResultados = findViewById(R.id.textViewResultados);

        // Crear la instancia de la clase PlantaPotabilizadora con valores por defecto
        plantaPotabilizadora = new PlantaPotabilizadora(0, 0, 0, 0, 0);

        // Asignar un listener al botón para que ejecute una acción al hacer clic
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores que el usuario ingresa en los campos de texto
                String textoCaudal = editTextCaudal.getText().toString();
                String textoCloro = editTextCloro.getText().toString();
                String textoSulfato = editTextSulfato.getText().toString();
                String textoPolielectrolitonoionico = editTextPolielectrolitonoionico.getText().toString();
                String textoVelocidad = editTextVelocidad.getText().toString();

                // Verificar que los campos de texto no estén vacíos
                if (!textoCaudal.isEmpty() && !textoCloro.isEmpty() && !textoSulfato.isEmpty() && !textoPolielectrolitonoionico.isEmpty() && !textoVelocidad.isEmpty()) {
                    // Convertir los textos a valores numéricos
                    double valorCaudal = Double.parseDouble(textoCaudal);
                    double valorCloro = Double.parseDouble(textoCloro);
                    double valorSulfato = Double.parseDouble(textoSulfato);
                    double valorPolielectrolitonoionico = Double.parseDouble(textoPolielectrolitonoionico);
                    double valorVelocidad = Double.parseDouble(textoVelocidad);

                    // Asignar los valores numéricos a los atributos de la instancia de la clase PlantaPotabilizadora
                    plantaPotabilizadora.setCaudal(valorCaudal);
                    plantaPotabilizadora.setCloro(valorCloro);
                    plantaPotabilizadora.setSulfato(valorSulfato);
                    plantaPotabilizadora.setPolielectrolitonoionico(valorPolielectrolitonoionico);
                    plantaPotabilizadora.setVelocidad(valorVelocidad);

                    // Llamar a los métodos de la clase PlantaPotabilizadora para calcular el caudal, la dosificación y la velocidad
                    double caudalCalculado = plantaPotabilizadora.calcularCaudal();
                    double[] dosisCalculadas = plantaPotabilizadora.calcularDosificacion();
                    double velocidadCalculada = plantaPotabilizadora.calcularVelocidad();

                    // Mostrar los resultados en el texto correspondiente
                    textViewResultados.setText("Resultados:\n" +
                            "Caudal: " + caudalCalculado + " m3/s\n" +
                            "Dosis de cloro: " + dosisCalculadas[0] + " kg/h\n" +
                            "Dosis de sulfato: " + dosisCalculadas[1] + " kg/h\n" +
                            "Dosis de polielectrolito no ionico: " + dosisCalculadas[2] + " kg/h\n" +
                            "Velocidad: " + velocidadCalculada + " m/h");
                } else {
                    // Mostrar un mensaje de error si alguno de los campos de texto está vacío
                    Toast.makeText(PlantaPotabilizadoraActivity.this, "Por favor, ingrese todos los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}