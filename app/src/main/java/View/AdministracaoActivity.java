package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.system_pos.R;

import java.util.ArrayList;

import controller.ProdutoController;
import model.Produto;

public class AdministracaoActivity extends AppCompatActivity {

    private ImageButton btVoltarTelaInicial;
    private TextView tvListaProdutos;
    private ImageButton btListaProdutos;
    private TextView tvQuantidadeProdutos;
    private ProdutoController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracao);

        btVoltarTelaInicial = findViewById(R.id.btVoltarTelaInicial);
        tvListaProdutos = findViewById(R.id.tvListaProdutos);
        btListaProdutos = findViewById(R.id.btListaProdutos);
        tvQuantidadeProdutos = findViewById(R.id.tvQuantidadeProdutos);

        btVoltarTelaInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvListaProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirListaProdutos();
            }
        });

        btListaProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirListaProdutos();
            }
        });

        atualizarQuantidadeProdutos();
    }

    private void atualizarQuantidadeProdutos() {
        String text = "";
        controller = new ProdutoController(this);
        ArrayList<Produto> lista = controller.buscarTodosProdutos();
        int quantdItens;

        if(lista==null){
            quantdItens = 0;
        }else{
            quantdItens = controller.buscarTodosProdutos().size();
        }

        if(quantdItens==1){
            text = quantdItens + " item";
        }else{
            text = quantdItens + " itens";
        }


        tvQuantidadeProdutos.setText(text);
    }

    private void abrirListaProdutos() {
        Intent intent = new Intent(AdministracaoActivity.this, ListaProdutosActivity.class);

        startActivity(intent);
    }
}