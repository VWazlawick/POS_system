package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import helper.SQLiteDataHelper;
import model.Cliente;
import model.Produto;

public class ClienteDAO implements GenericDAO<Cliente> {

    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;
    private String nomeTabela = "CLIENTE";

    private String[]colunas = {"id","nome","nrtelefone","email","endereco"};

    private Context context;

    private static ClienteDAO instancia;

    public static ClienteDAO getInstance(Context context){
        if(instancia == null){
            return instancia = new ClienteDAO(context);
        }else{
            return instancia;
        }
    }

    private ClienteDAO(Context context){
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "Koffee", null, 1);

        bd = openHelper.getWritableDatabase();

    }



    @Override
    public long insert(Cliente obj) {
        try{
            ContentValues cv = new ContentValues();

            cv.put(colunas[1], obj.getNome());
            cv.put(colunas[2], obj.getNrTelefone());
            cv.put(colunas[3], obj.getEmail());
            cv.put(colunas[3], obj.getEndereco());

            return bd.insert(nomeTabela, null, cv);
        }
        catch (Exception ex){
            Log.e("Erro", "ClienteDao.insert(): " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public long update(Cliente obj) {
        try{
            ContentValues cv = new ContentValues();

            cv.put(colunas[1], obj.getNome());
            cv.put(colunas[2], obj.getNrTelefone());
            cv.put(colunas[3], obj.getEmail());
            cv.put(colunas[3], obj.getEndereco());

            String[]identificador = {String.valueOf(obj.getId())};

            return bd.update(nomeTabela, cv, colunas[0] + " = ?", identificador);
        }
        catch (Exception ex){
            Log.e("Erro", "ClienteDao.update(): " + ex.getMessage());
        }

        return 0;
    }

    @Override
    public long delete(Cliente obj) {
        try{
            String[]identificador = {String.valueOf(obj.getId())};

            return bd.delete(nomeTabela, colunas[0] + " = ?", identificador);
        }
        catch(Exception ex){
            Log.e("Erro", "ClienteDao.delete(): " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Cliente> getAll() {
        ArrayList<Cliente> lista = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = bd.query(nomeTabela, colunas, null, null, null, null, colunas[0]);

            if (cursor.moveToFirst()) {
                do {
                    Cliente cliente = new Cliente();

                    cliente.setId(cursor.getInt(0));
                    cliente.setNome(cursor.getString(1));
                    cliente.setNrTelefone(cursor.getString(2));
                    cliente.setEmail(cursor.getString(3));
                    cliente.setEndereco(cursor.getString(4));

                    lista.add(cliente);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            Log.e("Erro", "ClienteDao.getAll(): " + ex.getMessage());
        }
        finally {
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return lista;
    }

    @Override
    public Cliente getById(int id) {
        Cursor cursor = null;
        try{
            String[]identificador = {String.valueOf(id)};

            cursor = bd.query(nomeTabela,colunas, colunas[0] + " = ?", identificador, null,null,null,null);
            if(cursor.moveToFirst()){
                Cliente cliente = new Cliente();

                cliente.setNome(cursor.getString(1));
                cliente.setNrTelefone(cursor.getString(2));
                cliente.setEmail(cursor.getString(3));
                cliente.setEndereco(cursor.getString(4));

                return cliente;
            }while (cursor.moveToNext());
        }
        catch (Exception ex){
            Log.e("Erro", "ClienteDao.getById(): " + ex.getMessage());
        }
        finally {
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return null;
    }
}
