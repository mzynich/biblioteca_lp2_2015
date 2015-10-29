
package interfaces;

import java.util.List;
import model.Emprestimo;

/**
 *
 * @author lhries
 */
public interface EmprestimoDAO {
    public void inserir(Emprestimo emprestimo);
    public void deletar(Emprestimo emprestimo);
    public void atualizar(Emprestimo emprestimo);
    public List<Emprestimo> listar();
    public Emprestimo procurarPorId(int id);
    public List<Emprestimo> procurarPorMatriculaCliente(String matricula);
    public List<Emprestimo> procurarPorISBNLivro(String ISBN);
    public List<Emprestimo> procurarPorNomeCliente(String nome);
    public List<Emprestimo> procurarPorNomeLivro(String nome);    
}
