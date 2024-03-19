package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.system_pos.R;

import controller.TipoUsuarioController;
import model.TipoUsuario;

public class TelaInicialActivity extends AppCompatActivity {

    private Button btCadastrarConta;
    private Button btContaExistente;
    private TipoUsuarioController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        btCadastrarConta = findViewById(R.id.btCadastrarConta);
        btContaExistente = findViewById(R.id.btContaExistente);

        btCadastrarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaCadastro();
            }
        });

        btContaExistente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirOpcoesLogin();
            }
        });

    }

    private void abrirOpcoesLogin() {
        Intent intent = new Intent(TelaInicialActivity.this, OpcoesLoginActivity.class);

        startActivity(intent);
    }

    private void abrirTelaCadastro() {
        Intent intent = new Intent(TelaInicialActivity.this, CadastroUsuarioActivity.class);

        startActivity(intent);
    }
}