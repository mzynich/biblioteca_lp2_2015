/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import banco.ConnectionFactory;
import interfaces.LivroDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Livro;

/**
 *
 * @author 631420375
 */
public class LivroDAOBD implements LivroDAO {

    private Connection conexao;
    private PreparedStatement comando;

    @Override
    public void inserir(Livro livro) {
        int id = 0;

        try {
            String sql = "INSERT INTO livro(isbn,nome,autor,editora,ano) VALUES(?,?,?,?,?)";
            conectarObtendoId(sql);
            comando.setString(1, livro.getISBN());
            comando.setString(2, livro.getNome());
            comando.setString(3, livro.getAutor());
            comando.setString(4, livro.getEditora());
            comando.setInt(5, livro.getAno());
            comando.executeUpdate();
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt(1);
                livro.setId(id);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Livro livro) {
        try {
            String sql = "DELETE FROM livro WHERE id = ?";
            conectar(sql);
            comando.setInt(1, livro.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizar(Livro livro) {
        try {
            String sql = "UPDATE livro SET isbn=? ,nome=? ,autor=?,editora=?,ano=? WHERE id=?";
            conectar(sql);
            comando.setString(1, livro.getISBN());
            comando.setString(2, livro.getNome());
            comando.setString(3, livro.getAutor());
            comando.setString(4, livro.getEditora());
            comando.setInt(5, livro.getAno());
            comando.setInt(6, livro.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Livro> listar() {
        ArrayList<Livro> array = new ArrayList<>();
        try {
            String sql = "SELECT * FROM livro ORDER BY id";
            conectar(sql);
            ResultSet r = comando.executeQuery();
            while (r.next()) {
                array.add(new Livro(r.getInt("id"), r.getString("ISBN"), r.getString("nome"), r.getString("autor"),
                        r.getString("editora"), r.getInt("ano")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return array;
    }

    @Override
    public Livro procurarPorId(int id) {
        try {
            String sql = "SELECT * FROM livro WHERE id=?";
            conectar(sql);
            comando.setInt(1, id);
            ResultSet r = comando.executeQuery();
            if (r.next()) {
                return new Livro(r.getInt("id"), r.getString("ISBN"), r.getString("nome"), r.getString("autor"),
                        r.getString("editora"), r.getInt("ano"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return null;
    }

    @Override
    public Livro procurarPorISBN(String ISBN) {
        try {
            String sql = "SELECT * FROM livro WHERE isbn=?";
            conectar(sql);
            comando.setString(1, ISBN);
            ResultSet r = comando.executeQuery();
            if (r.next()) {
                return new Livro(r.getInt("id"), r.getString("ISBN"), r.getString("nome"), r.getString("autor"),
                        r.getString("editora"), r.getInt("ano"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return null;
    }

    @Override
    public List<Livro> procurarPorNome(String nome) {
        List<Livro> array = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE nome ILIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                array.add(new Livro(resultado.getInt("id"), resultado.getString("isbn"), resultado.getString("nome"),
                        resultado.getString("autor"), resultado.getString("editora"), resultado.getInt("ano")));
            }
            return array;

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
