package funcoes;

import static main.Login.gbDeOnde;
import com.jfoenix.controls.JFXTextField;
import entities.ChecaErros;
import java.math.RoundingMode;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.List;
import static javafx.application.Application.launch;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import funcoes.MaskTextField;

public class MyFunc {

    public static void main(String[] args) {
       launch(args); 
    }
    
    static EntityManagerFactory emf = null;

    public static String alinPagXY(int pagX, int pagY) {
        return "pág. " + pagX + " de " + pagY;
    }

    public static String calcIdade(Date nasc) {
        if (nasc == null) {
            return null;
        } else {
            GregorianCalendar hj = new GregorianCalendar();
            GregorianCalendar nascimento = new GregorianCalendar();
            nascimento.setTime(nasc);
            int anohj = hj.get(Calendar.YEAR);
            int anoNascimento = nascimento.get(Calendar.YEAR);
            return new Integer(anohj - anoNascimento).toString();
        }
    }

    public static String corrigeCaixaNomes(String nome) {
        String[] especs = new String[]{" de ", " da ", " das ", " do ", " dos ", " e ", " bl ", " ap ", " apto ", " ap. ", " apto. ", " cj ", " cj. "};
        String[] arruma = new String[]{" De ", " Da ", " Das ", " Do ", " Dos ", " E ", " Bl ", " Ap ", " Apto ", " Ap. ", " Apto. ", " Cj ", " Cj. "};

        nome = nome.toLowerCase();
        nome = upperCaseAllFirst(nome);

        for (int i = 0; i < especs.length; i++) {
            nome = nome.replaceAll(arruma[i], especs[i]);
        }

        return nome;
    }

    public static String upperCaseAllFirst(String value) {

        char[] array = value.toCharArray();
        // Uppercase first letter.
        array[0] = Character.toUpperCase(array[0]);

        // Uppercase all letters that follow a whitespace character.
        for (int i = 1; i < array.length; i++) {
            if (Character.isWhitespace(array[i - 1])) {
                array[i] = Character.toUpperCase(array[i]);
            }
        }

        // Result.
        return new String(array);
    }

    public static String formataQuebraPapeleta(String curso, String tipoAluno) {

        if (tipoAluno.equals("E")) {
            return curso.toUpperCase() + "  -  Alunos ESPECIAIS";
        } else {
            if (tipoAluno.equals("O")) {
                return curso.toUpperCase() + "  -  Alunos OUVINTES ### SEM NOTA! ###";
            } else { // REGULARES OU NULOS
                return curso.toUpperCase() + "  -  Alunos REGULARES";
            }
        }
    }

    public static String mediaXXX(String tipoAluno) {

        if (tipoAluno.equals("O")) {
            return "XXX";
        } else {
            return " ";
        }
    }

    public static EntityManager getEntityManager() {
        if (emf == null) {
            try {
                emf = Persistence.createEntityManagerFactory(gbDeOnde);
            } catch (Exception e) {
            }
        }
        return emf.createEntityManager();
    }

