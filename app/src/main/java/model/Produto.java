package model;

import java.io.Serializable;

public class Produto implements Serializable {
    private int id;
    private String dsProduto;
    private double vlrProduto;
    private int qtdProduto;
    private double custoProduto;

    public Produto() {
    }

    public Produto(int id, String dsProduto, double vlrProduto, int qtdProduto, double custoProduto) {
        this.id = id;
        this.dsProduto = dsProduto;
        this.vlrProduto = vlrProduto;
        this.qtdProduto = qtdProduto;
        this.custoProduto = custoProduto;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDsProduto() {
        return dsProduto;
    }

    public void setDsProduto(String dsProduto) {
        this.dsProduto = dsProduto;
    }

    public double getVlrProduto() {
        return vlrProduto;
    }

    public void setVlrProduto(double vlrProduto) {
        this.vlrProduto = vlrProduto;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public double getCustoProduto() {
        return custoProduto;
    }

    public void setCustoProduto(double custoProduto) {
        this.custoProduto = custoProduto;
    }
}
