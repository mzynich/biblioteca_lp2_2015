/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.EmprestimoDAOBD;
import java.util.List;
import model.Cliente;
import model.Emprestimo;

/**
 *
 * @author max
 */
public class ServicoEmprestimo {

    private EmprestimoDAOBD emprestimoDAOBD;

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

    public void clientesMaisRetiraramLivro() {
        emprestimoDAOBD.clientesMaisRetiraramLivro();
    }

    public void clientesMaisAtrasaram() {
        emprestimoDAOBD.clientesMaisAtrasaram();
    }
}
