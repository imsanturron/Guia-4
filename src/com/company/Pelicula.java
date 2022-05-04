package com.company;

import java.util.Comparator;

public class Pelicula implements Comparable<Pelicula>, Comparator<Pelicula> {
    private String titulo;
    private int fechaLanzamiento;
    private int duracion;
    private String genero;
    private String clasificacion;
    private String pais;
    private String descripcion;
    private short copias;
    private int vecesAlquilado;

    public Pelicula(String titulo, int fechaLanzamiento, int duracion, String genero, String clasificacion,
                    String pais, String descripcion) {
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.duracion = duracion;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.pais = pais;
        this.descripcion = descripcion;
        copias = 10;
        vecesAlquilado = 0;
    }

    public String getTitulo() {
        return titulo;
    }

    public short getCopias() {
        return copias;
    }

    public void alquiloCopia() {
        copias--;
        vecesAlquilado++;
    }

    public void devolucionCopia() {
        copias++;
    }

    public int getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public String getPais() {
        return pais;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getVecesAlquilado() {
        return vecesAlquilado;
    }

    @Override
    public int compareTo(Pelicula o) {
        return Comparator.comparing(Pelicula::getGenero)
                .thenComparing(Pelicula::getVecesAlquilado)
                .compare(this, o);
    }

}
