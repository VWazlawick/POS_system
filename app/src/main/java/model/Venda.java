package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Venda implements Serializable {
    private int id;
    private double vlrTotal;
    private int qtdTotal;
    private ArrayList<ProdutoVenda> listaProdutos;
    private String dtVenda;
    private  Cliente cliente;
    private FormaPagamento formaPagamento;

    public Venda() {
    }


    public Venda(int id, double vlrTotal, int qtdTotal, ArrayList<ProdutoVenda> listaProdutos, String dtVenda, Cliente cliente, FormaPagamento formaPagamento) {
        this.id = id;
        this.vlrTotal = vlrTotal;
        this.qtdTotal = qtdTotal;
        this.listaProdutos = listaProdutos;
        this.dtVenda = dtVenda;
        this.cliente = cliente;
        this.formaPagamento = formaPagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public int getQtdTotal() {
        return qtdTotal;
    }

    public void setQtdTotal(int qtdTotal) {
        this.qtdTotal = qtdTotal;
    }

    public ArrayList<ProdutoVenda> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ArrayList<ProdutoVenda> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public String getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(String dtVenda) {
        this.dtVenda = dtVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