    public static Long getQuantosNomes(String entidade, String campo, String nome) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Long> query = em.createQuery("select COUNT(s) from " + entidade + " s WHERE s." + campo + " LIKE '%" + nome + "%'", Long.class
        );
        Long achados = query.getSingleResult();
        return achados;
    }

    public static Short getCodSequencial() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Short> query = em.createQuery("select s.codSequencial from Arqsys s WHERE s.cod = 1", Short.class
        );
        Short achados = query.getSingleResult();
        return achados;
    }

    public static Long getContaIgual(String entidade, String campo, int valor, String filtro) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select COUNT(s) from " + entidade + " s WHERE s." + campo + " = '" + valor + "'" + filtro;
        System.out.println(ssql);
        TypedQuery<Long> query = em.createQuery(ssql, Long.class
        );
        Long achados = query.getSingleResult();
        return achados;
    }

    public static Long contaFiltrados(String entidade, String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Long> query = em.createQuery("select COUNT(s) from " + entidade + " s " + pesq, Long.class
        );
        Long achados = query.getSingleResult();
        return achados;
    }

    public static Long contaTodos(String entidade) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Long> query = em.createQuery("select COUNT(s) from " + entidade + " s ", Long.class
        );
        Long achados = query.getSingleResult();
        return achados;
    }

    public List<?> getEstadocivilPesq(String entidade, String campo, String pesq, Object minhaclasse) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<?> qry = em.createQuery("select s from " + entidade + " s WHERE s." + campo + " LIKE '%" + pesq + "%'", minhaclasse.getClass());
        return qry.getResultList();
    }

    public static boolean emailConfere(String email) {
        if (email.indexOf("..") > 0) {
            return false;
        } else {
            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            return email.matches(regex);
        }
    }

    public static boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        } else {
            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            return email.matches(regex);
        }
    }

    public static String montaCondic(String condic, String tipo) {
        return " " + condic + " " + tipo + " ";
    }

    public static String weekDay(Calendar cal) {
        return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
    }

    public static void mostraMsg(String tit, String msg, int tipo) {
        Alert alert;
        // C E I W
        switch (tipo) {
            case 1:
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                break;
            case 2:
                alert = new Alert(Alert.AlertType.ERROR);
                break;
            case 3:
                alert = new Alert(Alert.AlertType.INFORMATION);
                break;
            case 4:
                alert = new Alert(Alert.AlertType.WARNING);
                break;
            default:
                alert = new Alert(Alert.AlertType.INFORMATION);
                break;
        }
        alert.setHeaderText(tit);
        alert.setContentText(msg);
        alert.show();
    }

    public static void mostraMsgWait(String tit, String msg, int tipo) {
        Alert alert;

        switch (tipo) {
            case 1:
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                break;
            case 2:
                alert = new Alert(Alert.AlertType.ERROR);
                break;
            case 3:
                alert = new Alert(Alert.AlertType.INFORMATION);
                break;
            case 4:
                alert = new Alert(Alert.AlertType.WARNING);
                break;
            default:
                alert = new Alert(Alert.AlertType.NONE);
                break;
        }
        alert.setHeaderText(tit);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isInt(MaskTextField input) {
        try {
            int dado = Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInt(JFXTextField input) {
        try {
            int dado = Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFloat(MaskTextField input) {
        try {
            float dado = Float.parseFloat(input.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFloat(JFXTextField input) {
        try {
            float dado = Float.parseFloat(input.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

//     public static boolean isInt(MaskTextField input, String message) {
//        try {
//            int dado = Integer.parseInt(input.getText());
//            return true;
//        } catch (NumberFormatException e) {
//            System.out.println("Erro: " + message + " não é numerico");
//            return false;
//        }
//    }
    public static String montaCondic(String campo, int operador, String conteudo, String tipo) {

        String sCond = " s." + campo;

        if (tipo == "S") {
            switch (operador) {
                case 0: // "contém"
                    sCond = sCond + " LIKE '%" + conteudo + "%'";
                    break;
                case 1: // "começa com"
                    sCond = sCond + " LIKE '" + conteudo + "%'";
                    break;
                case 2: // "termina com"
                    sCond = sCond + " LIKE '%" + conteudo + "'";
                    break;
                case 3: // "igual a"
                    sCond = sCond + " = '" + conteudo + "'";
                    break;
                case 4: // "diferente de"
                    sCond = sCond + " <> '" + conteudo + "'";
                    break;
                case 5: // "maior ou igual a"
                    sCond = sCond + " >= '" + conteudo + "'";
                    break;
                case 6: // "maior que"
                    sCond = sCond + " > '" + conteudo + "'";
                    break;
                case 7: // "menor ou igual a"
                    sCond = sCond + " <= '" + conteudo + "'";
                    break;
                case 8: // "menor que"
                    sCond = sCond + " < '" + conteudo + "'";
                    break;
                default:
                    sCond = "ERRO 014 - CONDIÇÃO ERRADA!";
            }
        } else {
            if (tipo == "I") {
                switch (operador) {
                    case 0: // "contém"
                        sCond = sCond + " LIKE '%" + conteudo + "%'";
                        break;
                    case 1: // "começa com"
                        sCond = sCond + " LIKE '" + conteudo + "%'";
                        break;
                    case 2: // "termina com"
                        sCond = sCond + " LIKE '%" + conteudo + "'";
                        break;
                    case 3: // "igual a"
                        sCond = sCond + " = " + conteudo;
                        break;
                    case 4: // "diferente de"
                        sCond = sCond + " <> " + conteudo;
                        break;
                    case 5: // "maior ou igual a"
                        sCond = sCond + " >= " + conteudo;
                        break;
                    case 6: // "maior que"
                        sCond = sCond + " > " + conteudo;
                        break;
                    case 7: // "menor ou igual a"
                        sCond = sCond + " <= " + conteudo;
                        break;
                    case 8: // "menor que"
                        sCond = sCond + " < " + conteudo;
                        break;
                    default:
                        sCond = "ERRO 014b - CONDIÇÃO ERRADA!";
                }

            }
        }
        return sCond;

    }

    private static String arredondar(double media) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(media);
    }

    public static boolean isCPF(String sCPF) {
        // se houver . ou -, retira

        if (sCPF.trim().length() != 14) {
            return false;
        } else {

            String CPF;

//                CPF = sCPF.replaceAll("-", "");
//                CPF = CPF.replaceAll(".", "");
            CPF = sCPF.substring(0, 3) + sCPF.substring(4, 7) + sCPF.substring(8, 11) + sCPF.substring(12, 14);

            // considera-se erro CPF's formados por uma sequencia de numeros iguais
            if (CPF.equals("00000000000")
                    || CPF.equals("11111111111")
                    || CPF.equals("22222222222") || CPF.equals("33333333333")
                    || CPF.equals("44444444444") || CPF.equals("55555555555")
                    || CPF.equals("66666666666") || CPF.equals("77777777777")
                    || CPF.equals("88888888888") || CPF.equals("99999999999")
                    || (CPF.length() != 11)) {
                return (false);
            }

            char dig10, dig11;
            int sm, i, r, num, peso;

            // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
            try {
                // Calculo do 1o. Digito Verificador
                sm = 0;
                peso = 10;
                for (i = 0; i < 9; i++) {
                    // converte o i-esimo caractere do CPF em um numero:
                    // por exemplo, transforma o caractere '0' no inteiro 0         
                    // (48 eh a posicao de '0' na tabela ASCII)         
                    num = (int) (CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11)) {
                    dig10 = '0';
                } else {
                    dig10 = (char) (r + 48); // converte no respectivo caractere numerico
                }
                // Calculo do 2o. Digito Verificador
                sm = 0;
                peso = 11;
                for (i = 0; i < 10; i++) {
                    num = (int) (CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11)) {
                    dig11 = '0';
                } else {
                    dig11 = (char) (r + 48);
                }

                // Verifica se os digitos calculados conferem com os digitos informados.
                if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                    return (true);
                } else {
                    return (false);
                }
            } catch (InputMismatchException erro) {
                return (false);
            }
        }
    }

    public static String imprimeCPF(String CPF) {
        return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "."
                + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

//    public static void checaCombo(String campo, int tipErr, ComboBox combo, ChecaErros che) {
//        String messa;
//        if (combo.getSelectionModel().getSelectedItem() == null) {
//            if (tipErr == 1) {
//                che.setWarn(che.getWarn() + "*** O campo <" + campo + "> não pode ficar vazio!\n");
//                che.setCte(che.getCte() + 1);
//            } else {
//                che.setWarn(che.getWarn() + "- O campo <" + campo + "> está vazio.\n");
//                che.setCtw(che.getCtw() + 1);
//            }
//        } else {
//            if (combo.getSelectionModel().toString().equals("")) {
//                if (tipErr == 1) {
//                    che.setWarn(che.getWarn() + "*** O campo <" + campo + "> não pode ficar vazio!\n");
//                    che.setCte(che.getCte() + 1);
//                } else {
//                    che.setWarn(che.getWarn() + "- O campo <" + campo + "> está vazio.\n");
//                    che.setCtw(che.getCtw() + 1);
//                }
//            }
//        }
//    }
    public static Boolean isByte(TextField edField) {
        try {
            Byte i = Byte.parseByte(edField.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Boolean isShort(TextField edField) {
        try {
            Short i = Short.parseShort(edField.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Boolean labelIsShort(Label label) {
        try {
            Short i = Short.parseShort(label.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Boolean isInteger(TextField edField) {
        try {
            Integer i = Integer.parseInt(edField.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Boolean isLong(TextField edField) {
        try {
            Long i = Long.parseLong(edField.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Boolean isDouble(TextField edField) {
        try {
            Double i = Double.parseDouble(edField.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Boolean isFloat(TextField edField) {
        try {
            Float i = Float.parseFloat(edField.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void verifTTFNum(String campo, int tipErr, TextField edField, ChecaErros che, Boolean obrig, int min, int max) {
        if ((edField.getText() == null) && (obrig)) {
            che.setWarn(che.getWarn() + "*** O campo <" + campo + "> não pode ficar vazio!\n");
            che.setCte(che.getCte() + 1);
        } else {
            // verificar se é numérico
            if (!isShort(edField)) {
                che.setWarn(che.getWarn() + "*** O campo <" + campo + "> não é numérico!\n");
                che.setCte(che.getCte() + 1);
            } else {
                Short valTTF = Short.parseShort(edField.getText());
                // verifica intervalo
                if ((min > 0) && (valTTF < min)) {
                    che.setWarn(che.getWarn() + "*** O campo <" + campo + "> não pode ser menor que " + min + "!\n");
                    che.setCte(che.getCte() + 1);
                } else {
                    if ((max > 0) && (valTTF > max)) {
                        che.setWarn(che.getWarn() + "*** O campo <" + campo + "> não pode ser maior que " + max + "!\n");
                        che.setCte(che.getCte() + 1);
                    }
                }

            }
        }
    }

    public static void verifTTFNumFloat(String campo, int tipErr, TextField edField, ChecaErros che, Boolean obrig, int min, int max) {
        if ((edField.getText() == null) && (obrig)) {
            che.setWarn(che.getWarn() + "*** O campo <" + campo + "> não pode ficar vazio!\n");
            che.setCte(che.getCte() + 1);
        } else {
            // verificar se é numérico
            if (!isFloat(edField)) {
                che.setWarn(che.getWarn() + "*** O campo <" + campo + "> não é numérico!\n");
                che.setCte(che.getCte() + 1);
            } else {
                Short valTTF = Short.parseShort(edField.getText());
                // verifica intervalo
                if ((min > 0) && (valTTF < min)) {
                    che.setWarn(che.getWarn() + "*** O campo <" + campo + "> não pode ser menor que " + min + "!\n");
                    che.setCte(che.getCte() + 1);
                } else {
                    if ((max > 0) && (valTTF > max)) {
                        che.setWarn(che.getWarn() + "*** O campo <" + campo + "> não pode ser maior que " + max + "!\n");
                        che.setCte(che.getCte() + 1);
                    }
                }

            }
        }
    }

//    public static Short convShort(String x) {
//        try {
//            short conv = Short.parseShort(x.trim());
//            return conv;
//        } catch (NumberFormatException e) {
//            return null;
//        }
//    }
    public static Integer convInt(String x) {
        Integer conv;
        if (x.trim() == null) {
            return null;
        } else {
            if (x.trim() == "") {
                return 0;
            } else {
                try {
                    conv = (Integer.parseInt(x.trim()));
                    return conv;
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
    }

    public static void checaTTF(String campo, int tipErr, TextField edField, ChecaErros che) {
        // tipoErro-> 1 = ERRO, 2 = Warning
        if (edField.getText() == null) {
            if (tipErr == 1) {
                che.setWarn(che.getWarn() + "*** O campo <" + campo + "> não pode ficar vazio!\n");
                che.setCte(che.getCte() + 1);
            } else {
                che.setWarn(che.getWarn() + "- O campo <" + campo + "> está vazio.\n");
                che.setCtw(che.getCtw() + 1);
            }
        } else {
            if (edField.getText().equals("")) {
                if (tipErr == 1) {
                    che.setWarn(che.getWarn() + "*** O campo <" + campo + "> não pode ficar vazio!\n");
                    che.setCte(che.getCte() + 1);
                } else {
                    che.setWarn(che.getWarn() + "- O campo <" + campo + "> está vazio.\n");
                    che.setCtw(che.getCtw() + 1);
                }
            }
        }
    }

    public static Boolean checaHora(String hora) {
        if (hora == null) {
            return false;
        }

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
            } else {
                return false;
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

    public static String calcularIdade(Date data) {

        Integer meses = 0;
        Integer dias = 0;
        Integer anos = 0;

        Calendar dataAtual;
        Calendar dataNascimento;

        dataAtual = Calendar.getInstance();
        dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(data);

        anos = anos + (dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR));

        meses = meses + (dataAtual.get(Calendar.MONTH) - dataNascimento.get(Calendar.MONTH));

        dias = dias + (dataAtual.get(Calendar.DAY_OF_MONTH) - dataNascimento.get(Calendar.DAY_OF_MONTH));

        if (dataAtual.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH)) {
            if (dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                dias = 30 + dias;
                meses = 12 + meses;
                anos = anos - 1;
            }
        } else if (dataAtual.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
            if (dataAtual.get(Calendar.DAY_OF_MONTH) >= dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                meses = 12 + meses;
                anos = anos - 1;
            } else if (dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                dias = 30 + dias;
                meses = 12 + meses;
                anos = anos - 1;
            }
        } else if (dataAtual.get(Calendar.MONTH) > dataNascimento.get(Calendar.MONTH)) {
            if (dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
                dias = 30 + dias;
                meses = meses - 1;
            }
        }

        return formatar(anos, meses, dias);
    }

    /**
     * Método responsável por formatar uma idade no formato 1 10 6. Concatena-se
     * 0 se o valor menor que 10 e logo apos o numero adicionasse espaço.
     *
     * @return Data formatada para o formato 00 00 00.
     */
    public static String formatar(Integer ano, Integer mes, Integer dia) {
        String idade = "";
        if (ano.intValue() < 10) {
            idade = (new StringBuilder()).append(idade).append("0").append(ano).append(" ").toString();
        } else {
            idade = (new StringBuilder()).append(idade).append(ano).append(" ").toString();
        }
        if (mes.intValue() < 10) {
            idade = (new StringBuilder()).append(idade).append("0").append(mes).append(" ").toString();
        } else {
            idade = (new StringBuilder()).append(idade).append(mes).append(" ").toString();
        }
        if (dia.intValue() < 10) {
            idade = (new StringBuilder()).append(idade).append("0").append(dia).append(" ").toString();
        } else {
            idade = (new StringBuilder()).append(idade).append(dia).append(" ").toString();
        }
        return idade;
    }

    /**
     * Pega os valores 00 00 00 e coloca Ano(s) Mes(es) Dia(s)
     */
    public static String formatarIdade(String ida) {
        String idade = "";
        String anos = null;
        String meses = null;
        String dias = null;
        String CharArray[] = ida.split(" ");
        anos = CharArray[0];
        meses = CharArray[1];
        dias = CharArray[2];
        if (!anos.equals("00")) {
            if (anos.equals("01")) {
                idade = idade.concat((new StringBuilder()).append(anos).append(" Ano ").toString());
            } else {
                idade = idade.concat((new StringBuilder()).append(anos).append(" Anos ").toString());
            }
        }
        if (!meses.equals("00")) {
            if (meses.equals("01")) {
                idade = idade.concat((new StringBuilder()).append(meses).append(" M52s ").toString());
            } else {
                idade = idade.concat((new StringBuilder()).append(meses).append(" Meses ").toString());
            }
        }
        if (!dias.equals("00")) {
            if (dias.equals("01")) {
                idade = idade.concat((new StringBuilder()).append(dias).append(" Dia ").toString());
            } else {
                idade = idade.concat((new StringBuilder()).append(dias).append(" Dias").toString());
            }
        }
        return idade;
    }
}
