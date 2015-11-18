/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
            System.out.println("Telefone não pode ser vazio.");
            return false;
        }
        if (stringPossuiLetras(telefone)) {
            System.out.println("Telefone não pode conter letras ou espaços.");
            return false;
        }
        if (getTamanhoString(telefone) > 12) {
            System.out.println("Telefone muito extenso.");
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
            System.out.println("Matrícula não pode ser vazia");
            return false;
        }
        if (getTamanhoString(matricula) > 20) {
            System.out.println("Matrícula muito extensa.");
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
            System.out.println("Nome não pode ser vazio");
            return false;
        }
        if (stringPossuiNúmero(nome)) {
            System.out.println("Nome não pode conter números.");
            return false;
        }
        if (getTamanhoString(nome) > 50) {
            System.out.println("Nome muito extenso.");
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
            System.out.println("ISBN não pode ser vazio.");
            return false;
        }
        if (stringPossuiLetras(isbn)) {
            System.out.println("ISBN não pode conter letras ou espaços.");
            return false;
        }
        if (getTamanhoString(isbn) != 13 && getTamanhoString(isbn) != 10) {
            System.out.println("ISBN deve possuir exatamente 10 ou 13 caracteres");
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
            System.out.println("Autor não pode ser vazio.");
            return false;
        }
        if (stringPossuiNúmero(autor)) {
            System.out.println("Autor não pode conter números.");
            return false;
        }
        if (getTamanhoString(autor) > 50) {
            System.out.println("Autor muito extenso.");
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
            System.out.println("Editora não pode ser vazio.");
            return false;
        }
        if (getTamanhoString(editora) > 50) {
            System.out.println("Editora muito extensa.");
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
        if (ano > anoAtual) {
            System.out.println("O ano de publicação não pode ser maior que " + anoAtual);
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
            System.out.println("A quantidade deve ser maior ou igual a 1.");
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
            System.out.println("Nome não pode ser vazio");
            return false;
        }
        if (getTamanhoString(nome) > 50) {
            System.out.println("Nome muito extenso.");
            return false;
        }
        return true;
    }
}
