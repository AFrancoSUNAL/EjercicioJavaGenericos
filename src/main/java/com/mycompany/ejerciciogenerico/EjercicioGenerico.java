package com.mycompany.ejerciciogenerico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Andres Felipe Franco Sepulveda
 */
public class EjercicioGenerico {
    
    public static Connection conn;

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/personagenerica";
        String username = "root";
        String password = "";

//        Pair<String, Integer> test = new Pair("Andres", 21);
//        System.out.println(test.toString());
//        test = new Pair(1, "Inverso");
         
        System.out.println("Creando nuevo usuario");
        
        System.out.println("Ingrese la edad y el nombre de la persona: ");
        Pair<Integer, String> basicInfo = new Pair<Integer, String>(
                Integer.valueOf(read.nextLine()), 
                read.nextLine());
        
        System.out.println("Ingrese la eps y la fecha de nacimiento de la persona:");
        Pair<String, String> detailsInfo = new Pair<String, String>(
                read.nextLine(), 
                read.nextLine());
        
        Persona usuario = new Persona(basicInfo.getFirst(), 
                basicInfo.getSecond(), 
                detailsInfo.getFirst(), 
                detailsInfo.getSecond());
        
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexión exitosa");
            create(conn, usuario);
            read(conn);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    private static void create(Connection conn, Persona usuario){
        try {
            PreparedStatement consulta;
            
            consulta = conn.prepareStatement("INSERT INTO usuario (name, age, eps, birthday) VALUES (?, ?, ?, ?)");
            consulta.setString(1, usuario.getName());
            consulta.setInt(2, usuario.getAge());
            consulta.setString(3, usuario.getEps());
            consulta.setString(4, usuario.getBirthday());
            
            consulta.executeUpdate();
            System.out.println("Registro guardado con exito.");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public static void read(Connection conn) {
        try {
            PreparedStatement consulta;
            
            consulta = conn.prepareStatement("SELECT * FROM usuario");
            ResultSet resultado = consulta.executeQuery();
            System.out.println("Consulta exitosa");
            while(resultado.next()) {
                System.out.println("Id: %s, Nombre: %s, Edad: %s, Eps: %s, Fecha de nacimiento: %s"
                        .formatted(resultado.getString("id"), 
                                resultado.getString("name"), 
                                resultado.getString("age"),
                                resultado.getString("eps"),
                                resultado.getString("birthday")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
