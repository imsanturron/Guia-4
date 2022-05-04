package com.company;
import java.time.LocalDate;

public class Boleta {
    public LocalDate fechaRetiro;
    public LocalDate fechaDevolucion;
    Cliente clientex;
    Pelicula peliculax;

    public Boleta(Cliente cliente, Pelicula pelicula){ ///igual q antes
        fechaRetiro = LocalDate.now();
        fechaDevolucion = fechaRetiro.plusDays(7);
        this.clientex = cliente;
        this.peliculax = pelicula;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
}


