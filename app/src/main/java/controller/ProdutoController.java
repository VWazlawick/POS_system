package controller;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import dao.ProdutoDAO;
import model.Produto;

public class ProdutoController {

    private Context context;

    public ProdutoController(Context context){
        this.context = context;
    }

    public String salvarProduto(String dsProduto, String  custoProduto, String vlrProduto){
        try {
            if(dsProduto.isEmpty() || dsProduto.equals("")){
                return "Informe a descrição do produto!!";
            }
            if(vlrProduto.isEmpty() || vlrProduto.equals("") || Double.parseDouble(vlrProduto)==0){
               return "Informe o valor!!";
            }
            if((custoProduto.isEmpty() || custoProduto.equals("") || Double.parseDouble(custoProduto)==0)){
                return "Informe o custo!";
            }

            Double valor = Double.parseDouble(vlrProduto);
            Double custo = Double.parseDouble(custoProduto);


            if(custo>=valor){
                return "Valor de venda não pode ser menor que o Custo!";
            }

            Produto produto = new Produto();
            produto.setId(ProdutoDAO.getInstance(context).getAll().size()+1);
            produto.setDsProduto(dsProduto);
            produto.setVlrProduto(valor);
            produto.setQtdProduto(0);
            produto.setCustoProduto(custo);

            ProdutoDAO.getInstance(context).insert(produto);
        }
        catch(Exception exception){
            return "Erro ao gravar Aluno";
        }
        return null;
    }

    public String atualizarProduto(Produto produto){
        try {
            if(produto.getDsProduto().isEmpty() || produto.getDsProduto().equals("")){
                return "Informe a descrição do produto!!";
            }
            if(produto.getVlrProduto() == 0){
                return "Informe o valor!!";
            }
            if(produto.getCustoProduto()==0){
                return "Informe o custo";
            }

            ProdutoDAO.getInstance(context).update(produto);
        }
        catch (Exception ex){
            return "Erro ao atualizar cadastro";
        }
        return null;
    }

    public String excluirProduto(Produto produto){
        try{
            ProdutoDAO.getInstance(context).delete(produto);
        }
        catch (Exception ex){
            return "Erro ao excluir";
            }
        return null;
    }

    public Produto buscarProduto(int id){
        try {
            return ProdutoDAO.getInstance(context).getById(id);
        }
        catch (Exception ex){
        }
        return null;
    }

    public ArrayList<Produto> buscarTodosProdutos(){
        ArrayList<Produto> lista = new ArrayList<>();
        try {
            lista = ProdutoDAO.getInstance(context).getAll();
        }
        catch (Exception ex){
            Log.e("ERROR", ex.getMessage());
        }
        return lista;
    }
}
