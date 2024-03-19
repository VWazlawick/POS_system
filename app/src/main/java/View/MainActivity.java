package View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import com.example.system_pos.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

import adapter.ProdutoVendaListAdapter;
import controller.ProdutoController;
import controller.ProdutoVendaController;
import model.Produto;
import model.ProdutoVenda;
import model.Venda;


public class MainActivity extends AppCompatActivity {

    private ImageButton btMenu;
    private AutoCompleteTextView actvBuscaProduto;
    private ImageButton btAddProduto;
    private EditText edQuantProduto;
    private TextView tvQuantidadeItens;
    private TextView tvValorTotal;
    private LinearLayout linearLayout3;
    private ProdutoController controller;
    private int posicaoSelecionada = -1;
    private ArrayList<String> lista;
    private ArrayList<Produto> listaProdutoSelecionados;
    private ArrayList<Produto> todosProdutosSelecionado;
    private ArrayList<ProdutoVenda> produtoVenda = new ArrayList<>();
    private RecyclerView rvProdutosVenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btMenu = findViewById(R.id.btMenu);
        actvBuscaProduto = findViewById(R.id.actvBuscaProduto);
        btAddProduto = findViewById(R.id.btAddProduto);
        edQuantProduto = findViewById(R.id.edQuantProduto);
        rvProdutosVenda = findViewById(R.id.rvProdutosVenda);
        tvQuantidadeItens = findViewById(R.id.tvQuantidadeItens);
        tvValorTotal = findViewById(R.id.tvValorTotal);
        linearLayout3 = findViewById(R.id.linearLayout3);

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu(v);
            }
        });

        btAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarProduto();
            }
        });

        actvBuscaProduto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                carregarSugestoes();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        actvBuscaProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicaoSelecionada = position;
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaDetalheVenda();
            }
        });
    }



    private void adicionarProduto() {
        if(actvBuscaProduto.getText().toString().isEmpty() || actvBuscaProduto.getText().toString()==""){
            actvBuscaProduto.setError("Informe o produto!");
            actvBuscaProduto.requestFocus();
        }
        else if(edQuantProduto.getText().toString().isEmpty() || edQuantProduto.getText().toString()==""){
            actvBuscaProduto.setError("Informe a quantidade");
            actvBuscaProduto.requestFocus();
        }
        else{
            Produto produto;
            produto = listaProdutoSelecionados.get(posicaoSelecionada);

            ProdutoVenda pv = new ProdutoVenda();
            pv.setProduto(produto);
            pv.setQntdProduto(Integer.parseInt(edQuantProduto.getText().toString()));

            produtoVenda.add(pv);

            edQuantProduto.setText("");
            actvBuscaProduto.setText("");

            carregarQtdVlr();

            carregaListaProdutoVenda();
        }
    }

    private void carregarQtdVlr() {
        int qtdTotal = 0;
        double vlrTotal = 0;

        for(int i =0;i<produtoVenda.size();i++){
            qtdTotal += produtoVenda.get(i).getQntdProduto();
            vlrTotal += produtoVenda.get(i).getVlrProduto()*produtoVenda.get(i).getQntdProduto();
        }

        tvValorTotal.setText("Total: R$" + vlrTotal);
        if(qtdTotal==1){
            tvQuantidadeItens.setText(qtdTotal + " item");
        }else{
            tvQuantidadeItens.setText(qtdTotal + " itens");
        }
    }

    private void carregaListaProdutoVenda() {
        ProdutoVendaListAdapter adapter = new ProdutoVendaListAdapter(produtoVenda, this);
        rvProdutosVenda.setLayoutManager(new LinearLayoutManager(this));
        rvProdutosVenda.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void carregarSugestoes() {
        lista = new ArrayList<>();
        controller = new ProdutoController(this);
        listaProdutoSelecionados = new ArrayList<>();

        todosProdutosSelecionado = controller.buscarTodosProdutos();

        for(int i = 0; i<todosProdutosSelecionado.size();i++){
            if(todosProdutosSelecionado.get(i).getDsProduto().toLowerCase().contains(actvBuscaProduto.getText().toString().toLowerCase())){
                lista.add(todosProdutosSelecionado.get(i).getDsProduto());
                listaProdutoSelecionados.add(todosProdutosSelecionado.get(i));
            }
        }
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, lista);
        actvBuscaProduto.setAdapter(adapter);
    }

    private void showPopUpMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view, Gravity.END,R.style.PopUpMenuStyle, 0);

        popupMenu.getMenuInflater().inflate(R.menu.menu_principal, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.opAtendente){
                    popupMenu.dismiss();
                    return true;
                }
                else if(item.getItemId() == R.id.opHistCompra){
                    return true;
                }
                else if(item.getItemId() == R.id.opApontarErro){
                    return true;
                }
                else if(item.getItemId() == R.id.opAdministracao){
                    abrirTelaAdministracao();
                    return true;
                }
                else if(item.getItemId() == R.id.opConta){
                    return true;
                }
                else if(item.getItemId() == R.id.opSuporte){
                    return true;
                }else{
                    return false;
                }
            }
        });
        popupMenu.show();
    }

    private void abrirTelaAdministracao() {
        Intent intent = new Intent(MainActivity.this, AdministracaoActivity.class);

        startActivity(intent);
    }

    private void abrirTelaDetalheVenda() {
        if(produtoVenda.size()==0){
            Toast.makeText(MainActivity.this,"Informe algum produto para Continuar!",
                    Toast.LENGTH_LONG).show();
        }else {
            Intent intent = new Intent(MainActivity.this, DetalheVendaActivity.class);
            intent.putExtra("lista_produto", produtoVenda);

            startActivity(intent);
        }
    }

}