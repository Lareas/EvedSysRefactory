package funcoes;


public class TestLastIndexOf {

    public static void main(String[] args) {
        String text = "\\\\192.168.0.19\\esphoto\\Davi Daniel de Oliveira_3432***";
        String word = "\\";
        String arq = "Carta de Recomendação 1.pdf";
        
        int pos = text.lastIndexOf(word);

        System.out.println(text.indexOf(word)); // prints "4"
        System.out.println(text.lastIndexOf(word)); // prints "22"
        System.out.println(text.substring(0,pos));
        System.out.println(text);
        System.out.println(text.substring(pos+1,text.length()));
    }
    
    
}
