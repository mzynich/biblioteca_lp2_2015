/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.InputMismatchException;
import util.Console;
import view.menu.MainMenu;

/**
 *
 * @author mzynich
 */
public class MainUI {

    public MainUI() {
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(MainMenu.getOpcoes());
            try {
                opcao = Console.scanInt("Digite sua opção:");
            } catch (InputMismatchException e) {
                opcao = -1; //Caso o usuário digite uma letra, ele força a utilização da cláusula default
            }
            switch (opcao) {
                case MainMenu.OP_CLIENTE:
                    new ClienteUI().executar();
                    break;
                case MainMenu.OP_LIVRO:
                    new LivroUI().executar();
                    break;
                case MainMenu.OP_RELATORIO:
                    new RelatorioUI().executar();
                    break;
                case MainMenu.OP_SAIR:
                    System.out.println("Aplicação finalizada!!!");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }

        } while (opcao != MainMenu.OP_SAIR);
    }
}
