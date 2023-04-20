package org.uv.programa04cc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angel
 */
public class ConexionDB {

    private static ConexionDB cx = null;

    public static ConexionDB getInstance() {
        if (cx == null) {
            cx = new ConexionDB();
        }
        return cx;
    }
    private Connection con = null;

    private ConexionDB() {

        try {
            String url = "jdbc:postgresql://localhost:7000/emp";
            String usuario = "postgres";
            String contra = "password";
            con = DriverManager.getConnection(url, usuario, contra);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public boolean execute(String sql){
        return true;
    }
    public boolean execute(TransactionDB tdb){
        return tdb.execute(con);
    }

}
