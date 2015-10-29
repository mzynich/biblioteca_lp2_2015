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
public class RelatorioMenu {

    public static final int OP_DETALHES_LIVRO = 1;
    public static final int OP_LIVROS_DISPONIVEIS = 2;
    public static final int OP_LIVROS_MAIS_RETIRADOS = 3;
    public static final int OP_CLIENTES_RETIRARAM_UM_LIVRO = 4;
    public static final int OP_CLIENTES_MAIS_ATRASARAM = 5;
    public static final int OP_LISTAR_EMPRESTIMOS = 6;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Detalhes de um livro\n"
                + "2- Livros disponíveis\n"
                + "3- Livros mais retirados\n"
                + "4- Clientes que mais retiraram um livro\n"
                + "5- Clientes que mais atrasaram\n"
                + "6- Listar todos os empréstimos\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }
}
