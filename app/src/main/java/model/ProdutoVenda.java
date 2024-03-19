package model;

import java.io.Serializable;

public class ProdutoVenda implements Serializable{
    private int id;
    private Produto produto;
    private double custoProduto;
    private double vlrProduto;
    private int qntdProduto;
    private int idVenda;

    public ProdutoVenda() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        setVlrProduto(produto.getVlrProduto());
        setCustoProduto(produto.getCustoProduto());
    }

    public double getCustoProduto() {
        return custoProduto;
    }

    public void setCustoProduto(double custoProduto) {
        this.custoProduto = custoProduto;
    }

    public double getVlrProduto() {
        return vlrProduto;
    }

    public void setVlrProduto(double vlrProduto) {
        this.vlrProduto = vlrProduto;
    }

    public int getQntdProduto() {
        return qntdProduto;
    }

    public void setQntdProduto(int qntdProduto) {
        this.qntdProduto = qntdProduto;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

}
