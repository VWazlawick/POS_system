package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import model.TipoUsuarioEnum;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE produto(id INTEGER PRIMARY KEY, dsproduto TEXT NOT NULL," +
                "vlrproduto REAL NOT NULL," +" qtdproduto INTEGER , custoproduto REAL NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE tipousuario(id INTEGER PRIMARY KEY, descricao TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE usuario(id INTEGER PRIMARY KEY, nome TEXT NOT NULL, email TEXT NOT NULL, " +
                "cpfcnpj INTEGER, senha TEXT NOT NULL, idtpusuario INTEGER REFERENCES tipousuario(id))");

        sqLiteDatabase.execSQL("CREATE TABLE produtoVenda(id INTEGER PRIMARY KEY, custoproduto REAL, vlrproduto REAL, " +
                "qtdproduto REAL, idproduto INTEGER REFERENCES produto(id), idvenda INTEGER REFERENCES venda(id))");

        sqLiteDatabase.execSQL("CREATE TABLE venda(id INTEGER PRIMARY KEY AUTOINCREMENT, vlrtotal REAL, qtdTotal INTEGER, dtVenda TEXT," +
                "idcliente INTEGER REFERENCES cliente(id), idformapagamento INTEGER REFERENCES formapagamento(id))");

        sqLiteDatabase.execSQL("CREATE TABLE cliente(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, nrtelefone TEXT, email TEXT," +
                "endereco TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE formapagamento(id INTEGER PRIMARY KEY, descricao TEXT)");

        for (TipoUsuarioEnum tipo : TipoUsuarioEnum.values()) {
            ContentValues values = new ContentValues();
            values.put("id", tipo.getId());
            values.put("descricao", tipo.getDescricao());
            sqLiteDatabase.insert("tipousuario", null, values);
        }
        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("nome", "qweq");
        values.put("email", "teste");
        values.put("senha", "123");
        values.put("idtpusuario", 1);
        sqLiteDatabase.insert("usuario",null, values);

        ContentValues valuesCliente = new ContentValues();
        valuesCliente.put("id",1);
        valuesCliente.put("nome", "Victor");
        valuesCliente.put("nrtelefone","45998618406");
        sqLiteDatabase.insert("cliente",null, valuesCliente);

        ContentValues valuesFp = new ContentValues();
        valuesFp.put("id",1);
        valuesFp.put("descricao","Dinheiro");
        sqLiteDatabase.insert("formapagamento", null,valuesFp);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
