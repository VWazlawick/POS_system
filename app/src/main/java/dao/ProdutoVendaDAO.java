package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import controller.ProdutoController;
import controller.VendaController;
import helper.SQLiteDataHelper;
import model.Produto;
import model.ProdutoVenda;
import model.Venda;

public class ProdutoVendaDAO implements GenericDAO<ProdutoVenda> {

    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;
    private String nomeTabela = "PRODUTOVENDA";

    private String[]colunas = {"id","custoproduto","vlrproduto","qtdproduto","idproduto","idvenda"};

    private Context context;

    private static ProdutoVendaDAO instancia;

    public static ProdutoVendaDAO getInstance(Context context){
        if(instancia == null){
            return instancia = new ProdutoVendaDAO(context);
        }else{
            return instancia;
        }
    }

    private ProdutoVendaDAO(Context context){
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "Koffee", null, 1);

        bd = openHelper.getWritableDatabase();

    }

    @Override
    public long insert(ProdutoVenda obj) {
        try{
            ContentValues cv = new ContentValues();

            cv.put(colunas[0], obj.getId());
            cv.put(colunas[1], obj.getCustoProduto());
            cv.put(colunas[2], obj.getVlrProduto());
            cv.put(colunas[3], obj.getQntdProduto());
            cv.put(colunas[4], obj.getProduto().getId());
            cv.put(colunas[5], obj.getIdVenda());

            bd.insert(nomeTabela, null,cv);
        }
        catch (Exception ex){
            Log.e("ERRO", "produtoVendaDao.insert: " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public long update(ProdutoVenda obj) {
        try{

            ContentValues cv = new ContentValues();

            cv.put(colunas[1], obj.getCustoProduto());
            cv.put(colunas[2], obj.getVlrProduto());
            cv.put(colunas[3], obj.getQntdProduto());

            String[]identificador = {String.valueOf(obj.getId())};
            bd.update(nomeTabela, cv,colunas[0] + "= ?", identificador);
        }
        catch (Exception ex){
            Log.e("ERRO", "ProdutoVendaDao.update: " + ex.getMessage());
        }
        return 9;
    }

    @Override
    public long delete(ProdutoVenda obj) {
        try{
            String[]identificador = {String.valueOf(obj.getId())};
            bd.delete(nomeTabela, colunas[0] + "= ?", identificador);
        }
        catch (Exception ex){
            Log.e("ERRO", "produtoVendaDao.delete: " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<ProdutoVenda> getAll() {
        ArrayList<ProdutoVenda> lista = new ArrayList<>();
        Cursor cursor = null;
        try{
            cursor = bd.query(nomeTabela,colunas,null,null,null,null,colunas[0]);
            if(cursor.moveToFirst()){
                ProdutoVenda pv = new ProdutoVenda();

                pv.setId(cursor.getInt(0));
                pv.setCustoProduto(cursor.getDouble(1));
                pv.setVlrProduto(cursor.getDouble(2));
                pv.setQntdProduto(cursor.getInt(3));

                Produto produto = new Produto();
                ProdutoController pc = new ProdutoController(context);

                produto = pc.buscarProduto(cursor.getInt(4));
                pv.setProduto(produto);

                Venda venda;
                VendaController vc = new VendaController(context);

                venda = vc.buscarVenda(cursor.getInt(5));
                pv.setIdVenda(venda.getId());

                lista.add(pv);
            }while(cursor.moveToNext());
        }
        catch (Exception ex){
            Log.e("ERRO", "produtoVendaDao.getAll: " + ex.getMessage());
        }
        finally {
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return lista;
    }

    @Override
    public ProdutoVenda getById(int id) {
        Cursor cursor = null;
        try{
            String[]identificador = {String.valueOf(id)};
            cursor = bd.query(nomeTabela,colunas ,colunas + " = ?", identificador,null,null,null,null);

            if(cursor.moveToNext()){
                ProdutoVenda pv = new ProdutoVenda();

                pv.setIdVenda(cursor.getInt(0));
                pv.setVlrProduto(cursor.getDouble(2));
                pv.setCustoProduto(cursor.getDouble(3));
                pv.setQntdProduto(cursor.getInt(4));

                Produto produto = new Produto();
                ProdutoController pc = new ProdutoController(context);

                produto = pc.buscarProduto(cursor.getInt(4));
                pv.setProduto(produto);

                Venda venda = new Venda();
                VendaController vc = new VendaController(context);

                venda = vc.buscarVenda(cursor.getInt(5));
                pv.setIdVenda(venda.getId());

                return pv;
            }
        }
        catch (Exception ex){
            Log.e("ERRO", "produtoVendaDao.getById: " + ex.getMessage());
        }
        finally {
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return null;
    }

    public ArrayList<ProdutoVenda> buscarPorIdVenda(int id){
        Cursor cursor = null;
        ArrayList<ProdutoVenda> lista = new ArrayList<>();

        try {
            String[]identificador = {String.valueOf(id)};
            cursor = bd.query(nomeTabela, colunas, colunas[0] + " = ?",identificador,null,null,null,null);
            if (cursor.moveToFirst()){
                do{
                    ProdutoVenda pv = new ProdutoVenda();

                    pv.setIdVenda(cursor.getInt(0));
                    pv.setVlrProduto(cursor.getDouble(2));
                    pv.setCustoProduto(cursor.getDouble(3));
                    pv.setQntdProduto(cursor.getInt(4));

                    Produto produto = new Produto();
                    ProdutoController pc = new ProdutoController(context);

                    produto = pc.buscarProduto(cursor.getInt(4));
                    pv.setProduto(produto);

                    Venda venda = new Venda();
                    VendaController vc = new VendaController(context);

                    venda = vc.buscarVenda(cursor.getInt(5));
                    pv.setIdVenda(venda.getId());

                    lista.add(pv);
                }
                while (cursor.moveToNext());
            }
        }
        catch (Exception ex){
            Log.e("ERRO: ",ex.getMessage());
        }
        finally {
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return lista;
    }
}
