package Estructuras;

public class Paciente {
    private int id; //datos personales    
    private String nombre;    
    private String sexo;
    private int edad;    
    private String fechaNacimiento; 
    
    //registros
    private String diagnostico;
    private String motivo;
    private String prioridad;

    public Paciente(int id, String nombre, String sexo, int edad,  String fechaNacimiento, String motivo, String diagnostico, String prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.prioridad = prioridad;
    }
    
    
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Paciente{" + "id=" + id + ", nombre=" + nombre + ", sexo=" + sexo + ", edad=" + edad + ", fechaNacimiento=" + fechaNacimiento + ", diagnostico=" + diagnostico + ", motivo=" + motivo + ", prioridad=" + prioridad + '}';
    }
}

//(id, nombre, sexo, fecha de nacimiento, diagnóstico y algún otro si lo desea)
//(id, nombre, edad, motivo, prioridad y algún otro si lo desea)
//nombre, sexo, edad, fecha de nacimiento, diagnostico, motivo, prioridad