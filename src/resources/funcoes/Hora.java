package funcoes;

public class Hora {

    public static void main(String[] args) {
        String h1 = "3:45";
        String h2 = "12:45";
        String h3 = "19:00";
        String h4 = "25:00";
        String h5 = "14:62";
        String h6 = "4:88";

        System.out.println(h1 + " " + checaHora(h1));
        System.out.println(h2 + " " + checaHora(h2));
        System.out.println(h3 + " " + checaHora(h3));
        System.out.println(h4 + " " + checaHora(h4));
        System.out.println(h5 + " " + checaHora(h5));
        System.out.println(h6 + " " + checaHora(h6));

    }

    private static Boolean checaHora(String hora) {
        int tam = hora.trim().length();
        if (tam == 4) { // h:mm
            // tem :?
            if (!hora.substring(1, 2).equals(":")) {
                return false;
            } else {
                // hora válida?
                if (!horaValida(hora.substring(0, 1))) {
                    return false;
                } else {
                    // minutos válidos?
                    if (!minValido(hora.substring(2, 4))) {
                        return false;
                    }
                }
            }
        } else {
            if (tam == 5) { // h:mm
                if (!hora.substring(2, 3).equals(":")) {
                    return false;
                } else {
                    // hora válida?
                    if (!horaValida(hora.substring(0, 2))) {
                        return false;
                    } else {
                        // minutos válidos?
                        if (!minValido(hora.substring(3, 5))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static Boolean horaValida(String hora) {

        try {
            // the String to int conversion happens here
            int i = Integer.parseInt(hora.trim());

            // print out the value after the conversion
            if ((i >= 0) && (i <= 24)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private static Boolean minValido(String min) {

        try {
            // the String to int conversion happens here
            int i = Integer.parseInt(min.trim());

            // print out the value after the conversion
            if ((i >= 0) && (i <= 59)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
