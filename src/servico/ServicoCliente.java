/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servico;

import dao.ClienteDAOBD;
import java.util.List;
import model.Cliente;

/**
 *
 * @author mzynich
 */
public class ServicoCliente {

    ClienteDAOBD clienteDAO;

    public ServicoCliente() {
        clienteDAO = new ClienteDAOBD();
    }

    public Cliente pesquisaClienteMatricula(String matricula) {
        return clienteDAO.procurarPorMatricula(matricula);
    }

    public Cliente pesquisaClienteID(int id) {
        return clienteDAO.procurarPorId(id);
    }

    public void addCliente(Cliente cliente) {
        clienteDAO.inserir(cliente);
    }

    public List<Cliente> getListaClientes() {
        return clienteDAO.listar();
    }

    public void editarCliente(Cliente c) {
        clienteDAO.atualizar(c);
    }

    public int getQtdEmprestimosAtuais(Cliente cliente) {
        return clienteDAO.quantidadeEmprestimosAtuais(cliente);
    }

    public void excluir(Cliente c) {
        clienteDAO.deletar(c);
    }

}
