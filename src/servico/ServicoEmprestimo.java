/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.EmprestimoDAOBD;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Emprestimo;
import model.ItemLivro;
import org.joda.time.LocalDate;

/**
 * Classe com operações de empréstimo.
 *
 * @author max
 */
public class ServicoEmprestimo {

    private EmprestimoDAOBD emprestimoDAOBD;

    /**
     * 
     */
    public ServicoEmprestimo() {
        this.emprestimoDAOBD = new EmprestimoDAOBD();
    }

    public void addEmprestimo(Emprestimo emprestimo) {
        emprestimoDAOBD.inserir(emprestimo);
    }

    public Emprestimo getEmprestimoClienteISBNLivro(Cliente cliente, String ISBN) {
        return emprestimoDAOBD.getEmprestimoClienteISBNLivro(cliente, ISBN);
    }

    public void editarEmprestimo(Emprestimo emprestimo) {
        emprestimoDAOBD.atualizar(emprestimo);
    }

    public List<Emprestimo> getListaEmprestimo() {
        return emprestimoDAOBD.listar();
    }

    public void livrosMaisEmprestados() {
        emprestimoDAOBD.livrosMaisRetirados();
    }

    /*
     public void clientesMaisRetiraramLivro() {
     emprestimoDAOBD.clientesMaisRetiraramLivro();
     }*/
    public void clientesMaisAtrasaram() {
        emprestimoDAOBD.clientesMaisAtrasaram();
    }

    public void emprestar(ItemLivro itemLivro, Cliente cliente) {
        Emprestimo emprestimo = new Emprestimo(cliente, itemLivro);
        addEmprestimo(emprestimo);
        itemLivro.removerQtdLivroDisponivel(1);
        new servico.ServicoItemLivro().editaItemLivro(itemLivro);
        JOptionPane.showMessageDialog(null, "Empréstimo efetuado com sucesso. Data de devolução: "
                + emprestimo.getDataDevolucao().getDayOfMonth() + "/"
                + emprestimo.getDataDevolucao().getMonthOfYear() + "/"
                + emprestimo.getDataDevolucao().getYear());
    }

    public void devolver(Emprestimo emprestimo) {
        emprestimo.setDevolucaoEfetiva(new LocalDate());
        emprestimo.setAtivo(false);
        emprestimo.getItemLivro().adicionarQtdLivroDisponivel(1);
        editarEmprestimo(emprestimo);
        if (emprestimo.getDiasAtraso() > 0) {
            JOptionPane.showMessageDialog(null, "Devolução efetuada com sucesso. Dias de atraso: " + emprestimo.getDiasAtraso());
        } else {
            JOptionPane.showMessageDialog(null, "Devolução efetuada com sucesso.");
        }

    }

    public Emprestimo getEmprestimoID(int selecionado) {
        return emprestimoDAOBD.procurarPorId(selecionado);
    }
}
