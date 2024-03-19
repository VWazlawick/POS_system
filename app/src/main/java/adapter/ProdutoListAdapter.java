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

public class ProdutoListAdapter extends
        RecyclerView.Adapter<ProdutoListAdapter.ProdutoViewHolder> {

    private ArrayList<Produto> listaProdutos;
    private Context context;

    public ProdutoListAdapter(ArrayList<Produto> listaProdutos, Context context) {
        this.listaProdutos = listaProdutos;
        this.context = context;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listView = inflater.inflate(R.layout.item_lista_produtos, parent, false);

        return new ProdutoViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produtoSelecionado = listaProdutos.get(position);
        holder.tvNmProduto.setText(produtoSelecionado.getDsProduto());
        holder.tvCustoProduto.setText(String.valueOf(produtoSelecionado.getCustoProduto()));
        holder.tvVlrProduto.setText(String.valueOf(produtoSelecionado.getVlrProduto()));
    }

    public int getItemCount(){
        return listaProdutos.size();
    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder{
        public TextView tvNmProduto;
        public TextView tvCustoProduto;
        public TextView tvVlrProduto;

        public ProdutoViewHolder(@NonNull View itemView){
            super(itemView);

            this.tvNmProduto = itemView.findViewById(R.id.tvNmProduto);
            this.tvCustoProduto = itemView.findViewById(R.id.tvCustoProduto);
            this.tvVlrProduto = itemView.findViewById(R.id.tvVlrProduto);
        }
    }
}
