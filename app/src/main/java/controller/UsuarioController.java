package controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.Locale;

import dao.UsuarioDAO;
import model.TipoUsuario;
import model.TipoUsuarioEnum;
import model.Usuario;

public class UsuarioController {

    private Context context;

    public UsuarioController(Context context){
        this.context = context;
    }

    public String salvarUsuario(String nome, String email, String cpfcnpj, String senha, String senhaConfirma, TipoUsuarioEnum tpUsuario){
        try {
            if(nome.isEmpty() || nome==""){
                return "Informe o Nome do Usuário!";
            }
            if(email.isEmpty() || email=="" || !email.contains("@")){
                return "Informe um Email válido!";
            }
            if(cpfcnpj.isEmpty() || cpfcnpj=="" || cpfcnpj.length()<11){
                return "Informe Cpf/Cnpj válido";
            }
            if(senha.isEmpty() || senha=="" || senha.length()<8){
                return "Informe uma Senha válida!\n(Minimo 8 digitos)";
            }
            if(tpUsuario==null){
                return "Informe o Tipo de Usuário";
            }
            if(!senha.equals(senhaConfirma)){
                return "Senhas não coincidem";
            }

            for(int i=0; i<UsuarioDAO.getInstance(context).getAll().size();i++){
                if(email.toLowerCase(Locale.ROOT)==UsuarioDAO.getInstance(context).getAll().get(i).getEmail()){
                    return "Email já cadastrado";
                }
            }

            Usuario usuario = new Usuario();

            ArrayList<Usuario> lista = UsuarioDAO.getInstance(context).getAll();
            if(lista==null){
                usuario.setId(1);
            }else{
                usuario.setId(lista.size()+1);
            }
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setCpfcnpj(cpfcnpj);
            usuario.setSenha(senha);
            usuario.setTpUsuario(tpUsuario);


            UsuarioDAO.getInstance(context).insert(usuario);
        }
        catch(Exception exception){
            return "Erro ao gravar Aluno" + exception.getMessage();
        }
        return null;
    }

    public String atualizarUsuario(Usuario usuario){
        try {
            if(usuario.getNome().isEmpty() || usuario.getNome()==""){
                return "Informe o Nome do Usuário!";
            }
            if(usuario.getEmail().isEmpty() || usuario.getEmail()=="" || !usuario.getEmail().contains("@")){
                return "Informe um Email válido!";
            }
            if(usuario.getCpfcnpj().isEmpty() || usuario.getCpfcnpj()=="" || usuario.getCpfcnpj().length()<11){
                return "Informe Cpf/Cnpj válido";
            }
            if(usuario.getSenha().isEmpty() || usuario.getSenha()=="" || usuario.getSenha().length()<8){
                return "Informe uma Senha válida!";
            }

            UsuarioDAO.getInstance(context).update(usuario);
        }
        catch (Exception ex){
            return "Erro ao atualizar cadastro";
        }
        return null;
    }

    public String excluirUsuario(Usuario usuario){
        try{
            UsuarioDAO.getInstance(context).delete(usuario);
        }
        catch (Exception ex){
            return "Erro ao excluir";
        }
        return null;
    }

    public Usuario buscarUsuario(int id){
        try {
            return UsuarioDAO.getInstance(context).getById(id);
        }
        catch (Exception ex){
        }
        return null;
    }

    public ArrayList<Usuario> buscarTodosUsuarios(){
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            lista = UsuarioDAO.getInstance(context).getAll();
        }
        catch (Exception ex){

        }
        return lista;
    }
}
