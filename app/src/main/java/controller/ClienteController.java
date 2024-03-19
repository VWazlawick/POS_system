package controller;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import dao.ClienteDAO;
import model.Cliente;
import model.Produto;

public class ClienteController {

    private Context context;

    public ClienteController(Context context) {
        this.context = context;
    }

    public String salvarCliente(String nome, String email, String nrTelefone, String endereco){
        try{
            if(nome.isEmpty() || nome.equals("")){
                return "Informe o Nome do Cliente!";
            }
            if(!email.isEmpty() && !email.contains("@")){
                return "Informe um Email válido!";
            }
            if(!nrTelefone.isEmpty() && nrTelefone.length()<11){
                return "Informe um número válido!";
            }


            Cliente c1 = new Cliente();

            c1.setNome(nome);
            c1.setNrTelefone(email);
            c1.setEmail(email);
            c1.setEndereco(endereco);

            ClienteDAO.getInstance(context).insert(c1);
        }
        catch(Exception ex){
            return "Erro ao gravar aluno";
        }
        return null;
    }

    public String atualizarCliente(Cliente cliente){
        try{
            if(cliente.getNome().isEmpty() || cliente.getNome().equals("")){
                return "Informe o Nome do Cliente!";
            }
            if(!cliente.getEmail().isEmpty() && !cliente.getEmail().contains("@")){
                return "Informe um Email válido!";
            }
            if(!cliente.getNrTelefone().isEmpty() && cliente.getNrTelefone().length()<11){
                return "Informe um número válido!";
            }

            ClienteDAO.getInstance(context).update(cliente);
        }
        catch (Exception ex){
            return "Erro ao atualizar Cliente";
        }
        return null;
    }

    public String excluirCliente(Cliente cliente){
        try{
            ClienteDAO.getInstance(context).delete(cliente);
        }
        catch (Exception ex){
            return "Erro ao deletar Cliente";
        }
        return null;
    }

    public Cliente buscarCliente(int id){
        try{
            return ClienteDAO.getInstance(context).getById(id);
        }
        catch (Exception ex){
            Log.e("ERRO: ",  ex.getMessage());
        }
        return null;
    }
    public ArrayList<Cliente> buscarTodosCliente(){
        ArrayList<Cliente> lista = new ArrayList<>();
        try{
            lista = ClienteDAO.getInstance(context).getAll();
        }
        catch (Exception ex){
            Log.e("Erro: ", ex.getMessage());
        }
        return lista;
    }
}
