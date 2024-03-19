package View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.system_pos.R;

import java.util.ArrayList;

import adapter.ProdutoListAdapter;
import controller.ProdutoController;
import model.Produto;

public class ListaProdutosActivity extends AppCompatActivity {

    private ImageButton btVoltarTelaIncial;

    private Button btCadastrarNovoProduto;
    private ImageButton btBuscarProduto;
    private ProdutoController controller;
    private RecyclerView rvProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        btVoltarTelaIncial = findViewById(R.id.btVoltarTelaInicial);
        btCadastrarNovoProduto = findViewById(R.id.btCadastrarNovoProduto);
        btBuscarProduto = findViewById(R.id.btBuscarProduto);
        rvProdutos = findViewById(R.id.rvProdutos);

        btVoltarTelaIncial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btCadastrarNovoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCadastroProduto();
            }
        });

        btBuscarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarListaProdutos();
            }
        });
    }

    private void carregarListaProdutos() {
        controller = new ProdutoController(this);
        ArrayList<Produto> lista = controller.buscarTodosProdutos();
        ProdutoListAdapter adapter = new ProdutoListAdapter(lista, this);
        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
        rvProdutos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void abrirCadastroProduto() {
        Intent intent = new Intent(ListaProdutosActivity.this, CadastroProdutoActivity.class);

        startActivity(intent);
    }
}