package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Collections;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static List<Pelicula> peliculas = new ArrayList<Pelicula>(); ///ordenar por genero, dentro por popularidad
    static List<Cliente> clientes = new ArrayList<Cliente>();
    static List<Boleta> boletas = new ArrayList<Boleta>();
    ///que vendria a ser boleta? algo que tiene cliente y dueño?

    public static void main(String[] args) {
        ingresarPelicula();
        ingresarCliente();
        solicitarAlquilerPelicula();
        devoluciones(54544545, "adsadjas");///
        alquileresvigentes();
        devolucionesDeHoy();
        verUltimosAlquileresClientes();
        verInfoPeliculax();
        scan.close();
        Collections.sort(peliculas);
    }

    public static void ingresarPelicula() {
        int genero, clasificacion;
        String generoEnvio = "unrated", clasificacionEnvio = "unrated";

        System.out.println("ingrese nombre pelicula");
        String titulo = scan.nextLine();
        System.out.println("ingrese numero de genero:\n(1:ACCION  2:AVENTURA  3:COMEDIA " +
                "4:DOCUMENTAL  5:DRAMA  6:HORROR");
        do {
            genero = scan.nextInt();
            switch (genero) {
                case 1 -> generoEnvio = "accion";
                case 2 -> generoEnvio = "aventura";
                case 3 -> generoEnvio = "comedia";
                case 4 -> generoEnvio = "documental";
                case 5 -> generoEnvio = "drama";
                case 6 -> generoEnvio = "horror";
                default -> System.out.println("error, ingrese de nuevo:");
            }
        } while (genero > 6 || genero < 1);

        System.out.println("ingrese clasificacion:\n(1:G  2:PG  3:PG-13  4:R  5:NC-17  6:UNRATED");
        do {
            clasificacion = scan.nextInt();
            switch (clasificacion) {
                case 1 -> clasificacionEnvio = "g";
                case 2 -> clasificacionEnvio = "pg";
                case 3 -> clasificacionEnvio = "pg-13";
                case 4 -> clasificacionEnvio = "r";
                case 5 -> clasificacionEnvio = "nc-17";
                case 6 -> clasificacionEnvio = "unrated";
                default -> System.out.println("error, ingrese de nuevo:");
            }
        } while (clasificacion > 6 || clasificacion < 1);

        System.out.println("ingrese duracion:");
        int duracion = scan.nextInt();
        scan.nextLine();

        System.out.println("ingrese pais (en minusculas):");
        String pais = scan.nextLine();////////////////////////////////////////////// no toma

        System.out.println("ingrese breve descripcion del film:");
        String descripcion = scan.nextLine();

        System.out.println("ingrese año lanzamiento:");
        int fechaLanz = scan.nextInt();
        scan.nextLine();

        Pelicula peliculax = new Pelicula(titulo, fechaLanz, duracion, generoEnvio, clasificacionEnvio, pais, descripcion);
        peliculas.add(peliculax);//non-static field 'peliculas' cannot be referenced from a static context
    }

    public static void ingresarCliente() {
        System.out.println("ingrese nombre cliente:");
        String nombre = scan.nextLine();

        System.out.println("ingrese direccion cliente:");
        String direccion = scan.nextLine();

        System.out.println("ingrese telefono cliente:");
        int telefono = scan.nextInt();
        scan.nextLine();

        Cliente clientex = new Cliente(nombre, direccion, telefono);
        clientes.add(clientex);
    }

    public static void solicitarAlquilerPelicula() {
        String titulo;
        boolean prosigo = false;
        int i = 0, y = 0, telefono;

        System.out.println("ingrese la pelicula a buscar");
        titulo = scan.nextLine();
        while (!prosigo && i < peliculas.size()) { ///not cambia el false? (parece q no)
            if (titulo.equalsIgnoreCase(peliculas.get(i).getTitulo())) {
                System.out.println("pelicula encontrada."); ///q no haga de mas
                if (peliculas.get(i).getCopias() > 0) {
                    System.out.println("pelicula en stock!");
                    peliculas.get(i).alquiloCopia();
                    prosigo = true;
                } else
                    System.out.println("sin stock!");
            } else {
                System.out.println("pelicula inexistente");
            }
            i++;
        }
        if (prosigo) {
            System.out.println("ingrese telefono cliente");
            telefono = scan.nextInt();
            scan.nextLine();
            prosigo = false;
            while (!prosigo && y < clientes.size()) {
                if (telefono == clientes.get(y).getTelefono()) {
                    System.out.println("cliente encontrado.");
                    clientes.get(y).alquilerRealizado(peliculas.get(i));
                    generarBoleta(clientes.get(y), peliculas.get(i));
                    prosigo = true;
                }
                y++;
            }
            if (!prosigo) {
                ingresarCliente();
                clientes.get(y).alquilerRealizado(peliculas.get(i));
                generarBoleta(clientes.get(y), peliculas.get(i));
            }
        }
    }

    public static void generarBoleta(Cliente cliente, Pelicula pelicula) {
        Boleta boleteax = new Boleta(cliente, pelicula);
        boletas.add(boleteax);
    }

    public static void devoluciones(int celular, String pelicula) { ///podria clase?
        int i = 0;
        boolean control = false;
        while (i < clientes.size() && celular != clientes.get(i).getTelefono())
            i++;

        //if (clientes.get(i) != null)??????????????????????????????????????????
        if (i < clientes.size())
            clientes.get(i).alquilerDevuelto(pelicula);///verificar exista
        else
            System.out.println("cliente no encontrado");

        i = 0;
        ///acceder a lista desde aca, pero si tengo mas boletas puede q devuelva otra?
        while (i < peliculas.size()) {  ///do while arrancando de -1 alternativa a 148 arrancando de -1
            //|| que la devolucion la haga la boleta
            if (pelicula.equalsIgnoreCase(peliculas.get(i).getTitulo())) {
                control = true;
                break;
            }
            i++;
        }
        if (control)
            peliculas.get(i).devolucionCopia();
        else
            System.out.println("error, no esta la pelicula");
    }

    public static void alquileresvigentes() {
        System.out.println("lista de alquileres vigentes:");
        for (Cliente clientex : clientes) {
            if (clientex.getAlqVigentes().size() > 0)
                System.out.println("alquileres vigentes de " + clientex.getNombre() + "  -->" + clientex.getTelefono());
            for (Pelicula peliculax : clientex.getAlqVigentes())
                System.out.print(peliculax.getTitulo() + " // ");
        }
    }

    public static void devolucionesDeHoy() {
        System.out.println("devoluciones esperadas para el dia de hoy:");
        for (Boleta boletax : boletas) {
            if (boletax.getFechaDevolucion().equals(LocalDate.now()))
                System.out.println(boletax.clientex.getNombre() + "hoy debe devoler " + boletax.peliculax.getTitulo());
        }
    }

    public static void verUltimosAlquileresClientes() {
        int i = 0;
        for (Cliente clientex : clientes) {
            if (clientex.getUltAlquileres().size() > 0) {
                System.out.println("ultimos alquileres de " + clientex.getNombre() + "  -->" + clientex.getTelefono());
                for (Pelicula peliculax : clientex.getUltAlquileres()) {
                    System.out.print(peliculax.getTitulo() + " // ");
                    i++;
                    if (i == 9)
                        break;
                }
            }
        }
    }

    public static void verInfoPeliculax() {
        String tituPelicula;
        int i = 0;
        boolean control = false;
        System.out.println("ingrese la pelicula para ver informacion detallada"); //ingresar clase?
        tituPelicula = scan.nextLine();
        while (i < peliculas.size() && !control) {
            if (tituPelicula.equalsIgnoreCase(peliculas.get(i).getTitulo())) {
                control = true;
                System.out.println("----------------------------------------");
                System.out.println("pelicula:" + peliculas.get(i).getTitulo());
                System.out.println("copias disponibles: " + peliculas.get(i).getCopias());
                System.out.println("genero: " + peliculas.get(i).getGenero());
                System.out.println("duracion: " + peliculas.get(i).getDuracion());
                System.out.println("lanzamiento: " + peliculas.get(i).getFechaLanzamiento());
                System.out.println("clasificacion: " + peliculas.get(i).getClasificacion());
                System.out.println("pais: " + peliculas.get(i).getPais());
                System.out.println("descripcion: " + peliculas.get(i).getDescripcion());
                System.out.println("veces alquilada: " + peliculas.get(i).getVecesAlquilado());
                System.out.println("----------------------------------------");
            }
            i++;
        }
        if (!control)
            System.out.println("pelicula no encontrada (-info-)");
    }
}
