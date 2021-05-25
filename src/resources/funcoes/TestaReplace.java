package funcoes;

import java.sql.Timestamp;
import java.util.Date;

public class TestaReplace {

    public static void main(String[] args) {
        String s = "Luiz Fernando de Assis Moura Arêas";
        String a = s.replaceAll("n", "X");
        String b = s.replaceAll("n", "");
        System.out.println(s);
        System.out.println(a);
        System.out.println(b);

        System.out.println(new Date());
        
    }

}
