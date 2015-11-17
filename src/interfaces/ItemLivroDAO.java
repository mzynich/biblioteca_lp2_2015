package interfaces;

import java.util.List;
import model.ItemLivro;

/**
 *
 * @author lhries
 */
public interface ItemLivroDAO {

    public void inserir(ItemLivro itemLivro);

    public void deletar(ItemLivro itemLivro);

    public void atualizar(ItemLivro itemLivro);

    public List<ItemLivro> listar();

    public ItemLivro procurarPorId(int id);

    public ItemLivro procurarPorISBN(String ISBN);

    public List<ItemLivro> procurarPorNome(String nome);

}
