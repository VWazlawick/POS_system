package controller;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import dao.ProdutoDAO;
import dao.ProdutoVendaDAO;
import model.Produto;
import model.ProdutoVenda;

public class ProdutoVendaController {

    private Context context;

    public ProdutoVendaController(Context context){
        this.context = context;
    }

    public String salvarProdutoVenda(int qtdProduto, Produto produto, int idVenda){
        try {
            ProdutoVenda produtoVenda = new ProdutoVenda();
            ArrayList<ProdutoVenda>lista = new ArrayList<>();
            lista = ProdutoVendaDAO.getInstance(context).getAll();

            if(lista==null){
                produtoVenda.setId(1);
            }
            else{
                produtoVenda.setId(lista.size()+1);
            }
            produtoVenda.setProduto(produto);
            produtoVenda.setQntdProduto(qtdProduto);
            produtoVenda.setIdVenda(idVenda);

            ProdutoVendaDAO.getInstance(context).insert(produtoVenda);
        }
        catch(Exception exception){
            return "Erro ao gravar Aluno";
        }
        return null;
    }

    public String atualizarProdutoVenda(ProdutoVenda produtoVenda){
        try {
            ProdutoVendaDAO.getInstance(context).update(produtoVenda);
        }
        catch (Exception ex){
            return "Erro ao atualizar cadastro";
        }
        return null;
    }

    public String excluirProdutoVenda(ProdutoVenda produtoVenda){
        try{
            ProdutoVendaDAO.getInstance(context).delete(produtoVenda);
        }
        catch (Exception ex){
            return "Erro ao excluir: " + ex.getMessage();
        }
        return null;
    }

    public ProdutoVenda buscarProdutoVenda(int id){
        try {
            return ProdutoVendaDAO.getInstance(context).getById(id);
        }
        catch (Exception ex){
        }
        return null;
    }

    public ArrayList<ProdutoVenda> buscarTodosProdutoVenda(){
        ArrayList<ProdutoVenda> lista = new ArrayList<>();
        try {
            lista = ProdutoVendaDAO.getInstance(context).getAll();
        }
        catch (Exception ex){
            Log.e("ERROR", ex.getMessage());
        }
        return lista;
    }
    public ArrayList<ProdutoVenda> buscarPorIdVenda(int id){
        try{
            return ProdutoVendaDAO.getInstance(context).buscarPorIdVenda(id);
        }
        catch (Exception ex){
            Log.e("ERROR", ex.getMessage());
        }
        return null;
    }
}
