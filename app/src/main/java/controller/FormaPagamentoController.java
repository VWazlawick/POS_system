package controller;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import dao.FormaPagamentoDAO;
import model.FormaPagamento;

public class FormaPagamentoController {

    private Context context;

    public FormaPagamentoController(Context context) {
        this.context = context;
    }

    public String salvarFormaPagamento(String descricao){
        try{
            FormaPagamento fp = new FormaPagamento();

            ArrayList<FormaPagamento> lista = new ArrayList<>();
            lista = FormaPagamentoDAO.getInstance(context).getAll();

            if(lista==null){
                fp.setId(1);
            }else{
                fp.setId(lista.size()+1);
            }

            fp.setDescricao(descricao);

            FormaPagamentoDAO.getInstance(context).insert(fp);
        }
        catch (Exception ex){
            return "Erro ao gravar Forma de Pagamento";
        }
        return null;
    }

    public String atualizarFormaPagamento(FormaPagamento formaPagamento){
        try {
            FormaPagamentoDAO.getInstance(context).update(formaPagamento);
        }
        catch (Exception ex){
            return "Erro ao atualizar Forma de Pagamento";
        }

        return null;
    }

    public String excluirFormaPagamento(FormaPagamento formaPagamento){
        try {
            FormaPagamentoDAO.getInstance(context).delete(formaPagamento);
        }
        catch (Exception ex){
            return "Erro ao deletar Forma de Pagamento";
        }

        return null;
    }

    public FormaPagamento buscarFormaPagamento(int id){
        try{
            return FormaPagamentoDAO.getInstance(context).getById(id);
        }
        catch (Exception ex){
            Log.e("Erro", ex.getMessage());
        }
        return null;
    }

    public ArrayList<FormaPagamento> buscarTodasFormasPagamento(){
        ArrayList<FormaPagamento> lista = new ArrayList<>();
        try{
            lista = FormaPagamentoDAO.getInstance(context).getAll();
        }
        catch (Exception ex){
            Log.e("Erro", ex.getMessage());
        }
        return lista;
    }
}
