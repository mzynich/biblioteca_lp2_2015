package biblioteca;

import org.joda.time.LocalDate;
import view.MainUI;

/**
 *
 * @author mzynich
 */
public class Biblioteca {

    public static final int limiteEmprestimos = 3;

    public static void main(String args[]) {
       new MainUI().executar();
    }
}
