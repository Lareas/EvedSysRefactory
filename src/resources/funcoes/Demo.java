package funcoes;

public class Demo {

    public static boolean isValid(String email) {
        if (email.indexOf("..") > 0) {
            return false;
        } else {
            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            return email.matches(regex);
        }
    }

    public static void main(String[] args) {
        String email = "john123@gmail.com";
        System.out.println("The E-mail ID is: " + email);
        System.out.println("Is the above E-mail ID valid? " + isValid(email) + "\n");
        email = "j";
        System.out.println("The E-mail ID is: " + email);
        System.out.println("Is the above E-mail ID valid? " + isValid(email) + "\n");
        email = "@john123gmail.com";
        System.out.println("The E-mail ID is: " + email);
        System.out.println("Is the above E-mail ID valid? " + isValid(email) + "\n");
        email = "john123@gmailcom";
        System.out.println("The E-mail ID is: " + email);
        System.out.println("Is the above E-mail ID valid? " + isValid(email) + "\n");
        email = "john123@gmail.com.cd.net";
        System.out.println("The E-mail ID is: " + email);
        System.out.println("Is the above E-mail ID valid? " + isValid(email) + "\n");
        email = "j..ohn123@gmail.com";
        System.out.println("The E-mail ID is: " + email);
        System.out.println("Is the above E-mail ID valid? " + isValid(email) + "\n");
        email = "j.o.h.n.123@gmail.com";
        System.out.println("The E-mail ID is: " + email);
        System.out.println("Is the above E-mail ID valid? " + isValid(email) + "\n");
        email = "j@.gmail.com";
        System.out.println("The E-mail ID is: " + email);
        System.out.println("Is the above E-mail ID valid? " + isValid(email) + "\n");
        email = "j.oa@a.n.123@gmail.com";
        System.out.println("The E-mail ID is: " + email);
        System.out.println("Is the above E-mail ID valid? " + isValid(email) + "\n");
        
        
        
    }
}
