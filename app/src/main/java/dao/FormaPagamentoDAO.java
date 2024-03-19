package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import helper.SQLiteDataHelper;
import model.FormaPagamento;

public class FormaPagamentoDAO implements GenericDAO<FormaPagamento> {

    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;
    private String nomeTabela = "FORMAPAGAMENTO";

    private String[]colunas = {"id","descricao"};

    private Context context;

    private static FormaPagamentoDAO instancia;

    public static FormaPagamentoDAO getInstance(Context context){
        if(instancia == null){
            return instancia = new FormaPagamentoDAO(context);
        }else{
            return instancia;
        }
    }

    private FormaPagamentoDAO(Context context){
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "Koffee", null, 1);

        bd = openHelper.getWritableDatabase();

    }


    @Override
    public long insert(FormaPagamento obj) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(colunas[0], obj.getId());
            cv.put(colunas[1], obj.getDescricao());

            return bd.insert(nomeTabela, null, cv);
        }
        catch (Exception ex){
            Log.e("Error: ", " FormaPagamentoDAO.insert() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(FormaPagamento obj) {
        try {
            ContentValues cv = new ContentValues();

            cv.put(colunas[1], obj.getDescricao());

            String[] identificador = {String.valueOf(obj.getId())};
            return bd.update(nomeTabela, cv, colunas[0] + " = ?",identificador);
        }
        catch (Exception ex){
            Log.e("Error: ", " FormaPagamentoDAO.update() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(FormaPagamento obj) {
        try{
            String[] identificador = {String.valueOf(obj.getId())};
            return bd.delete(nomeTabela, colunas[0] + " = ?", identificador);
        }
        catch (Exception ex){
            Log.e("Error: ", " FormaPagamentoDAO.delete() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<FormaPagamento> getAll() {
        ArrayList<FormaPagamento> lista = new ArrayList<>();
        Cursor cursor = null;
        try{
            cursor = bd.query(nomeTabela, colunas,null,null,null,null,colunas[0]);
            if(cursor.moveToFirst()){
                do{
                    FormaPagamento formaPagamento = new FormaPagamento();

                    formaPagamento.setId(cursor.getInt(0));
                    formaPagamento.setDescricao(cursor.getString(1));

                    lista.add(formaPagamento);
                }
                while (cursor.moveToNext());
            }

        }
        catch (Exception ex){
            Log.e("Error: ", " FormaPagamentoDAO.getAll() " + ex.getMessage());
        }
        finally {
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return lista;
    }

    @Override
    public FormaPagamento getById(int id) {
        Cursor cursor = null;
        try{
            String[]identificador = {String.valueOf(id)};
            cursor = bd.query(nomeTabela, colunas, colunas[0] + " = ?", identificador, null,null,null,null);

            if(cursor.moveToFirst()){
                do {
                    FormaPagamento formaPagamento = new FormaPagamento();

                    formaPagamento.setId(cursor.getInt(0));
                    formaPagamento.setDescricao(cursor.getString(1));

                    return formaPagamento;
                }
                while (cursor.moveToNext());
            }

        }
        catch (Exception ex){
            Log.e("Error: ", " FormaPagamentoDAO.getById() " + ex.getMessage());
        }finally {
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return null;
    }
}
