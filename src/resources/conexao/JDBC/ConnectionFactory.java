/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;

/**
 *
 * @author ticoa
 */
// classe responsável por fabricar as conexões
public class ConnectionFactory {

    public Connection getConnection() {
//        try {
//            String enderecoServidor = "localhost";
//            // String nomeBanco = "evedsys";
//
//            // essa linha faz com que o sistema lide com caracteres chineses
////            String nomeBanco = "esse5?useUnicode=yes&characterEncoding=UTF-8";
////            String nomeUsuario = "root";
////            String senhaUsuario = "";
//            
//            String nomeBanco = "copia04_ca?zeroDateTimeBehavior=convertToNull";
//            String nomeUsuario = "eveDStation";
//            String senhaUsuario = "NovasEnha@521";
//
//            return DriverManager.getConnection("jdbc:mysql://"
//                    + enderecoServidor + "/" + nomeBanco, nomeUsuario, senhaUsuario);
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Erro de conexão");
//            throw new RuntimeException(ex);
//        }
return null;
  }
}
