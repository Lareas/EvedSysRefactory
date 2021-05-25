package funcoes;

//import JDBC.ConnectionFactory;
import JDBC.ConnectionFactory;
import static main.Login.gbDeOnde;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnector {

    public static Connection getConnection() {
        try {
            if (gbDeOnde == "Jpa_EvedSys_Server") { // servidor 

                String enderecoServidor = "192.168.0.19:3306";
                String nomeBanco = "eved_05_05_2020?zeroDateTimeBehavior=convertToNull";
                String nomeUsuario = "eveDStation";
                String senhaUsuario = "NovasEnha@521";

                return DriverManager.getConnection("jdbc:mysql://"
                        + enderecoServidor + "/" + nomeBanco, nomeUsuario, senhaUsuario);

            } else { // local
                if (gbDeOnde == "Jpa_EvedSys_Local") {
                    String enderecoServidor = "localhost";

                    // essa linha faz com que o sistema lide com caracteres chineses
                    String nomeBanco = "esse5?useUnicode=yes&characterEncoding=UTF-8";
                    String nomeUsuario = "root";
                    String senhaUsuario = "ovres@521Pv16-20";

                    return DriverManager.getConnection("jdbc:mysql://localhost:3306/esse5?zeroDateTimeBehavior=convertToNull&amp;useSSL=false"
                            + nomeBanco, nomeUsuario, senhaUsuario);
                } else { // Jpa_EvedSys_Loc_Copia04_CA
                    String enderecoServidor = "localhost";

                    // essa linha faz com que o sistema lide com caracteres chineses
                    String nomeBanco = "esse5?useUnicode=yes&characterEncoding=UTF-8";
                    String nomeUsuario = "userdoe";
                    String senhaUsuario = "icanenter";
                    
                             //return DriverManager.getConnection("jdbc:mysql://localhost:3306/ca_04maio2020_10h30?zeroDateTimeBehavior=convertToNull&amp;useSSL=false",
                            //return DriverManager.getConnection("jdbc:mysql://localhost:3306/copia04_ca?zeroDateTimeBehavior=convertToNull&amp;useSSL=false"
                    return DriverManager.getConnection("jdbc:mysql://localhost:3306/eved_05_05_2020?zeroDateTimeBehavior=convertToNull",
                            nomeUsuario, senhaUsuario);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro de conexão");
            throw new RuntimeException(ex);
        }
    }
}
