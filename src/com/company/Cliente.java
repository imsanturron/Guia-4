package com.company;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String direccion;
    private int telefono;
    private List<Pelicula> alqVigentes = new ArrayList<Pelicula>(); /// o hacer una lista de alquileres vigentes
    private List<Pelicula> ultAlquileres = new ArrayList<Pelicula>(); ///not private?? // con linked list vig y no vig?

    public Cliente(String nombre, String direccion, int telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void alquilerRealizado(Pelicula peliculax) { ////////////////////
        alqVigentes.add(peliculax);
    }

    public void alquilerDevuelto(String pelicula) {
        int i = 0;
        boolean control = false;

        while (i < alqVigentes.size()) {
            if (pelicula.equalsIgnoreCase(alqVigentes.get(i).getTitulo())) {
                control = true;
                break;
            }
            i++;
        }

        if (control)
        ultAlquileres.add(alqVigentes.remove(i));
    }

    public List<Pelicula> getAlqVigentes() {
        return alqVigentes;
    }

    public List<Pelicula> getUltAlquileres() {
        return ultAlquileres;
    }
}
