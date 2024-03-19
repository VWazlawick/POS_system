package model;

import java.io.Serializable;

public class Cliente implements Serializable {
    private int id;
    private String nome;
    private String nrTelefone;
    private String email;
    private String endereco;

    public Cliente() {
    }

    public Cliente(int id, String nome, String nrTelefone, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.nrTelefone = nrTelefone;
        this.email = email;
        this.endereco = endereco;
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

    public String getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
