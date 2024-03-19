package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.system_pos.R;

import java.util.ArrayList;

import model.Produto;
import model.ProdutoVenda;

public class DetalheVendaListAdapter extends RecyclerView.Adapter<DetalheVendaListAdapter.DetalheVendaViewHolder>{

    private ArrayList<ProdutoVenda> listaProdutos;
    private Context context;

    public DetalheVendaListAdapter(ArrayList<ProdutoVenda> listaProdutos, Context context) {
        this.listaProdutos = listaProdutos;
        this.context = context;
    }

    @NonNull
    @Override
    public DetalheVendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listView = inflater.inflate(R.layout.item_list_produtos_venda, parent, false);

        return new DetalheVendaViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetalheVendaViewHolder holder, int position) {
        ProdutoVenda produtoSelecionado = listaProdutos.get(position);
        holder.tvNmProduto.setText(produtoSelecionado.getProduto().getDsProduto());
        holder.tvQtdProduto.setText(String.valueOf(produtoSelecionado.getCustoProduto()));
        holder.tvVlrProduto.setText(String.valueOf(produtoSelecionado.getVlrProduto()));
    }

    public int getItemCount(){
        return listaProdutos.size();
    }

    public class DetalheVendaViewHolder extends RecyclerView.ViewHolder{
        public TextView tvNmProduto;
        public TextView tvQtdProduto;
        public TextView tvVlrProduto;

        public DetalheVendaViewHolder(@NonNull View itemView){
            super(itemView);

            this.tvNmProduto = itemView.findViewById(R.id.tvNmProduto);
            this.tvQtdProduto = itemView.findViewById(R.id.tvQtdProduto);
            this.tvVlrProduto = itemView.findViewById(R.id.tvVlrProduto);
        }
    }
}
