/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import banco.ConnectionFactory;
import interfaces.EmprestimoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Emprestimo;
import model.ItemLivro;
import model.Livro;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 *
 * @author 631420375
 */
public class EmprestimoDAOBD implements EmprestimoDAO {

    private Connection conexao;
    private PreparedStatement comando;

    @Override
    public void inserir(Emprestimo emprestimo) {
        int id = 0;

        try {
            String sql = "INSERT INTO emprestimo(codCliente,codItemLivro,dataEmprestimo,dataDevolucao,devolucaoEfetiva,diasAtraso,ativo) VALUES(?,?,?,?,?,?,?)";
            conectarObtendoId(sql);
            comando.setInt(1, emprestimo.getCliente().getId());
            comando.setInt(2, emprestimo.getItemLivro().getId());
            comando.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().toDate().getTime()));
            comando.setDate(4, new java.sql.Date(emprestimo.getDataDevolucao().toDate().getTime()));
            comando.setDate(5, new java.sql.Date(emprestimo.getDevolucaoEfetiva().toDate().getTime()));
            comando.setInt(6, emprestimo.getDiasAtraso());
            comando.setBoolean(7, emprestimo.isAtivo());
            comando.executeUpdate();
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt(1);
                emprestimo.setId(id);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Emprestimo emprestimo) {
        try {
            String sql = "DELETE FROM emprestimo WHERE id = ? CASCADE";
            conectar(sql);
            comando.setInt(1, emprestimo.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizar(Emprestimo emprestimo) {
        try {
            String sql = "UPDATE emprestimo SET codCliente=?,codItemLivro=?,dataEmprestimo=?,dataDevolucao=?,devolucaoEfetiva=?,diasAtraso=?,ativo=? WHERE id=?";
            conectar(sql);
            comando.setInt(1, emprestimo.getCliente().getId());
            comando.setInt(2, emprestimo.getItemLivro().getId());
            comando.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().toDate().getTime()));
            comando.setDate(4, new java.sql.Date(emprestimo.getDataDevolucao().toDate().getTime()));
            comando.setDate(5, new java.sql.Date(emprestimo.getDevolucaoEfetiva().toDate().getTime()));
            comando.setInt(6, emprestimo.getDiasAtraso());
            comando.setBoolean(7, emprestimo.isAtivo());
            comando.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Emprestimo> listar() {
        ArrayList<Emprestimo> array = new ArrayList<>();
        try {
            ClienteDAOBD cliente = new ClienteDAOBD();
            ItemLivroDAOBD itemLivro = new ItemLivroDAOBD();
            String sql = "SELECT * FROM emprestimo";
            conectar(sql);
            ResultSet r = comando.executeQuery();
            while (r.next()) {
                int id = r.getInt("id");
                Cliente c = cliente.procurarPorId(r.getInt("codCliente"));
                ItemLivro livro = itemLivro.procurarPorId(r.getInt("codItemLivro"));
                LocalDate dataEmprestimo = new LocalDate(r.getDate("dataEmprestimo"));
                LocalDate dataDevolucao = new LocalDate(r.getDate("dataDevolucao"));
                LocalDate devolucaoEfetiva = new LocalDate(r.getDate("devolucaoEfetiva"));
                int diasAtraso = r.getInt("diasAtraso");
                boolean ativo = r.getBoolean("ativo");
                array.add(new Emprestimo(id, c, livro, dataEmprestimo, dataDevolucao, devolucaoEfetiva, diasAtraso, ativo));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return array;
    }

    @Override
    public Emprestimo procurarPorId(int id) {
        try {
            String sql = "SELECT * FROM emprestimo WHERE id=?";
            conectar(sql);
            comando.setInt(1, id);
            ResultSet r = comando.executeQuery();
            if (r.next()) {
                ClienteDAOBD cliente = new ClienteDAOBD();
                ItemLivroDAOBD itemLivro = new ItemLivroDAOBD();
                Cliente c = cliente.procurarPorId(r.getInt("codCliente"));
                ItemLivro livro = itemLivro.procurarPorId(r.getInt("codItemLivro"));
                LocalDate dataEmprestimo = new LocalDate(r.getDate("dataEmprestimo"));
                LocalDate dataDevolucao = new LocalDate(r.getDate("dataDevolucao"));
                LocalDate devolucaoEfetiva = new LocalDate(r.getDate("devolucaoEfetiva"));
                int diasAtraso = r.getInt("diasAtraso");
                boolean ativo = r.getBoolean("ativo");
                return new Emprestimo(id, c, livro, dataEmprestimo, dataDevolucao, devolucaoEfetiva, diasAtraso, ativo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return null;
    }

    @Override
    public Emprestimo procurarPorISBNLivro(String ISBN) {
        try {
            ItemLivroDAOBD l = new ItemLivroDAOBD();
            ItemLivro itemLivro = l.procurarPorISBN(ISBN);
            if (itemLivro != null) {
                String sql = "SELECT * FROM emprestimo WHERE codItemLivro=?";
                conectar(sql);
                comando.setInt(1, itemLivro.getId());
                ResultSet r = comando.executeQuery();
                if (r.next()) {
                    ClienteDAOBD cliente = new ClienteDAOBD();
                    Cliente c = cliente.procurarPorId(r.getInt("codCliente"));
                    LocalDate dataEmprestimo = new LocalDate(r.getDate("dataEmprestimo"));
                    LocalDate dataDevolucao = new LocalDate(r.getDate("dataDevolucao"));
                    LocalDate devolucaoEfetiva = new LocalDate(r.getDate("devolucaoEfetiva"));
                    int diasAtraso = r.getInt("diasAtraso");
                    boolean ativo = r.getBoolean("ativo");
                    return new Emprestimo(r.getInt("id"), c, itemLivro, dataEmprestimo, dataDevolucao, devolucaoEfetiva, diasAtraso, ativo);
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
    public List<Emprestimo> procurarPorNomeCliente(String nome) {
        List<Emprestimo> array = new ArrayList<>();
        try {
            ItemLivroDAOBD l = new ItemLivroDAOBD();
            List<ItemLivro> listaLivro = l.procurarPorNome(nome);
            if (listaLivro.size() > 0) {
                for (ItemLivro itemLivro : listaLivro) {
                    String sql = "SELECT * FROM emprestimo WHERE codItemLivro=?";
                    conectar(sql);
                    comando.setInt(1, itemLivro.getId());
                    ResultSet r = comando.executeQuery();
                    while (r.next()) {
                        ClienteDAOBD cliente = new ClienteDAOBD();
                        Cliente c = cliente.procurarPorId(r.getInt("codCliente"));
                        LocalDate dataEmprestimo = new LocalDate(r.getDate("dataEmprestimo"));
                        LocalDate dataDevolucao = new LocalDate(r.getDate("dataDevolucao"));
                        LocalDate devolucaoEfetiva = new LocalDate(r.getDate("devolucaoEfetiva"));
                        int diasAtraso = r.getInt("diasAtraso");
                        boolean ativo = r.getBoolean("ativo");
                        array.add(new Emprestimo(r.getInt("id"), c, itemLivro, dataEmprestimo, dataDevolucao, devolucaoEfetiva, diasAtraso, ativo));
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

    @Override
    public Emprestimo procurarPorMatriculaCliente(String matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Emprestimo> procurarPorNomeLivro(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
