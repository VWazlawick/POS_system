package View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.system_pos.R;

import java.util.ArrayList;

import adapter.DetalheVendaListAdapter;
import controller.ClienteController;
import model.Cliente;
import model.ProdutoVenda;
import model.Venda;

public class DetalheVendaActivity extends AppCompatActivity {

    private ArrayList<ProdutoVenda> lista;
    private Intent intent;
    private RecyclerView rvProdutoDetalhes;
    private TextView tvValorTotalVenda;
    private AutoCompleteTextView actvCliente;
    private ImageButton btvoltar;
    private Button btFechaVenda;
    private Button btAvista;
    private Button btAprazo;
    private LinearLayout lnCancelarVenda;
    private ArrayList<String> listaSugestoes;
    private ArrayList<Cliente> listaCliente;
    private ArrayList<Cliente> listaClienteSugerido;
    private ClienteController controller;
    private int posicaoSelecionada;
    private AlertDialog createDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_venda);

        rvProdutoDetalhes = findViewById(R.id.rvProdutoDetalhes);
        tvValorTotalVenda = findViewById(R.id.tvValorTotalVenda);
        btvoltar = findViewById(R.id.btVoltar);
        actvCliente = findViewById(R.id.actvCliente);
        btFechaVenda = findViewById(R.id.btFechaVenda);
        lnCancelarVenda = findViewById(R.id.lnCancelarVenda);
        intent = getIntent();

        carregarProdutos();
        atualizarSubTotal();

        btvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        actvCliente.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>= (actvCliente.getRight()-actvCliente.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())){
                        cadastrarCliente();
                        return true;
                    }
                }
                return false;
            }
        });

        actvCliente.addTextChangedListener(new TextWatcher() {
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

        actvCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicaoSelecionada = position;
            }
        });

        btFechaVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDialogFormaPagamento();
            }
        });

        lnCancelarVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarVenda();
            }
        });
    }

    private void cancelarVenda() {
        Intent intent1 = new Intent(DetalheVendaActivity.this, MainActivity.class);

        startActivity(intent1);
    }

    private void abrirDialogFormaPagamento() {
        View viewALert = getLayoutInflater().inflate(R.layout.dialog_forma_pagament, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(viewALert);
        builder.setCancelable(true);

        btAvista = viewALert.findViewById(R.id.btAvista);
        btAprazo = viewALert.findViewById(R.id.btAprazo);

        createDialog = builder.create();

        createDialog.setOnShowListener(dialog -> {
            Window window = createDialog.getWindow();

            if(window!=null){
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

                layoutParams.copyFrom(window.getAttributes());
                layoutParams.gravity = Gravity.BOTTOM;
                window.setAttributes(layoutParams);
            }
        });

        createDialog.show();


        btAvista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog.dismiss();
                abrirFinalizarVenda();
            }
        });

    }

    private void abrirFinalizarVenda() {
        Intent intent = new Intent(DetalheVendaActivity.this, FinalizarVendaAvistaActivity.class);
        Venda venda = new Venda();
        double vlrTotal=0;
        for(int i = 0; i<lista.size();i++){
            vlrTotal += lista.get(i).getVlrProduto()*lista.get(i).getQntdProduto();
        }
        int qtdTotal=0;
        for(int i = 0; i<lista.size();i++){
            qtdTotal += lista.get(i).getQntdProduto();
        }

        venda.setVlrTotal(vlrTotal);
        venda.setQtdTotal(qtdTotal);
        venda.setListaProdutos(lista);
        venda.setCliente(listaClienteSugerido.get(posicaoSelecionada));

        intent.putExtra("venda", venda);

        startActivity(intent);
    }

    private void carregarSugestoes() {
        listaSugestoes = new ArrayList<>();
        controller = new ClienteController(this);
        listaClienteSugerido = new ArrayList<>();

        listaCliente = controller.buscarTodosCliente();

        for(int i =0; i<listaCliente.size();i++){
            if(listaCliente.get(i).getNome().toLowerCase().contains(actvCliente.getText().toString().toLowerCase())){
                listaSugestoes.add(listaCliente.get(i).getNome());
                listaClienteSugerido.add(listaCliente.get(i));
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(DetalheVendaActivity.this,
                android.R.layout.simple_dropdown_item_1line, listaSugestoes);
        actvCliente.setAdapter(adapter);
    }

    private void cadastrarCliente() {
        Intent intent1 = new Intent(DetalheVendaActivity.this,CadastroClienteActivity.class);

        startActivity(intent1);
    }

    private void atualizarSubTotal() {
        double vlrTotal = 0;

        for(int i=0; i<lista.size();i++){
            vlrTotal += lista.get(i).getVlrProduto()*lista.get(i).getQntdProduto();
        }
        tvValorTotalVenda.setText("R$" + vlrTotal);
    }

    private void carregarProdutos() {
        lista = (ArrayList<ProdutoVenda>) intent.getSerializableExtra("lista_produto");
;       DetalheVendaListAdapter adapter = new DetalheVendaListAdapter(lista,this);
        rvProdutoDetalhes.setLayoutManager(new LinearLayoutManager(this));
        rvProdutoDetalhes.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}