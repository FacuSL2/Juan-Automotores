package com.example.crud_php;

public class Vehiculos {
    private int id;
    private String comprador;
    private String km;
    private String foto;

    public Vehiculos(int id, String comprador, String km, String foto) {
        this.id = id;
        this.comprador = comprador;
        this.km = km;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return comprador;
    }

    public void setTitulo(String comprador) {
        this.comprador = comprador;
    }

    public String getPrecio() {
        return km;
    }

    public void setPrecio(String km) {
        this.km = km;
    }

    public String getImagen() {
        return foto;
    }

    public void setImagen(String foto) {
        this.foto = foto;
    }
}

