package model;

public enum TipoUsuarioEnum {
    ADMINISTRADOR(1, "Administrador"),
    COLABORADOR(2, "Colaborador");

    private int id;
    private String descricao;

    TipoUsuarioEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
