package View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.system_pos.R;

import controller.ClienteController;
import model.Cliente;

public class CadastroClienteActivity extends AppCompatActivity {

    private ImageButton btVoltar;
    private Button btCadastrarCliente;
    private EditText edNomeCliente;
    private EditText edNrTelefone;
    private EditText edEmailCliente;
    private EditText edEnderecoCliente;
    private ClienteController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        btVoltar = findViewById(R.id.btVoltar);
        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);
        edNomeCliente = findViewById(R.id.edNomeCliente);
        edNrTelefone = findViewById(R.id.edNrTelefone);
        edEmailCliente = findViewById(R.id.edEmailCliente);
        edEnderecoCliente = findViewById(R.id.edEnderecoCliente);

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarCliente();
            }
        });
    }

    private void salvarCliente() {
        controller = new ClienteController(this);


        String retorno = controller.salvarCliente(edNomeCliente.getText().toString(), edEmailCliente.getText().toString(),
                edNrTelefone.getText().toString(), edEnderecoCliente.getText().toString());

        if(retorno!=null){
            if(retorno.contains("Nome")){
                edNomeCliente.requestFocus();
                edNomeCliente.setError(retorno);
            }
            else if(retorno.contains("Email")){
                edEmailCliente.setError(retorno);
                edEmailCliente.requestFocus();
            }
            else if(retorno.contains("Numero")){
                edNrTelefone.setError(retorno);
                edNrTelefone.requestFocus();
            }else{
                Toast.makeText(CadastroClienteActivity.this, retorno, Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this, "Cliente cadastrado com sucesso", Toast.LENGTH_LONG);
            finish();
        }

    }
}