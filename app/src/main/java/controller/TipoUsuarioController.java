package controller;

import android.content.Context;

import java.util.ArrayList;

import dao.ProdutoDAO;
import dao.TipoUsuarioDAO;
import model.Produto;
import model.TipoUsuario;
import model.TipoUsuarioEnum;

public class TipoUsuarioController {

    private Context context;

    public TipoUsuarioController(Context context){
        this.context = context;
    }

    public TipoUsuarioEnum buscarTpUsuario(int id){
        try {
            return TipoUsuarioDAO.getInstance(context).getById(id);
        }
        catch (Exception ex){
        }
        return null;
    }

    }

