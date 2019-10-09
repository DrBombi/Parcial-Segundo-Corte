/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Estudiante
 */
public class Jdbc {
  
    private transient Connection con;
    private String jdbcDriver="com.mysql.jdbc.driver";
    private String databaseURL="jdbc:mysql://localhost:3306/solicituddecompra?serverTimezone=UTC";
    private String user="root";
    private String password="Metaforas19891218*";
    
    public Connection conectar()throws SQLException{
        
        con = DriverManager.getConnection(this.databaseURL, this.user, this.password);
        return con;
    }
    public void desconectar()throws SQLException{
        if(con!=null){
            try{
                con.close();
            }finally{
                con=null;
            }
        }
    }
}