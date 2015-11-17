/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import banco.ConnectionFactory;
import interfaces.ClienteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;

/**
 *
 * @author 631420375
 */
public class ClienteDAOBD implements ClienteDAO {

    private Connection conexao;
    private PreparedStatement comando;

    @Override
    public void inserir(Cliente cliente) {
        int id = 0;

        try {
            String sql = "INSERT INTO cliente(matricula,nome,telefone) VALUES(?,?,?)";
            conectarObtendoId(sql);
            comando.setString(1, cliente.getMatricula());
            comando.setString(2, cliente.getNome());
            comando.setString(3, cliente.getTelefone());
            comando.executeUpdate();
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt(1);
                cliente.setId(id);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Cliente cliente) {
        try {
            String sql = "DELETE FROM cliente WHERE id = ?";
            conectar(sql);
            comando.setInt(1, cliente.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizar(Cliente cliente) {
        try {
            String sql = "UPDATE cliente SET matricula=? ,nome=? ,telefone=? WHERE id=?";
            conectar(sql);
            comando.setString(1, cliente.getMatricula());
            comando.setString(2, cliente.getNome());
            comando.setString(3, cliente.getTelefone());
            comando.setInt(4, cliente.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Cliente> listar() {
        ArrayList<Cliente> array = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cliente";
            conectar(sql);
            ResultSet r = comando.executeQuery();
            while (r.next()) {
                array.add(new Cliente(r.getInt("id"), r.getString("matricula"), r.getString("nome"), r.getString("telefone")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return array;
    }

    @Override
    public Cliente procurarPorId(int id) {
        try {
            String sql = "SELECT * FROM cliente WHERE id=?";
            conectar(sql);
            comando.setInt(1, id);
            ResultSet r = comando.executeQuery();
            if (r.next()) {
                return new Cliente(r.getInt("id"), r.getString("matricula"), r.getString("nome"), r.getString("telefone"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return null;
    }

    @Override
    public List<Cliente> procurarPorNome(String nome) {
        List<Cliente> array = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet r = comando.executeQuery();

            while (r.next()) {
                array.add(new Cliente(r.getInt("id"), r.getString("matricula"), r.getString("nome"), r.getString("telefone")));
            }
            return array;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (array);
    }

    @Override
    public Cliente procurarPorMatricula(String matricula) {
        try {
            String sql = "SELECT * FROM cliente WHERE matricula=?";
            conectar(sql);
            comando.setString(1, matricula);
            ResultSet r = comando.executeQuery();
            if (r.next()) {
                return new Cliente(r.getInt("id"), r.getString("matricula"), r.getString("nome"), r.getString("telefone"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return null;

    }

    public int quantidadeEmprestimosAtuais(Cliente cliente) {
        try {
            String sql = "SELECT COUNT(*) FROM emprestimo WHERE codCliente=? AND ativo=true";
            conectar(sql);
            comando.setInt(1, cliente.getId());
            ResultSet r = comando.executeQuery();
            if (r.next()) {
                return r.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
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
            Logger.getLogger(ClienteDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
