package View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.system_pos.R;

import java.util.ArrayList;

import adapter.DetalheVendaListAdapter;
import adapter.VendaFinalizadaAdapter;
import controller.VendaController;
import model.ProdutoVenda;
import model.Venda;

public class VendaFinalizadaActivity extends AppCompatActivity {

    private ImageButton btFechar;
    private VendaController controller;
    private TextView tvIdVenda;
    private TextView tvNomeCliente;
    private TextView tvData;
    private TextView tvFormaPagamento;
    private RecyclerView rvDetalheFinal;
    ArrayList<ProdutoVenda> listaProdutos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda_finalizada);

        btFechar = findViewById(R.id.btFechar);
        tvIdVenda = findViewById(R.id.tvIdVenda);
        tvNomeCliente = findViewById(R.id.tvNomeCliente);
        tvData = findViewById(R.id.tvData);
        tvFormaPagamento = findViewById(R.id.tvFormaPagamento);
        rvDetalheFinal = findViewById(R.id.rvDetalheFinal);


        btFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarMain();
            }
        });
        
        carregarInformacooes();
    }

    private void carregarInformacooes() {
        controller = new VendaController(this);
        int tamanhoLista = controller.buscarTodoasVenda().size();
        ArrayList<Venda> lista = controller.buscarTodoasVenda();

        int id = lista.get(tamanhoLista-1).getId();
        Venda venda = controller.buscarVenda(id);

        tvIdVenda.setText("Venda: " + venda.getId());
        tvNomeCliente.setText("Cliente: " + venda.getCliente().getNome());
        tvData.setText("Data: " + venda.getDtVenda());
        tvFormaPagamento.setText("Forma de Pagamento: " + venda.getFormaPagamento().getDescricao());

        listaProdutos = venda.getListaProdutos();
        VendaFinalizadaAdapter adapter = new VendaFinalizadaAdapter(listaProdutos,this);
        rvDetalheFinal.setLayoutManager(new LinearLayoutManager(this));
        rvDetalheFinal.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void voltarMain() {
        Intent intent = new Intent(VendaFinalizadaActivity.this, MainActivity.class);

        startActivity(intent);
    }
}