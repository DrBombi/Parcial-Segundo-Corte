/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import controlador.Jdbc;
import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;
import modelo.Cliente;

/**
 *
 * @author josed
 */
public class ClienteJdbc extends Jdbc {
    
    
    private List listaCliente;
    Jdbc con=new Jdbc();

    public void saveCliente(Cliente rc) throws SQLException {
    PreparedStatement pstt = null;
    try {
        pstt = this.conectar().prepareStatement("insert into cliente values(?,?,?,?,?,?,?,?,?)");
        pstt.setString(1, null);
        pstt.setString(2, rc.getCodigo());
        pstt.setString(3, rc.getNombreCliente());
        pstt.setString(4, rc.getEdad());
        pstt.setString(5, rc.getDireccion());
        pstt.setString(6, rc.getTelefono());
        pstt.setString(7, rc.getEmail());
        pstt.setString(8, rc.getEstado());
        pstt.setDate(9, null);
        if((getCliente(rc.getCodigo())).getCodigo()==null){
            if(rc.getCodigo()!=null){
                pstt.executeUpdate();
                JOptionPane.showMessageDialog(null, "El Usuario fue registrado exitosamente!");
            }else{
                JOptionPane.showMessageDialog(null, "No deje campos vacios");
            }
        }else{
            JOptionPane.showMessageDialog(null, "El Usuario que desea registar ya extiste!");
        }
        } finally {
            if (pstt != null) {
                pstt.close();
            }
        }
    }

    public void updateCliente(Cliente rc)throws SQLException{
    
    PreparedStatement pstn=null;
    try{
        pstn=this.conectar().prepareStatement("update cliente set id=?,codigo=?,nombreCliente=?,edad=?,direccion=?,telefono=?,email=?,estado=?,fechaRegistro=? where id=? ");
        pstn.setInt(1, rc.getId());
        pstn.setString(2, rc.getCodigo());
        pstn.setString(3, rc.getNombreCliente());
        pstn.setString(4, rc.getEdad());
        pstn.setString(5, rc.getDireccion());
        pstn.setString(6, rc.getTelefono());
        pstn.setString(7, rc.getEmail());
        pstn.setString(8, rc.getEstado());
        pstn.setString(9, rc.getFechaRegistro());
        pstn.executeUpdate();
        }finally{
            if(pstn!=null)
                pstn.close();
        }
    }

    public Cliente getCliente(String Codigo) throws SQLException {
    
    Cliente rc = new Cliente();
    PreparedStatement pstt = null;
    ResultSet rs = null;
    try {
        pstt =this.conectar().prepareStatement("select * from cliente where codigo=?");
        pstt.setString(1, Codigo);
        rs = pstt.executeQuery();
        while (rs.next()) {
            rc = load(rs);
        }
        } finally {
            if (pstt != null) {
                pstt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return rc;
    }

    public List getCliente() throws SQLException {
    
    listaCliente = new LinkedList();
    PreparedStatement pstt = null;
    ResultSet rs = null;
    try {
        pstt = this.conectar().prepareStatement("select * from cliente where codigo=?");
        rs = pstt.executeQuery();
        while (rs.next()) {
        listaCliente.add(load(rs));
    }
        } finally {
            if (pstt != null) {
                pstt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return listaCliente;
    }

    private Cliente load(ResultSet rs) throws SQLException {
    
        Cliente rcu = new Cliente();
        rcu.setId(rs.getInt(1));
        rcu.setCodigo(rs.getString(2));
        rcu.setNombreCliente(rs.getString(3));
        rcu.setEdad(rs.getString(4));
        rcu.setDireccion(rs.getString(5));
        rcu.setTelefono(rs.getString(6));
        rcu.setEmail(rs.getString(7));
        rcu.setEstado(rs.getString(8));
        rcu.setFechaRegistro(rs.getString(9));
        return rcu;
    }

    public void DeleteCliente(String Codigo)throws SQLException {
    
    PreparedStatement pstn=null;
    try{
        pstn= this.conectar().prepareStatement("delete from cliente where codigo=?");
        pstn.setString(1,Codigo);
        pstn.executeUpdate();
        }finally{
            if(pstn != null){
                pstn.close();
            }
        }
    }
}