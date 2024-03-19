package model;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String cpfcnpj;
    private String senha;
    private TipoUsuarioEnum tpUsuario;

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuarioEnum getTpUsuario() {
        return tpUsuario;
    }

    public void setTpUsuario(TipoUsuarioEnum tpUsuario) {
        this.tpUsuario = tpUsuario;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }
}


