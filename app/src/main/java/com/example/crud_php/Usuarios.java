package com.example.crud_php;

public class Usuarios {
    String id,comprador,modelo,patente, km,color, precioinfoautos, porcinfoautos, costo, valorlista, foto, fichauno, fichados,
    fichatres,fichacuatro,fichacinco,fichaseis,fichasiete,fichaocho,fichanueve,fichadiez;

    public Usuarios() {
    }

    public Usuarios(String id, String comprador,String modelo, String patente, String km, String color, String precioinfoautos, String porcinfoautos,
                    String costo,String valorlista,String foto, String fichauno, String fichados, String fichatres, String fichacuatro, String fichacinco, String fichaseis,
                    String fichasiete, String fichaocho, String fichanueve, String fichadiez) {
        this.id = id;
        this.comprador = comprador;
        this.modelo = modelo;
        this.patente = patente;
        this.km = km;
        this.color = color;
        this.precioinfoautos = precioinfoautos;
        this.porcinfoautos = porcinfoautos;
        this.costo = costo;
        this.valorlista = valorlista;
        this.foto = foto;
        this.fichauno = fichauno;
        this.fichados = fichados;
        this.fichatres = fichatres;
        this.fichacuatro = fichacuatro;
        this.fichacinco = fichacinco;
        this.fichaseis = fichaseis;
        this.fichasiete = fichasiete;
        this.fichaocho = fichaocho;
        this.fichanueve = fichanueve;
        this.fichadiez = fichadiez;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return comprador;
    }

    public void setNombre(String comprador) {
        this.comprador = comprador;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getCorreo() {
        return km;
    }

    public void setCorreo(String km) {
        this.km = km;
    }

    public String getDireccion() {
        return color;
    }

    public void setDireccion(String color) {
        this.color = color;
    }

    public String getPrecioinfoautos() {
        return precioinfoautos;
    }

    public void setPrecioinfoautos(String precioinfoautos) { this.precioinfoautos = precioinfoautos;}

    public String getPorcinfoautos() {
        return porcinfoautos;
    }

    public void setPorcinfoautos(String porcinfoautos) { this.porcinfoautos = porcinfoautos;}

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) { this.costo = costo;}

    public String getValorlista() {
        return valorlista;
    }

    public void setValorlista(String valorlista) { this.valorlista = valorlista;}

    public String getFichauno() {
        return fichauno;
    }

    public void setFichauno(String fichauno) { this.fichauno = fichauno;}

    public String getFichados() {
        return fichados;
    }

    public void setFichados(String fichados) { this.fichados = fichados;}

    public String getFichatres() {
        return fichatres;
    }

    public void setFichatres(String fichatres) { this.fichatres = fichatres;}

    public String getFichacuatro() {
        return fichacuatro;
    }

    public void setFichacuatro(String fichacuatro) { this.fichacuatro = fichacuatro;}

    public String getFichacinco() {
        return fichacinco;
    }

    public void setFichacinco(String fichacinco) { this.fichacinco = fichacinco;}

    public String getFichaseis() {
        return fichaseis;
    }

    public void setFichaseis(String fichaseis) { this.fichaseis = fichaseis;}

    public String getFichasiete() {
        return fichasiete;
    }

    public void setFichasiete(String fichasiete) { this.fichasiete = fichasiete;}

    public String getFichaocho() {
        return fichaocho;
    }

    public void setFichaocho(String fichaocho) { this.fichaocho = fichaocho;}

    public String getFichanueve() {
        return fichanueve;
    }

    public void setFichanueve(String fichanueve) { this.fichanueve = fichanueve;}

    public String getFichadiez() {
        return fichadiez;
    }

    public void setFichadiez(String fichadiez) { this.fichadiez = fichadiez;}



    public String getImagen() {
        return foto;
    }

    public void setImagen(String foto) {
        this.foto = foto;
    }



}
