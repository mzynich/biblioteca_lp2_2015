package model;

import java.util.ArrayList;
import biblioteca.Biblioteca;

/**
 *
 * @author mzynich
 */
public class Cliente {

    private String matricula, nome, telefone;
    private ArrayList<Emprestimo> emprestimosAtuais;

    public Cliente(String matricula, String nome, String telefone) {
        this.matricula = matricula;
        this.nome = nome;
        this.telefone = telefone;
        this.emprestimosAtuais = new ArrayList<Emprestimo>(Biblioteca.limiteEmprestimos);
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void addEmprestimoAtual(Emprestimo emprestimo) {
        if (emprestimo != null) {
            this.emprestimosAtuais.add(emprestimo);
        }
    }

    public void removeEmprestimoAtual(Emprestimo emprestimo) {
        if (emprestimo != null) {
            this.emprestimosAtuais.remove(emprestimo);
        }
    }

    public ArrayList<Emprestimo> getEmprestimosAtuais() {
        return emprestimosAtuais;
    }

    public Emprestimo getEmprestimoNomeLivro(String nomeLivro) {
        for (Emprestimo e : emprestimosAtuais) {
            if (e.getItemLivro().getLivro().getNome().equalsIgnoreCase(nomeLivro)) {
                return e;
            }
        }
        return null;
    }
}
