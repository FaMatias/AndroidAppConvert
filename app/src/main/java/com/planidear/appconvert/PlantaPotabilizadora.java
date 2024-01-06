package com.planidear.appconvert;

public class PlantaPotabilizadora {

    // Atributos de la clase
    private double caudal; // Caudal de ingreso a la planta en m3/s
    private double cloro; // Concentración de cloro en mg/L
    private double sulfato; // Concentración de sulfato de aluminio en mg/L
    private double polielectrolitonoionico; // Concentración de polielectrolito no ionico en mg/L
    private double velocidad; // Velocidad de tratamiento de la planta en m/h

    // Constructor de la clase
    public PlantaPotabilizadora(double caudal, double cloro, double sulfato, double polielectrolitonoionico, double velocidad) {
        this.caudal = caudal;
        this.cloro = cloro;
        this.sulfato = sulfato;
        this.polielectrolitonoionico = polielectrolitonoionico;
        this.velocidad = velocidad;
    }

    // Métodos getters y setters de los atributos
    public double getCaudal() {
        return caudal;
    }

    public void setCaudal(double caudal) {
        this.caudal = caudal;
    }

    public double getCloro() {
        return cloro;
    }

    public void setCloro(double cloro) {
        this.cloro = cloro;
    }

    public double getSulfato() {
        return sulfato;
    }

    public void setSulfato(double sulfato) {
        this.sulfato = sulfato;
    }

    public double getPolielectrolitonoionico() {
        return polielectrolitonoionico;
    }

    public void setPolielectrolitonoionico(double polielectrolitonoionicoo) {
        this.polielectrolitonoionico = polielectrolitonoionico;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    // Métodos para calcular el caudal, la dosificación y la velocidad de la planta
    public double calcularCaudal() {
        // Fórmula para calcular el caudal de ingreso a la planta
        // Q = A * v
        // Donde Q es el caudal en m3/s, A es el área de la sección transversal del canal o tubería en m2, y v es la velocidad del flujo en m/s
        // Suponemos que el área es de 1 m2 y la velocidad es de 0.5 m/s
        double area = 1;
        double velocidadFlujo = 0.5;
        double caudal = area * velocidadFlujo;
        return caudal;
    }

    public double[] calcularDosificacion() {
        // Fórmula para calcular la dosificación de los químicos
        // D = Q * C * 3.6
        // Donde D es la dosificación en kg/h, Q es el caudal en m3/s, C es la concentración en mg/L, y 3.6 es un factor de conversión
        // Calculamos la dosificación de cloro, sulfato y polielectrolitonoionico, y las guardamos en un arreglo
        double dosisCloro = caudal * cloro * 3.6;
        double dosisSulfato = caudal * sulfato * 3.6;
        double dosisPolielectrolitonoionico = caudal * polielectrolitonoionico * 3.6;
        double[] dosis = {dosisCloro, dosisSulfato, dosisPolielectrolitonoionico};
        return dosis;
    }

    public double calcularVelocidad() {
        // Fórmula para calcular la velocidad de tratamiento de la planta
        // v = Q / A
        // Donde v es la velocidad en m/h, Q es el caudal en m3/h, y A es el área de la superficie del tanque o cámara en m2
        // Suponemos que el área es de 100 m2 y el caudal es de 1800 m3/h
        double area = 100;
        double caudal = 1800;
        double velocidad = caudal / area;
        return velocidad;
    }
}
