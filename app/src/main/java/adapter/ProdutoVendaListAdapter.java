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

public class ProdutoVendaListAdapter extends RecyclerView.Adapter<ProdutoVendaListAdapter.ProdutoVendaViewHolder> {


    private ArrayList<ProdutoVenda> listaProdutos;
    private Context context;

    public ProdutoVendaListAdapter(ArrayList<ProdutoVenda> listaProdutos, Context context) {
        this.listaProdutos = listaProdutos;
        this.context = context;
    }

    @NonNull
    @Override
    public ProdutoVendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listView = inflater.inflate(R.layout.item_list_produto_pedido, parent, false);

        return new ProdutoVendaViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoVendaViewHolder holder, int position) {
        ProdutoVenda produtoVenda = listaProdutos.get(position);
        holder.tvNmProduto.setText(produtoVenda.getProduto().getDsProduto());
        holder.tvQtdProduto.setText(String.valueOf(produtoVenda.getQntdProduto()));
        holder.tvVlrProduto.setText(String.valueOf(produtoVenda.getVlrProduto()));
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public class ProdutoVendaViewHolder extends RecyclerView.ViewHolder{

        public TextView tvNmProduto;
        public TextView tvQtdProduto;
        public TextView tvVlrProduto;

        public ProdutoVendaViewHolder(@NonNull View itemView){
            super(itemView);

            this.tvNmProduto = itemView.findViewById(R.id.tvNmProduto);
            this.tvQtdProduto = itemView.findViewById(R.id.tvQtdProduto);
            this.tvVlrProduto = itemView.findViewById(R.id.tvVlrProduto);
        }
    }
}


