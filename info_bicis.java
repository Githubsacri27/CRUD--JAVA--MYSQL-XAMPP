/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_certificacion_2t;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rsacr
 */
public class info_bicis {
    private int toma_de_encargo;
    private String tecnico;
    private String tipo_pedido;
    private String Proveedor;
    private String estado_pedido;
    private String producto;
    private String repuesto;
    private int codigo_repuesto;
    
   

    public info_bicis() {
    }

    public info_bicis(int toma_de_encargo, String tecnico, String tipo_pedido,
            String Proveedor, String estado_pedido, String producto, 
            String repuesto, int codigo_repuesto) {
        this.toma_de_encargo = toma_de_encargo;
        this.tecnico = tecnico;
        this.tipo_pedido = tipo_pedido;
        this.Proveedor = Proveedor;
        this.estado_pedido = estado_pedido;
        this.producto = producto;
        this.repuesto = repuesto;
        this.codigo_repuesto = codigo_repuesto;
       
    }

    public int getToma_de_encargo() {
        return toma_de_encargo;
    }

    public void setToma_de_encargo(int toma_de_encargo) {
        this.toma_de_encargo = toma_de_encargo;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getTipo_pedido() {
        return tipo_pedido;
    }

    public void setTipo_pedido(String tipo_pedido) {
        this.tipo_pedido = tipo_pedido;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String Proveedor) {
        this.Proveedor = Proveedor;
    }

    public String getEstado_pedido() {
        return estado_pedido;
    }

    public void setEstado_pedido(String estado_pedido) {
        this.estado_pedido = estado_pedido;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(String repuesto) {
        this.repuesto = repuesto;
    }

    public int getCodigo_repuesto() {
        return codigo_repuesto;
    }

    public void setCodigo_repuesto(int codigo_repuesto) {
        this.codigo_repuesto = codigo_repuesto;
    }

    

    
        // Insertar o dar de alta 
             public int insertar (){
        int n=0;
        try {
            //cargar Driver de MYSQL
            Class.forName("com.mysql.jdbc.Driver");
            // Hacemos la conexión con la base de datos
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/certificacion_46407574z","root","");
            Statement sentencia=conexion.createStatement();
             //Creación de la sentencia sql tipo insert en la tabla departamentos
            String sql = "insert into taller values (" +toma_de_encargo + ","
                    + "'"+tecnico +"','"+tipo_pedido +"','"+Proveedor +"','"+
                    estado_pedido+"','"+producto+"','"+repuesto+"',"+codigo_repuesto+");";
            // lanzamiento de la sentencia con el executeUpdate
            n = sentencia.executeUpdate(sql);
            
            //cerrar sentencia, y la conexión
            sentencia.close();
            conexion.close ();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(info_bicis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(info_bicis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
        
    }
    
    // mostrar el listado
    public String listado (){
        String linea="";
        try {
            //cargar Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/certificacion_46407574z","root","");
            Statement sentencia=conexion.createStatement();
            
            
            // lanzamiento de la sentencia con el executeQuery para que nos devuelva el ResulSet
            ResultSet resultado = sentencia.executeQuery("select * from taller");
            //bucle para recorrer todo y que nos devuelva cada campo correspondiente de la tabla
            while (resultado.next()){
                linea = linea + resultado.getInt(1) + "\t" + resultado.getString(2) + "\t" + resultado.getString(3) + "\t" + resultado.getString(4) 
                        + "\t" + resultado.getString(5) + "\t" + resultado.getString(6) + "\t" + resultado.getString(7)+ "\t" + resultado.getInt(8) +"\t" + "\n";
            }
            //cerrar sentencia,conexión
            sentencia.close();
            conexion.close ();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(info_bicis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(info_bicis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return linea;
    
    }
    //Buscar solo por nombre
    public String buscar (String tecnico ){
        String linea="";
        try {
            //cargar Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/certificacion_46407574z","root","");
            Statement sentencia=conexion.createStatement();
            
            String filtro="";
            if (tecnico.trim()!=""){
                filtro ="tecnico like '%" + tecnico.trim() + "%'";
            }
            if (filtro !=""){
            
                filtro= " where " +filtro;
            }
            // lanzamiento de la sentencia con el executeQuery para que nos devuelva el ResulSet
            ResultSet resultado = sentencia.executeQuery("select * from taller" + filtro);
            //bucle para recorrer todo y que nos devuelva cada campo correspondiente de la tabla
            while (resultado.next()){
               linea = linea + resultado.getInt(1) + "\t" + resultado.getString(2) + "\t" + resultado.getString(3)
               + "\t" + resultado.getString(4) + "\t" + resultado.getString(5) + "\t" + resultado.getString(6) + "\t" + resultado.getString(7)
              + "\t" + resultado.getInt(8) +"\t" + "\n";
            }
            //cerrar sentencia,conexión
            sentencia.close();
            conexion.close ();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(info_bicis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(info_bicis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return linea;
    
    }
       // eliminar o dar de baja
    public int borrar (int toma_de_encargo){
        int n=0;
        try {
            //cargar Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/certificacion_46407574z","root","");
            Statement sentencia=conexion.createStatement();
            //Creación de la sentencia sql para borrar datos en la tabla departamentos
            String sql = "delete from taller where toma_de_encargo=" +toma_de_encargo + ";";
            
            // lanzamiento de la sentencia con el executeUpdate
            n = sentencia.executeUpdate(sql);
            //cerrar sentencia,conexión
            sentencia.close();
            conexion.close ();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(info_bicis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(info_bicis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
        
    }
    //modificar los datos
    public int modificacion (int toma_de_encargo, String tecnico, String tipo_pedido, 
            String Proveedor, String estado_pedido, String producto, String repuesto, int codigo_repuesto){
        int n=0;
        try{
            ////cargar Driver de MYSQL
            Class.forName("com.mysql.jdbc.Driver");
            //CONECTAR A LA BASE DE DATOS CON MI DNI 46407574z
            Connection conexion =DriverManager.getConnection("jdbc:mysql://localhost/certificacion_46407574z","root","");
            //lanzamiento
            Statement sentencia =conexion.createStatement();
            String sql = "update taller set tecnico='" + tecnico + "', tipo_pedido='" + tipo_pedido +"',  Proveedor='" + Proveedor+  "', estado_pedido='" 
                    + estado_pedido +  "', producto='" + producto +  "', repuesto='" + repuesto +  "', codigo_repuesto='" +
                    codigo_repuesto + "' where toma_de_encargo=" + toma_de_encargo + ";";  
             
            n = sentencia.executeUpdate(sql);
            //cerrar sentencia, conexión
            sentencia.close();
            conexion.close ();
            //captura de errores
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(info_bicis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(info_bicis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

        }
    
}
