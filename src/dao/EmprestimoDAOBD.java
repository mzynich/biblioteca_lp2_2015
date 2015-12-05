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
import org.joda.time.LocalDate;
import servico.ServicoItemLivro;
import util.Console;
import util.Validador;

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
            if (emprestimo.getDevolucaoEfetiva() != null) {
                comando.setDate(5, new java.sql.Date(emprestimo.getDevolucaoEfetiva().toDate().getTime()));
            } else {
                comando.setDate(5, null);
            }
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
            String sql = "DELETE FROM emprestimo WHERE id = ?";
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
            comando.setInt(8, emprestimo.getId());
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
            String sql = "SELECT * FROM emprestimo ORDER BY id";
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

    public Emprestimo getEmprestimoClienteISBNLivro(Cliente cliente, String ISBN) {
        try {
            ItemLivro i = new ItemLivroDAOBD().procurarPorISBN(ISBN);
            String sql = "SELECT * FROM emprestimo WHERE codCliente=? AND codItemLivro=? AND ativo=true";
            conectar(sql);
            comando.setInt(1, cliente.getId());
            comando.setInt(2, i.getId());
            ResultSet r = comando.executeQuery();
            if (r.next()) {
                LocalDate dataEmprestimo = new LocalDate(r.getDate("dataEmprestimo"));
                LocalDate dataDevolucao = new LocalDate(r.getDate("dataDevolucao"));
                LocalDate devolucaoEfetiva = new LocalDate(r.getDate("devolucaoEfetiva"));
                int diasAtraso = r.getInt("diasAtraso");
                boolean ativo = r.getBoolean("ativo");
                return new Emprestimo(r.getInt("id"), cliente, i, dataEmprestimo, dataDevolucao, devolucaoEfetiva, diasAtraso, ativo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimoDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return null;
    }

    @Override
    public void livrosMaisRetirados() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-50s", "Livro") + "\t"
                + String.format("%-3s", "|Quantidade de empréstimos"));
        try {
            String sql = "SELECT livro.nome,COUNT(*) AS quantidade FROM emprestimo "
                    + "INNER JOIN itemLivro ON (emprestimo.codItemLivro = itemLivro.id) "
                    + "INNER JOIN livro ON (itemLivro.codLivro = livro.id) "
                    + "GROUP BY nome,codItemLivro "
                    + "ORDER BY quantidade DESC";
            conectar(sql);
            ResultSet r = comando.executeQuery();
            while (r.next()) {
                System.out.println(String.format("%-50s", r.getString(1)) + "\t"
                        + String.format("%-3s", "|" + r.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimoDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    public void clientesMaisRetiraramLivro() {
        String livro = Console.scanString("Nome do livro: ");
        if (Validador.nomeLivroValido(livro)) {
            List<ItemLivro> lista = new ServicoItemLivro().pesquisaItemLivroNome(livro);
            if (lista.size() <= 0) {
                System.out.println("Livro não existente no sistema");
            } else {
                System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                        + String.format("%-50s", "|NOME") + "\t");
                for (int i = 0; i < lista.size(); i++) {
                    System.out.println(String.format("%-13s", i) + "\t"
                            + String.format("%-30s", "|" + lista.get(i).getLivro().getNome()));
                }
                int opcao = Console.scanInt("Digite o código do livro ou -1 para cancelar: ");
                if (opcao != -1 && opcao < lista.size()) {
                    System.out.println("-----------------------------\n");
                    System.out.println(String.format("%-50s", "Cliente") + "\t"
                            + String.format("%-3s", "|Quantidade de empréstimos"));
                    try {
                        String sql = "SELECT cliente.nome, COUNT(*) AS quantidade FROM emprestimo "
                                + "INNER JOIN cliente ON (emprestimo.codCliente = cliente.id)"
                                + "INNER JOIN itemLivro ON (emprestimo.codItemLivro = itemLivro.id) "
                                + "INNER JOIN livro ON (itemLivro.codLivro = livro.id) AND livro.nome = ?"
                                + "GROUP BY cliente.nome ORDER BY quantidade DESC";
                        conectar(sql);
                        comando.setString(1, lista.get(opcao).getLivro().getNome());
                        ResultSet r = comando.executeQuery();
                        while (r.next()) {
                            System.out.println(String.format("%-50s", r.getString(1)) + "\t"
                                    + String.format("%-3s", "|" + r.getInt(2)));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(EmprestimoDAOBD.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        fecharConexao();
                    }
                }
            }
        }
    }

    public void clientesMaisAtrasaram() {
        try {
            String sql = "SELECT cliente.nome,COUNT(*) AS quantidade FROM emprestimo INNER JOIN cliente ON (emprestimo.codCliente = cliente.id) "
                    + "AND devolucaoEfetiva-dataDevolucao>0 "
                    + "GROUP BY cliente.nome "
                    + "ORDER BY quantidade DESC";
            conectar(sql);
            ResultSet r = comando.executeQuery();
            System.out.println(String.format("%-50s", "Cliente") + "\t"
                    + String.format("%-3s", "|" + "Total de atrasos"));
            while (r.next()) {
                System.out.println(String.format("%-50s", r.getString(1)) + "\t"
                        + String.format("%-3s", "|" + r.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmprestimoDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
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
            Logger.getLogger(LivroDAOBD.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}
