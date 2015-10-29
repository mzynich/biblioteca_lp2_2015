
package interfaces;

import java.util.List;
import model.Cliente;

/**
 *
 * @author lhries
 */
public interface ClienteDAO {
    public void inserir(Cliente cliente);
    public void deletar(Cliente cliente);
    public void atualizar(Cliente paciente);
    public List<Cliente> listar();
    public Cliente procurarPorId(int id);
    public Cliente procurarPorMatricula(String matricula);
    public List<Cliente> procurarPorNome(String nome);
        
}
