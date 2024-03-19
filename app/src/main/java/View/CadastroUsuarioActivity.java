package View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.system_pos.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import controller.TipoUsuarioController;
import controller.UsuarioController;
import model.TipoUsuarioEnum;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private FloatingActionButton btVoltarTelaInicial;
    private EditText edNomeUsuario;
    private EditText edEmailUsuario;
    private EditText edNrCpfCnpj;
    private EditText edSenha;
    private EditText edSenhaConfirma;
    private Button btCadastroUsuario;
    private UsuarioController controller;

    TipoUsuarioController tpc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        btVoltarTelaInicial = findViewById(R.id.btVoltarTelaInicial);
        edNomeUsuario = findViewById(R.id.edNomeUsuario);
        edEmailUsuario = findViewById(R.id.edEmailUsuario);
        edSenha = findViewById(R.id.edSenha);
        edSenhaConfirma = findViewById(R.id.edSenhaConfirma);
        edNrCpfCnpj = findViewById(R.id.edNrCpfCnpj);
        btCadastroUsuario = findViewById(R.id.btCadastroUsuario);

        btVoltarTelaInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btCadastroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarUsuario();
            }
        });
    }

    private void salvarUsuario() {
        controller = new UsuarioController(this);
        tpc = new TipoUsuarioController(this);

        TipoUsuarioEnum tp = TipoUsuarioEnum.ADMINISTRADOR;
        tp = tpc.buscarTpUsuario(1);

        String retorno = controller.salvarUsuario(edNomeUsuario.getText().toString(),
                edEmailUsuario.getText().toString(), edNrCpfCnpj.getText().toString(),
                edSenha.getText().toString(),edSenhaConfirma.getText().toString(), tp);

        if(retorno!=null){
            if(retorno.contains("Nome")){
                edNomeUsuario.setError(retorno);
                edNomeUsuario.requestFocus();
            }
            if(retorno.contains("Email válido")){
                edEmailUsuario.setError(retorno);
                edEmailUsuario.requestFocus();
            }
            if(retorno.contains("Cpf")){
                edNrCpfCnpj.setError(retorno);
                edNrCpfCnpj.requestFocus();
            }
            if(retorno.contains("válida")){
                edSenha.setError(retorno);
                edSenha.requestFocus();
            }
            if(retorno.contains("Senhas")){
                edSenhaConfirma.setError(retorno);
                edSenhaConfirma.requestFocus();
            }
            if(retorno.contains("cadastrado")){
                edEmailUsuario.setError(retorno);
                edEmailUsuario.requestFocus();

            }else{
                Toast.makeText(this, retorno, Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}