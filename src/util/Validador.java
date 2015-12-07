/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JOptionPane;
import org.joda.time.LocalDate;

/**
 *
 * @author mzynich
 */
public class Validador {

    /**
     * Verifica se a String possui números
     *
     * @param string A String a ser analisada
     * @return true se possuir número e false se não
     */
    private static boolean stringPossuiNúmero(String string) {
        return string.matches(".*\\d.*");
    }

    /**
     * Verifica se a String está vazia
     *
     * @param string A String a ser analisada
     * @return true se estiver vazia e false se não
     */
    private static boolean stringVazia(String string) {
        return string.trim().isEmpty();
    }

    /**
     * Verifica se a String possui letras
     *
     * @param string A String a ser analisada
     * @return true se possuir letras e false se não
     */
    private static boolean stringPossuiLetras(String string) {
        return string.matches(".*\\D.*");
    }

    /**
     * Elimina os espaços e retorna o número de caracteres da String
     *
     * @param string A String a ser analisada
     * @return Número de caracteres na String
     */
    private static int getTamanhoString(String string) {
        return string.trim().length();
    }

    /**
     * Verifica se um telefone é válido
     *
     * @param telefone A String a ser analisada
     * @return true se a String estiver de acordo com as validações e false se
     * não
     */
    public static boolean telefoneValido(String telefone) {
        if (stringVazia(telefone)) {
            JOptionPane.showMessageDialog(null, "Telefone não pode ser vazio.", "Telefone inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (stringPossuiLetras(telefone)) {
            JOptionPane.showMessageDialog(null, "Telefone não pode conter letras ou espaços.", "Telefone inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (getTamanhoString(telefone) > 12) {
            JOptionPane.showMessageDialog(null, "Telefone muito extenso.", "Telefone inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Verifica se uma matrícula é válida
     *
     * @param matricula A String a ser analisada
     * @return true se a String estiver de acordo com as validações e false se
     * não
     */
    public static boolean matriculaValida(String matricula) {
        if (stringVazia(matricula)) {
            JOptionPane.showMessageDialog(null, "Matrícula não pode ser vazia.", "Matrícula inválida.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (getTamanhoString(matricula) > 20) {
            JOptionPane.showMessageDialog(null, "Matrícula muito extensa.", "Matrícula inválida.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Verifica se um nome de cliente é válido
     *
     * @param nome A String a ser analisada
     * @return true se a String estiver de acordo com as validações e false se
     * não
     */
    public static boolean nomeClienteValido(String nome) {
        if (stringVazia(nome)) {
            JOptionPane.showMessageDialog(null, "Nome não pode ser vazio.", "Nome inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (stringPossuiNúmero(nome)) {
            JOptionPane.showMessageDialog(null, "Nome não pode conter números.", "Nome inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (getTamanhoString(nome) > 50) {
            JOptionPane.showMessageDialog(null, "Nome muito extenso.", "Nome inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Verifica se um ISBN é válido
     *
     * @param isbn A String a ser analisada
     * @return true se a String estiver de acordo com as validações e false se
     * não
     */
    public static boolean ISBNValido(String isbn) {
        if (stringVazia(isbn)) {
            JOptionPane.showMessageDialog(null, "ISBN não pode ser vazio.", "ISBN inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (stringPossuiLetras(isbn)) {
            JOptionPane.showMessageDialog(null, "ISBN não pode conter letras ou espaços.", "ISBN inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (getTamanhoString(isbn) != 13 && getTamanhoString(isbn) != 10) {
            JOptionPane.showMessageDialog(null, "ISBN deve possuir exatamente 10 ou 13 caracteres", "ISBN inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Verifica se um autor é válido
     *
     * @param autor A String a ser analisada
     * @return true se a String estiver de acordo com as validações e false se
     * não
     */
    public static boolean autorValido(String autor) {
        if (stringVazia(autor)) {
            JOptionPane.showMessageDialog(null, "Autor não pode ser vazio.", "Autor inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (stringPossuiNúmero(autor)) {
            JOptionPane.showMessageDialog(null, "Autor não pode conter números.", "Autor inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (getTamanhoString(autor) > 50) {
            JOptionPane.showMessageDialog(null, "Autor muito extenso.", "Autor inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Verifica se uma editora é válida
     *
     * @param editora A String a ser analisada
     * @return true se a String estiver de acordo com as validações e false se
     * não
     */
    public static boolean editoraValida(String editora) {
        if (stringVazia(editora)) {
            JOptionPane.showMessageDialog(null, "Editora não pode ser vazio.", "Editora inválida.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (getTamanhoString(editora) > 50) {
            JOptionPane.showMessageDialog(null, "Editora muito extensa.", "Editora inválida.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Verifica se um ano é válido
     *
     * @param ano O ano a ser verificado
     * @return true se o ano estiver de acordo com as validações e false se não
     */
    public static boolean anoValido(int ano) {
        int anoAtual = new LocalDate().getYear();
        if (ano < 0) {
            JOptionPane.showMessageDialog(null, "O ano de publicação não pode ser negativo", "Ano inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (ano > anoAtual) {
            JOptionPane.showMessageDialog(null, "O ano de publicação não pode ser maior que " + anoAtual, "Ano inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Verifica se uma quantidade é válida
     *
     * @param quantidade A quantidade a ser verificado
     * @return true se a quantidade estiver de acordo com as validações e false
     * se não
     */
    public static boolean quantidadeValida(int quantidade) {
        if (quantidade < 1) {
            JOptionPane.showMessageDialog(null, "A quantidade deve ser maior ou igual a 1.", "Quantidade inválida.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Verifica se um nome de livro é válido
     *
     * @param nome A String a ser analisada
     * @return true se a String estiver de acordo com as validações e false se
     * não
     */
    public static boolean nomeLivroValido(String nome) {
        if (stringVazia(nome)) {
            JOptionPane.showMessageDialog(null, "Nome não pode ser vazio.", "Livro inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (getTamanhoString(nome) > 50) {
            JOptionPane.showMessageDialog(null, "Nome muito extenso.", "Livro inválido.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
