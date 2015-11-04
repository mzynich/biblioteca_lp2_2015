/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import banco.ConnectionFactory;
import interfaces.ItemLivroDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ItemLivro;
import model.Livro;

/**
 *
 * @author 631420375
 */
public class ItemLivroDAOBD implements ItemLivroDAO {

    private Connection conexao;
    private PreparedStatement comando;

    @Override
    public void inserir(ItemLivro itemLivro) {
        int id = 0;

        try {
            String sql = "INSERT INTO itemLivro(codLivro,quantidadeTotal,quantidadeDisponivel) VALUES(?,?,?)";
            conectarObtendoId(sql);
            comando.setInt(1, itemLivro.getLivro().getId());
            comando.setInt(2, itemLivro.getQuantidadeTotal());
            comando.setInt(3, itemLivro.getQuantidadeDisponivel());
            comando.executeUpdate();
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt(1);
                itemLivro.setId(id);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(ItemLivro itemLivro) {
        try {
            String sql = "DELETE FROM itemLivro WHERE id = ? CASCADE";
            conectar(sql);
            comando.setInt(1, itemLivro.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizar(ItemLivro itemLivro) {
        try {
            String sql = "UPDATE itemLivro SET codLivro=? ,quantidadeTotal=?, quantidadeDisponivel=? WHERE id=?";
            conectar(sql);
            comando.setInt(1, itemLivro.getLivro().getId());
            comando.setInt(2, itemLivro.getQuantidadeTotal());
            comando.setInt(3, itemLivro.getQuantidadeDisponivel());
            comando.setInt(4, itemLivro.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<ItemLivro> listar() {
        ArrayList<ItemLivro> array = new ArrayList<>();
        try {
            String sql = "SELECT * FROM itemLivro";
            conectar(sql);
            ResultSet r = comando.executeQuery();
            while (r.next()) {
                int id = r.getInt("id");
                int qtdDisponivel = r.getInt("quantidadeDisponivel");
                int qtdTotal = r.getInt("quantidadeTotal");
                LivroDAOBD l = new LivroDAOBD();
                Livro livro = l.procurarPorId(r.getInt("codLivro"));
                array.add(new ItemLivro(id, livro, qtdDisponivel, qtdTotal));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return array;
    }

    @Override
    public ItemLivro procurarPorId(int id) {
        try {
            String sql = "SELECT * FROM itemLivro WHERE id=?";
            conectar(sql);
            comando.setInt(1, id);
            ResultSet r = comando.executeQuery();
            if (r.next()) {
                int qtdDisponivel = r.getInt("quantidadeDisponivel");
                int qtdTotal = r.getInt("quantidadeTotal");
                LivroDAOBD l = new LivroDAOBD();
                Livro livro = l.procurarPorId(r.getInt("codLivro"));
                return new ItemLivro(id, livro, qtdDisponivel, qtdTotal);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return null;
    }

    @Override
    public ItemLivro procurarPorISBN(String ISBN) {
        try {
            LivroDAOBD l = new LivroDAOBD();
            Livro livro = l.procurarPorISBN(ISBN);
            if (livro != null) {
                String sql = "SELECT * FROM itemLivro WHERE codLivro=?";
                conectar(sql);
                comando.setInt(1, livro.getId());
                ResultSet r = comando.executeQuery();
                if (r.next()) {
                    int idItem = r.getInt("id");
                    int qtdDisponivel = r.getInt("quantidadeDisponivel");
                    int qtdTotal = r.getInt("quantidadeTotal");
                    return new ItemLivro(idItem, livro, qtdDisponivel, qtdTotal);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return null;
    }

    @Override
    public List<ItemLivro> procurarPorNome(String nome) {
        List<ItemLivro> array = new ArrayList<>();
        try {
            LivroDAOBD l = new LivroDAOBD();
            List<Livro> listaLivro = l.procurarPorNome(nome);
            if (listaLivro.size() > 0) {
                for (Livro livro : listaLivro) {
                    String sql = "SELECT * FROM itemLivro WHERE codLivro=?";
                    conectar(sql);
                    comando.setInt(1, livro.getId());
                    ResultSet r = comando.executeQuery();
                    while (r.next()) {
                        int idItem = r.getInt("id");
                        int qtdDisponivel = r.getInt("quantidadeDisponivel");
                        int qtdTotal = r.getInt("quantidadeTotal");
                        array.add(new ItemLivro(idItem, livro, qtdDisponivel, qtdTotal));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (array);
    }

    public void conectar(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
    }

    public void conectarObtendoId(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    public void fecharConexao() {
        try {
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
