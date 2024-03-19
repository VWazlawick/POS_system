package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import controller.ClienteController;
import controller.FormaPagamentoController;
import controller.ProdutoVendaController;
import helper.SQLiteDataHelper;
import model.FormaPagamento;
import model.Produto;
import model.ProdutoVenda;
import model.Venda;

public class VendaDAO implements GenericDAO<Venda> {
    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;
    private String nomeTabela = "VENDA";

    private String[]colunas = {"id","vlrtotal","qtdtotal","dtvenda","idcliente","idformapagamento"};

    private Context context;

    private static VendaDAO instancia;

    public static VendaDAO getInstance(Context context){
        if(instancia == null){
            return instancia = new VendaDAO(context);
        }else{
            return instancia;
        }
    }

    private VendaDAO(Context context){
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "Koffee", null, 1);

        bd = openHelper.getWritableDatabase();

    }



    @Override
    public long insert(Venda obj) {
        try{
            ContentValues cv = new ContentValues();

            cv.put(colunas[1], obj.getVlrTotal());
            cv.put(colunas[2], obj.getQtdTotal());
            cv.put(colunas[3], obj.getDtVenda());
            cv.put(colunas[4], obj.getCliente().getId());
            cv.put(colunas[5], obj.getFormaPagamento().getId());

            return bd.insert(nomeTabela, null, cv);
        }
        catch (Exception ex){
            Log.e("Erro", "vendaDao.insert: " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Venda obj) {
        try{
            ContentValues cv = new ContentValues();

            cv.put(colunas[1], obj.getVlrTotal());
            cv.put(colunas[2], obj.getQtdTotal());
            cv.put(colunas[3], obj.getDtVenda());

            String[] identificador = {String.valueOf(obj.getId())};

            return bd.update(nomeTabela, cv, colunas[0] + "= ?", identificador);
        }
        catch (Exception ex){
            Log.e("Erro", "vendaDao.update: " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Venda obj) {
        try{
            String[]identificador = {String.valueOf(obj.getId())};
            return bd.delete(nomeTabela, colunas[0] + "= ?", identificador);
        }
        catch (Exception ex){
            Log.e("Erro", "vendaDao.delete: " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Venda> getAll() {
        ArrayList<Venda> lista = new ArrayList<>();
        Cursor cursor = null;
        try{
            cursor = bd.query(nomeTabela, colunas, null,null,null,null, colunas[0]);
            if(cursor.moveToFirst()){
                do {
                    Venda venda = new Venda();

                    venda.setId(cursor.getInt(0));
                    venda.setVlrTotal((cursor.getDouble(1)));
                    venda.setQtdTotal(cursor.getInt(2));
                    venda.setDtVenda(cursor.getString(3));

                    ClienteController cliente = new ClienteController(context);
                    venda.setCliente(cliente.buscarCliente(cursor.getInt(4)));


                    FormaPagamentoController fpc = new FormaPagamentoController(context);
                    venda.setFormaPagamento(fpc.buscarFormaPagamento(cursor.getInt(5)));

                    lista.add(venda);
                }while(cursor.moveToNext());
            }
        }
        catch (Exception ex){
            Log.e("Erro", "vendaDao.getAll: " + ex.getMessage());
        }
        finally {
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }

        return lista;
    }

    @Override
    public Venda getById(int id) {
        Cursor cursor = null;
        try{
            String[]identificador = {String.valueOf(id)};
            cursor = bd.query(nomeTabela, colunas,colunas[0] + " = ?", identificador, null,null,null,null);

            if(cursor.moveToNext()){
                Venda venda = new Venda();

                venda.setId(cursor.getInt(0));
                venda.setVlrTotal(cursor.getDouble(1));
                venda.setQtdTotal(cursor.getInt(2));
                venda.setDtVenda(cursor.getString(3));

                ClienteController cliente = new ClienteController(context);
                venda.setCliente(cliente.buscarCliente(cursor.getInt(4)));

                ProdutoVendaController pvc = new ProdutoVendaController(context);
                ArrayList<ProdutoVenda> lista = pvc.buscarPorIdVenda(venda.getId());
                venda.setListaProdutos(lista);

                FormaPagamentoController fpc = new FormaPagamentoController(context);
                venda.setFormaPagamento(fpc.buscarFormaPagamento(cursor.getInt(5)));


                return venda;
            }
        }
        catch (Exception ex){
            Log.e("Erro: ", ex.getMessage());
        }
        finally {
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return null;
    }
}
