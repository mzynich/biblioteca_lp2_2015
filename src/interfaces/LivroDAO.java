
package interfaces;

import java.util.List;
import model.Livro;

/**
 *
 * @author lhries
 */
public interface LivroDAO {
    public void inserir(Livro livro);
    public void deletar(Livro livro);
    public void atualizar(Livro livro);
    public List<Livro> listar();
    public Livro procurarPorId(int id);
    public Livro procurarPorISBN(String ISBN);
    public List<Livro> procurarPorNome(String nome);
        
}
