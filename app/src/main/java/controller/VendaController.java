package controller;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.ProdutoVendaDAO;
import dao.VendaDAO;
import model.Produto;
import model.ProdutoVenda;
import model.Venda;

public class VendaController {

    private Context context;

    public VendaController(Context context){
        this.context = context;
    }

    public String salvarVenda(Venda v){
        try {
            Venda venda = new Venda();
            ArrayList<Venda> lista;
            lista = VendaDAO.getInstance(context).getAll();

            venda.setVlrTotal(v.getVlrTotal());
            venda.setQtdTotal(v.getQtdTotal());

            venda.setCliente(v.getCliente());
            venda.setFormaPagamento(v.getFormaPagamento());


            ProdutoVendaController pvc = new ProdutoVendaController(context);

            for(int i =0;i<v.getListaProdutos().size();i++){
                ProdutoVenda produto = v.getListaProdutos().get(i);

                pvc.salvarProdutoVenda(produto.getQntdProduto(), produto.getProduto(), venda.getId());
            }

            SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
            Date data = new Date();
            String dataFormatada = formataData.format(data);

            venda.setDtVenda(dataFormatada);

            VendaDAO.getInstance(context).insert(venda);
        }
        catch(Exception exception){
            return "Erro ao gravar Aluno";
        }
        return null;
    }

    public String atualizarVenda(Venda venda){
        try {
            VendaDAO.getInstance(context).update(venda);
        }
        catch (Exception ex){
            return "Erro ao atualizar cadastro";
        }
        return null;
    }

    public String excluirVenda(Venda venda){
        try{
            VendaDAO.getInstance(context).delete(venda);
        }
        catch (Exception ex){
            return "Erro ao excluir: " + ex.getMessage();
        }
        return null;
    }

    public Venda buscarVenda(int id){
        try {
            return VendaDAO.getInstance(context).getById(id);
        }
        catch (Exception ex){
        }
        return null;
    }

    public ArrayList<Venda> buscarTodoasVenda(){
        ArrayList<Venda> lista = new ArrayList<>();
        try {
            lista = VendaDAO.getInstance(context).getAll();
        }
        catch (Exception ex){
            Log.e("ERROR", ex.getMessage());
        }
        return lista;
    }
}
