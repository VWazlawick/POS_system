package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.system_pos.R;

import java.util.ArrayList;

import controller.FormaPagamentoController;
import controller.VendaController;
import model.FormaPagamento;
import model.Venda;

public class FinalizarVendaAvistaActivity extends AppCompatActivity {

    private ImageButton btVoltar;
    private Spinner spFormaPagamento;
    private ArrayList<FormaPagamento> lista;
    private FormaPagamentoController controller;
    private int posicaoSelecionada;
    private Button btFinalizarVenda;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_venda_avista);

        btVoltar = findViewById(R.id.btVoltar);
        spFormaPagamento = findViewById(R.id.spFormaPagamento);
        btFinalizarVenda = findViewById(R.id.btFinalizarVenda);
        intent = getIntent();


        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spFormaPagamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posicaoSelecionada = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btFinalizarVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizarVenda();
            }
        });

        carregarFormasPagamento();
    }

    private void finalizarVenda() {
        Venda venda = (Venda) intent.getSerializableExtra("venda");

        venda.setFormaPagamento(lista.get(posicaoSelecionada-1));

        VendaController vendaController = new VendaController(this);

        vendaController.salvarVenda(venda);

        Intent intent1 = new Intent(FinalizarVendaAvistaActivity.this, VendaFinalizadaActivity.class);
        startActivity(intent1);
    }

    private void carregarFormasPagamento() {
        controller = new FormaPagamentoController(this);
        lista =  controller.buscarTodasFormasPagamento();

        String[] listaExibir = new String[lista.size()+1];

        listaExibir[0] = "";

        for(int i = 0; i<lista.size();i++){
            FormaPagamento fp = lista.get(i);
            listaExibir[i+1] = fp.getDescricao();
        }
        ArrayAdapter adapter = new ArrayAdapter(FinalizarVendaAvistaActivity.this,
                android.R.layout.simple_dropdown_item_1line, listaExibir);

        spFormaPagamento.setAdapter(adapter);
    }
}