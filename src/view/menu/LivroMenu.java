/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

/**
 *
 * @author mzynich
 */
public class LivroMenu {

    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR_TODOS = 2;
    public static final int OP_EMPRESTAR = 3;
    public static final int OP_DEVOLVER = 4;
    public static final int OP_EDITAR = 5;
    public static final int OP_EXCLUIR = 6;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Livro\n"
                + "2- Listar Livros\n"
                + "3- Emprestar Livro\n"
                + "4- Devolver Livro\n"
                + "5- Editar Livro\n"
                + "6- Excluir Livro\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }
}
