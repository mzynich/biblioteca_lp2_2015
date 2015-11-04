package model;

/**
 *
 * @author mzynich
 */
public class ItemLivro {
    private int id;
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
    
    public ItemLivro(int id,Livro livro, int quantidade) {
        this.id = id;
        if (livro != null) {
            this.livro = livro;
        }
        if (quantidade >= 0) {
            this.quantidadeTotal = quantidade;
            this.quantidadeDisponivel = quantidade;
        }
    }
    
    public ItemLivro(int id,Livro livro, int quantidadeDisponivel, int quantidadeTotal) {
        this.id = id;
        if (livro != null) {
            this.livro = livro;
        }
        if (quantidadeTotal >= 0) {
            this.quantidadeTotal = quantidadeTotal;
            if(quantidadeDisponivel>=0 && quantidadeDisponivel<=quantidadeTotal){
                this.quantidadeDisponivel = quantidadeDisponivel;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
