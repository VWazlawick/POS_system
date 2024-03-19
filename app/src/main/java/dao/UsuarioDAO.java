package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

import controller.TipoUsuarioController;
import helper.SQLiteDataHelper;
import model.TipoUsuario;
import model.TipoUsuarioEnum;
import model.Usuario;

public class UsuarioDAO implements GenericDAO<Usuario> {

    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;
    private String nomeTabela = "USUARIO";

    private String[]colunas = {"id","nome","email","cpfcnpj","senha","idtpusuario"};

    private Context context;

    private static UsuarioDAO instancia;

    public static UsuarioDAO getInstance(Context context){
        if(instancia == null){
            return instancia = new UsuarioDAO(context);
        }else{
            return instancia;
        }
    }

    private UsuarioDAO(Context context){
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "Koffee", null, 1);


        bd = openHelper.getWritableDatabase();

    }

    @Override
    public long insert(Usuario obj) {
        try {
            ContentValues cv = new ContentValues();

            cv.put(colunas[0], obj.getId());
            cv.put(colunas[1], obj.getNome());
            cv.put(colunas[2], obj.getEmail());
            cv.put(colunas[3], obj.getCpfcnpj());
            cv.put(colunas[4], obj.getSenha());
            cv.put(colunas[5], obj.getTpUsuario().getId());

            return bd.insert(nomeTabela,null, cv);
        }
        catch (Exception ex){
            Log.e("Erro", "UsuarioDao.insert(): " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Usuario obj) {
        try {
            ContentValues cv = new ContentValues();

            cv.put(colunas[1], obj.getNome());
            cv.put(colunas[2], obj.getEmail());
            cv.put(colunas[3], obj.getCpfcnpj());
            cv.put(colunas[4], obj.getSenha());

            String[]identificador = {String.valueOf(obj.getId())};

            return bd.update(nomeTabela, cv, colunas[0] + " = ?",identificador);
        }
        catch (Exception ex){
            Log.e("ERRO", "UsuarioDao.update(): " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Usuario obj) {
        try{
            String[]identificador = {String.valueOf(obj.getId())};
            return bd.delete(nomeTabela, colunas[0] + " = ?",identificador);
        }
        catch (Exception ex){
            Log.e("ERRO", "UsuarioDao.delete(): " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Usuario> getAll() {
        ArrayList<Usuario>lista = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = bd.query(nomeTabela, colunas,null, null, null, null, colunas[0]);
            if(cursor.moveToFirst()){
                do{
                    Usuario usuario = new Usuario();

                    usuario.setId(cursor.getInt(0));
                    usuario.setNome(cursor.getString(1));
                    usuario.setEmail(cursor.getString(2));
                    usuario.setCpfcnpj(cursor.getString(3));
                    usuario.setSenha(cursor.getString(4));

                    TipoUsuarioEnum tp;
                    TipoUsuarioController tpc = new TipoUsuarioController(context);

                    tp = tpc.buscarTpUsuario(cursor.getInt(5));

                    usuario.setTpUsuario(tp);

                    lista.add(usuario);
                }while(cursor.moveToNext());
            }

        }
        catch (Exception ex){
            Log.e("ERRO", "UsuarioDao.getAll(): " +  ex.getMessage());
        }
        finally {
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return lista;
    }

    @Override
    public Usuario getById(int id) {
        Cursor cursor = null;
        try {
            String[]identificador = {String.valueOf(id)};
            cursor = bd.query(nomeTabela, colunas, colunas[0]+ " = ?", identificador, null,null,null,null);

            if(cursor.moveToFirst()){
                Usuario usuario = new Usuario();

                usuario.setId(cursor.getInt(0));
                usuario.setNome(cursor.getString(1));
                usuario.setEmail(cursor.getString(2));
                usuario.setCpfcnpj(cursor.getString(3));
                usuario.setSenha(cursor.getString(4));

                TipoUsuarioEnum tp;
                TipoUsuarioController tpc = new TipoUsuarioController(context);

                tp = tpc.buscarTpUsuario(cursor.getInt(5));

                usuario.setTpUsuario(tp);

                return usuario;
            }
        }
        catch (Exception ex){
            Log.e("ERRO", "UsuarioDao.getById(): " + ex.getMessage());
        }
        finally {
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return null;
    }


}
