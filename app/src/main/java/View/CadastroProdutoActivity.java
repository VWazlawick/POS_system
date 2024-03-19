package View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.system_pos.R;

import controller.ProdutoController;

public class CadastroProdutoActivity extends AppCompatActivity {

    private Button btCadastrarProduto;
    private ImageButton btVoltar;
    private ProdutoController controller;
    private EditText edNomeProduto;
    private EditText edCustoProduto;
    private EditText edValorVenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        btCadastrarProduto = findViewById(R.id.btCadastrarProduto);
        edNomeProduto = findViewById(R.id.edNomeProduto);
        edCustoProduto = findViewById(R.id.edCustoProduto);
        edValorVenda = findViewById(R.id.edValorVenda);
        btVoltar = findViewById(R.id.btVoltar);

        btCadastrarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarProduto();
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void salvarProduto() {
        controller = new ProdutoController(this);

        String retorno = controller.salvarProduto(edNomeProduto.getText().toString(),
                edCustoProduto.getText().toString(), edValorVenda.getText().toString());

        if(retorno!=null){
            if(retorno.contains("produto")){
                edNomeProduto.setError(retorno);
                edNomeProduto.requestFocus();
            } else if (retorno.contains("valor")) {
                edValorVenda.setError(retorno);
                edValorVenda.requestFocus();
            }else if(retorno.contains("custo")){
                edCustoProduto.setError(retorno);
                edCustoProduto.requestFocus();
            }else{
                Toast.makeText(this, retorno, Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Produto Cadastrado com sucesso!",Toast.LENGTH_LONG).show();
            finish();
        }
    }
}