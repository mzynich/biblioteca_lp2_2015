/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import model.Emprestimo;

/**
 *
 * @author mzynich
 */
public class RepositorioEmprestimo {

    private ArrayList<Emprestimo> repositorioEmprestimo;

    public RepositorioEmprestimo() {
        repositorioEmprestimo = new ArrayList<>();
    }

    public boolean addEmprestimo(Emprestimo emprestimo) {
        if (emprestimo != null) {
            return repositorioEmprestimo.add(emprestimo);
        }
        return false;
    }

    public ArrayList<Emprestimo> getListaEmprestimos() {
        return this.repositorioEmprestimo;
    }

}
