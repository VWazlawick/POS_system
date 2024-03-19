package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import helper.SQLiteDataHelper;
import model.TipoUsuario;
import model.TipoUsuarioEnum;

public class TipoUsuarioDAO implements GenericDAO{

    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase bd;
    private String nomeTabela = "TIPOUSUARIO";

    private String[]colunas = {"id","descricao"};

    private Context context;

    private static TipoUsuarioDAO instancia;

    public static TipoUsuarioDAO getInstance(Context context){
        if(instancia == null){
            return instancia = new TipoUsuarioDAO(context);
        }else{
            return instancia;
        }
    }

    private TipoUsuarioDAO(Context context){
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "Koffee", null, 1);

        bd = openHelper.getWritableDatabase();

    }



    @Override
    public long insert(Object obj) {
        return 0;
    }

    @Override
    public long update(Object obj) {
        return 0;
    }

    @Override
    public long delete(Object obj) {
        return 0;
    }

    @Override
    public ArrayList<TipoUsuarioEnum> getAll() {
        return null;
    }

    @Override
    public TipoUsuarioEnum getById(int id) {
        Cursor cursor = null;
        try {
            String[]identificador = {String.valueOf(id)};
            cursor = bd.query(nomeTabela, colunas, colunas[0]+ " = ?", identificador, null,null,null,null);

            if(cursor.moveToFirst()){
                for(TipoUsuarioEnum tp : TipoUsuarioEnum.values()){
                    if(tp.getId() == id){

                        return tp;
                    }
                    return null;
                }
                return null;

            }
        }
        catch (Exception ex){
            Log.e("ERRO", "TipoUsuarioDao.getById(): " + ex.getMessage());
        }
        finally {
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return null;
    }
}
