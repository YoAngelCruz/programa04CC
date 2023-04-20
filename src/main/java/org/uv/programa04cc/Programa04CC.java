package org.uv.programa04cc;

/**
 *
 * @author Angel
 */
public class Programa04CC {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        DaoEmpleado dao = new DaoEmpleado();
        Empleado emp = new Empleado();
        
        emp.setClave(6);
        emp.setNombre("Yooo");
        emp.setTelefono("12324534");
        emp.setDireccion("av. 1 ");
        
        dao.create(emp);
    }
}
