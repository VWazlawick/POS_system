package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

import model.Produto;
import helper.SQLiteDataHelper;

public class ProdutoDAO implements GenericDAO<Produto> {

    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;
    private String nomeTabela = "PRODUTO";

    private String[]colunas = {"id","dsproduto","vlrproduto","qtdproduto","custoproduto"};

    private Context context;

    private static ProdutoDAO instancia;

    public static ProdutoDAO getInstance(Context context){
        if(instancia == null){
            return instancia = new ProdutoDAO(context);
        }else{
            return instancia;
        }
    }

    private ProdutoDAO(Context context){
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "Koffee", null, 1);

        bd = openHelper.getWritableDatabase();

    }

    @Override
    public long insert(Produto obj) {
        try {
            ContentValues cv = new ContentValues();

            cv.put(colunas[0], obj.getId());
            cv.put(colunas[1], obj.getDsProduto());
            cv.put(colunas[2], obj.getVlrProduto());
            cv.put(colunas[3], obj.getQtdProduto());
            cv.put(colunas[4], obj.getCustoProduto());

            return bd.insert(nomeTabela,null, cv);
        }
        catch (Exception ex){
            Log.e("Erro", "ProdutoDao.insert(): " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Produto obj) {
        try {
            ContentValues cv = new ContentValues();

            cv.put(colunas[1], obj.getDsProduto());
            cv.put(colunas[2], obj.getVlrProduto());
            cv.put(colunas[4], obj.getCustoProduto());

            String[]identificador = {String.valueOf(obj.getId())};

            return bd.update(nomeTabela, cv, colunas[0] + " = ?",identificador);
        }
        catch (Exception ex){
            Log.e("ERRO", "ProdutoDao.update(): " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Produto obj) {
        try{
            String[]identificador = {String.valueOf(obj.getId())};
            return bd.delete(nomeTabela, colunas[0] + " = ?",identificador);
        }
        catch (Exception ex){
            Log.e("ERRO", "ProdutoDao.delete(): " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Produto> getAll() {
        ArrayList<Produto>lista = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = bd.query(nomeTabela, colunas,null, null, null, null, colunas[0]);
            if(cursor.moveToFirst()){
                do{
                    Produto produto = new Produto();

                    produto.setId(cursor.getInt(0));
                    produto.setDsProduto(cursor.getString(1));
                    produto.setVlrProduto(cursor.getDouble(2));
                    produto.setQtdProduto(cursor.getInt(3));
                    produto.setCustoProduto(cursor.getDouble(4));

                    lista.add(produto);
                }while(cursor.moveToNext());
            }

        }
        catch (Exception ex){
            Log.e("ERRO", "ProdutoDao.getAll(): " +  ex.getMessage());
        }
        finally {
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return lista;
    }

    @Override
    public Produto getById(int id) {
        Cursor cursor = null;
        try {
            String[]identificador = {String.valueOf(id)};
            cursor = bd.query(nomeTabela, colunas, colunas[0]+ " = ?", identificador, null,null,null,null);

            if(cursor.moveToFirst()){
                Produto produto = new Produto();
                produto.setId(cursor.getInt(0));
                produto.setDsProduto(cursor.getString(1));
                produto.setVlrProduto(cursor.getDouble(2));
                produto.setQtdProduto(cursor.getInt(3));
                produto.setCustoProduto(cursor.getDouble(4));

                if(!cursor.isClosed() || cursor!=null){
                    cursor.close();
                }

                return produto;
            }
        }
        catch (Exception ex){
            Log.e("ERRO", "ProdutoDao.getById(): " + ex.getMessage());
        }
        finally {
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return null;
    }
}
