package model;

/**
 *
 * @author mzynich
 */
public class ItemLivro {

    private Livro livro;
    private int quantidadeTotal, quantidadeDisponivel;

    public ItemLivro(Livro livro, int quantidade) {
        if (livro != null) {
            this.livro = livro;
        }
        if (quantidade >= 0) {
            this.quantidadeTotal = quantidade;
            this.quantidadeDisponivel = quantidade;
        }
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        if (livro != null) {
            this.livro = livro;
        }
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void adicionarQtdLivroTotal(int quantidade) {
        if (quantidade >= 0) {
            this.quantidadeTotal += quantidade;
            this.quantidadeDisponivel += quantidade;
        }
    }

    public void removerQtdLivroTotal(int quantidade) {
        if (quantidade >= 0 && quantidade <= this.quantidadeTotal) {
            this.quantidadeTotal -= quantidade;
            this.quantidadeDisponivel -= quantidade;
        }
    }

    public void adicionarQtdLivroDisponivel(int quantidade) {
        if (quantidade >= 0) {
            this.quantidadeDisponivel += quantidade;
        }
    }

    public void removerQtdLivroDisponivel(int quantidade) {
        if (quantidade >= 0 && quantidade <= this.quantidadeDisponivel) {
            this.quantidadeDisponivel -= quantidade;
        }
    }

    public void setQuantidade(int quantidade) {
        if (quantidade > 0) {
            this.quantidadeTotal = quantidade;
            this.quantidadeDisponivel = quantidade;
        }
    }

}
