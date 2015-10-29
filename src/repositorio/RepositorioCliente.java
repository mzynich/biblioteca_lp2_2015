/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author mzynich
 */
public class RepositorioCliente {

    private ArrayList<Cliente> repositorioCliente;

    public RepositorioCliente() {
        repositorioCliente = new ArrayList<>();
    }

    public boolean addCliente(Cliente cliente) {
        if (cliente != null && pesquisaClienteMatricula(cliente.getMatricula())==null) {
            return repositorioCliente.add(cliente);
        }
        return false;
    }

    public boolean removeCliente(Cliente cliente) {
        if (cliente != null && pesquisaClienteMatricula(cliente.getMatricula())!=null) {
            return repositorioCliente.remove(cliente);
        }
        return false;
    }

    public Cliente pesquisaClienteMatricula(String matricula) {
        for (Cliente c : repositorioCliente) {
            if (c.getMatricula().equalsIgnoreCase(matricula)) {
                return c;
            }
        }
        return null;
    }

    public Cliente pesquisaClienteNome(String nome) {
        for (Cliente c : repositorioCliente) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Cliente> getListaClientes() {
        return this.repositorioCliente;
    }
}
