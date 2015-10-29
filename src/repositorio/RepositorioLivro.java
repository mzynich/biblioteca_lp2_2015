package repositorio;

import java.util.ArrayList;
import model.ItemLivro;

/**
 *
 * @author mzynich
 */
public class RepositorioLivro {

    ArrayList<ItemLivro> repositorioLivro;

    public RepositorioLivro() {
        this.repositorioLivro = new ArrayList<ItemLivro>();
    }

    public boolean addItemLivro(ItemLivro ItemLivro) {
        if (ItemLivro != null) {
            return repositorioLivro.add(ItemLivro);
        }
        return false;
    }

    public boolean removeItemLivro(ItemLivro ItemLivro) {
        if (ItemLivro != null) {
            return repositorioLivro.remove(ItemLivro);
        }
        return false;
    }

    public ItemLivro pesquisaItemLivroNome(String nome) {
        for (ItemLivro i : repositorioLivro) {
            if (i.getLivro().getNome().equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return null;
    }

    public ItemLivro pesquisaItemLivroISBN(String ISBN) {
        for (ItemLivro i : repositorioLivro) {
            if (i.getLivro().getISBN().equalsIgnoreCase(ISBN)) {
                return i;
            }
        }
        return null;
    }

    public ArrayList<ItemLivro> getListaLivros() {
        return this.repositorioLivro;
    }
}
