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
public class MainMenu {

    public static final int OP_CLIENTE = 1;
    public static final int OP_LIVRO = 2;
    public static final int OP_RELATORIO = 3;
    public static final int OP_SAIR = 0;

    /**
     * Menu principal da aplicação
     * @return As opções disponíveis
     */
    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Menu Clientes\n"
                + "2- Menu Livros\n"
                + "3- Menu Relatórios\n"
                + "0- Sair da Aplicação"
                + "\n--------------------------------------");
    }
}
