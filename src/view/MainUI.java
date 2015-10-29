/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.InputMismatchException;
import model.Cliente;
import model.Emprestimo;
import model.ItemLivro;
import model.Livro;
import org.joda.time.DateTime;
import repositorio.RepositorioCliente;
import repositorio.RepositorioEmprestimo;
import repositorio.RepositorioLivro;
import util.Console;
import view.menu.MainMenu;

/**
 *
 * @author mzynich
 */
public class MainUI {

    private RepositorioCliente repositorioCliente;
    private RepositorioEmprestimo repositorioEmprestimo;
    private RepositorioLivro repositorioLivro;

    public MainUI() {
        repositorioCliente = new RepositorioCliente();
        repositorioEmprestimo = new RepositorioEmprestimo();
        repositorioLivro = new RepositorioLivro();

        Cliente c1 = new Cliente("123", "max", "99999999");
        Cliente c2 = new Cliente("456", "joão", "93999999");
        Cliente c3 = new Cliente("789", "andré", "99995999");
        Cliente c4 = new Cliente("101", "leonardo", "91999999");
        Cliente c5 = new Cliente("121", "antônio", "99599999");
        Cliente c6 = new Cliente("134", "josé", "99998999");
        
        repositorioCliente.addCliente(c1);
        repositorioCliente.addCliente(c2);
        repositorioCliente.addCliente(c3);
        repositorioCliente.addCliente(c4);
        repositorioCliente.addCliente(c5);
        repositorioCliente.addCliente(c6);
        
        ItemLivro l1 = new ItemLivro(new Livro("001", "Senhor dos Anéis", "wegwg", "wgwrg", 2000), 10);
        ItemLivro l2 = new ItemLivro(new Livro("002", "Deuses Americanos", "wegwg", "wgwrg", 2001), 9);
        ItemLivro l3 = new ItemLivro(new Livro("003", "Java", "wegwg", "wgwrg", 2002), 8);
        ItemLivro l4 = new ItemLivro(new Livro("004", "C++", "wegwg", "wgwrg", 2003), 7);
        ItemLivro l5 = new ItemLivro(new Livro("005", "Bíblia", "wegwg", "wgwrg", 2004), 6);
        ItemLivro l6 = new ItemLivro(new Livro("006", "CSS", "wegwg", "wgwrg", 2005), 5);
        ItemLivro l7 = new ItemLivro(new Livro("007", "SQL", "wegwg", "wgwrg", 2006), 4);
        ItemLivro l8 = new ItemLivro(new Livro("008", "Oracle", "wegwg", "wgwrg", 2007), 3);
        ItemLivro l9 = new ItemLivro(new Livro("009", "Planeta Terra", "wegwg", "wgwrg", 2008), 2);
        ItemLivro l10 = new ItemLivro(new Livro("010", "Conhecendo o Canadá", "wegwg", "wgwrg", 2009), 1);
        ItemLivro l11 = new ItemLivro(new Livro("011", "PHP", "wegwg", "wgwrg", 2010), 0);
        ItemLivro l12 = new ItemLivro(new Livro("012", "PL/SQL", "wegwg", "wgwrg", 2011), 5);
        ItemLivro l13 = new ItemLivro(new Livro("013", "MySQL", "wegwg", "wgwrg", 2012), 6);
        ItemLivro l14 = new ItemLivro(new Livro("014", "Arduíno", "wegwg", "wgwrg", 2013), 8);
        ItemLivro l15 = new ItemLivro(new Livro("015", "Harry Potter", "wegwg", "wgwrg", 2014), 0);

        repositorioLivro.addItemLivro(l1);
        repositorioLivro.addItemLivro(l2);
        repositorioLivro.addItemLivro(l3);
        repositorioLivro.addItemLivro(l4);
        repositorioLivro.addItemLivro(l5);
        repositorioLivro.addItemLivro(l6);
        repositorioLivro.addItemLivro(l7);
        repositorioLivro.addItemLivro(l8);
        repositorioLivro.addItemLivro(l9);
        repositorioLivro.addItemLivro(l10);
        repositorioLivro.addItemLivro(l11);
        repositorioLivro.addItemLivro(l12);
        repositorioLivro.addItemLivro(l13);
        repositorioLivro.addItemLivro(l14);
        repositorioLivro.addItemLivro(l15);
        
        Emprestimo e1 = new Emprestimo(c1, l1);
        Emprestimo e2 = new Emprestimo(c1, l3);
        Emprestimo e3 = new Emprestimo(c2, l1);
        Emprestimo e4 = new Emprestimo(c3, l4);
        Emprestimo e5 = new Emprestimo(c4, l7);
        Emprestimo e6 = new Emprestimo(c5, l10);
        Emprestimo e7 = new Emprestimo(c5, l14);
        Emprestimo e8 = new Emprestimo(c5, l9);
        
        e1.setDevolucaoEfetiva(new DateTime(2015, 10, 30, 0, 0));
        e2.setDevolucaoEfetiva(new DateTime(2015, 10, 6, 0, 0));
        e3.setDevolucaoEfetiva(new DateTime(2015, 11, 20, 0, 0));
        e4.setDevolucaoEfetiva(new DateTime(2015, 12, 23, 0, 0));
        e5.setDevolucaoEfetiva(new DateTime(2015, 1, 3, 0, 0));
        e6.setDevolucaoEfetiva(new DateTime(2015, 10, 7, 0, 0));
        e7.setDevolucaoEfetiva(new DateTime(2015, 10, 8, 0, 0));
        e8.setDevolucaoEfetiva(new DateTime(2015, 10, 6, 0, 0));
        
        repositorioEmprestimo.addEmprestimo(e1);
        repositorioEmprestimo.addEmprestimo(e2);
        repositorioEmprestimo.addEmprestimo(e3);
        repositorioEmprestimo.addEmprestimo(e4);
        repositorioEmprestimo.addEmprestimo(e5);
        repositorioEmprestimo.addEmprestimo(e6);
        repositorioEmprestimo.addEmprestimo(e7);
        repositorioEmprestimo.addEmprestimo(e8);
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
                    new ClienteUI(repositorioCliente).executar();
                    break;
                case MainMenu.OP_LIVRO:
                    new LivroUI(repositorioCliente, repositorioLivro, repositorioEmprestimo).executar();
                    break;
                case MainMenu.OP_RELATORIO:
                    new RelatorioUI(repositorioCliente, repositorioLivro, repositorioEmprestimo).executar();
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
