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
public class ClienteMenu {

    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR_TODOS = 2;
    public static final int OP_EDITAR=3;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Cliente\n"
                + "2- Listar Clientes\n"
                + "3- Editar Cliente\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }
}
