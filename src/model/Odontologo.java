package model;

public class Odontologo {
    private Integer id;
    private Integer numeromatricula;
    private String nombre;
    private String apellido;

    public Odontologo(Integer numeromatricula, String nombre, String apellido) {
        this.numeromatricula = numeromatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(Integer id, Integer numeromatricula, String nombre, String apellido) {
        this.id = id;
        this.numeromatricula = numeromatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeromatricula() {
        return numeromatricula;
    }

    public void setNumeromatricula(Integer numeromatricula) {
        this.numeromatricula = numeromatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", numeromatricula=" + numeromatricula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
