
package org.uv.programa04cc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angel
 */
public class DaoEmpleado implements IDaoGeneral<Empleado, Long>{

    @Override
    public Empleado create(Empleado p) {
        ConexionDB cx = ConexionDB.getInstance();
        TransactionDB tdb = new TransactionDB<Empleado>(p) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql = "insert into prueba (id, nombre, telefono, direccion)"
                            + " values (?,?,?,?)";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setLong(1, p.getClave());
                    pst.setString(2, p.getNombre());
                    pst.setString(3, p.getTelefono());
                    pst.setString(4, p.getDireccion());
                    pst.execute();
                    
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        cx.execute(tdb);
        return null;
    }

    @Override
    public List<Empleado> findAll() {
        List<Empleado> empleados= new ArrayList<>();
        ConexionDB cx= ConexionDB.getInstance();
        TransactionDB tbd=new TransactionDB<List<Empleado>>(empleados) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql="select * from empleado";
                    PreparedStatement psm= con.prepareStatement(sql);
                    psm.execute();
                    if(empleados!=null){
                        return true;
                    }
                    else{
                        return false;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        boolean resp=cx.execute(tbd);
        if (resp) {
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.INFO, "Lista empleados");
            return empleados;
        } else {
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.INFO, "Lista vacia");
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
       ConexionDB cx= ConexionDB.getInstance();
        TransactionDB tbd=new TransactionDB<Long>(id) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql="delete from empleado where clave=?";
                    PreparedStatement psm= con.prepareStatement(sql);
                    psm.setLong(1, id);
                    psm.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        
        boolean resp=cx.execute(tbd);
        if(resp){
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE,"Se ha eliminado");
            return true;
        }
        else{
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE,"Error");
            return false;
        }
    }

    @Override
    public Empleado update(Long id, Empleado p) {
ConexionDB cx= ConexionDB.getInstance();
        TransactionDB tbd=new TransactionDB<Empleado>(p) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql="update empleado set nombre=?, direccion=?, telefono=? where clave=?";
                    PreparedStatement psm= con.prepareStatement(sql);
                    psm.setLong(1, id);
                    psm.setString(2, p.getNombre());
                    psm.setString(3, p.getDireccion());
                    psm.setString(4, p.getTelefono());
                    psm.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        cx.execute(tbd);
        return p;
    }

    @Override
    public Empleado findByIdD(Long id) {
ConexionDB cx= ConexionDB.getInstance();
        Empleado p=new Empleado();
        TransactionDB tbd=new TransactionDB<Empleado>(p) {
            @Override
            public boolean execute(Connection con) {
                try {
                    String sql="select * from empleado where clave=?";
                    PreparedStatement psm= con.prepareStatement(sql);
                    psm.setLong(1, id);
                    psm.execute();
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
        };
        
        boolean resp=cx.execute(tbd);
        if(resp){
            return p;
        }
        else{
            Logger.getLogger(DaoEmpleado.class.getName()).log(Level.SEVERE,"Empleado no encontrado");
            return null;
        }    
    }
    
}