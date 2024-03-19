package View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.system_pos.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

import controller.UsuarioController;

public class OpcoesLoginActivity extends AppCompatActivity {

    private FloatingActionButton btVoltarTelaInicial;
    private Button btLoginDono;
    private Button btLoginColaborador;
    private TextView tvCadastrese;
    private Button btLogarColaborador;
    private Button btLogarAdm;
    private EditText edEmailColaborador;
    private EditText edSenhaColaborado;
    private EditText edEmailAdm;
    private EditText edSenhaAdm;
    private FloatingActionButton btVoltarOpcoes;
    private AlertDialog cadastroDialog;
    private UsuarioController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcoes_login);

        btVoltarTelaInicial = findViewById(R.id.btVoltarTelaInicial);
        btLoginColaborador = findViewById(R.id.btLoginColaborador);
        btLoginDono = findViewById(R.id.btLoginDono);
        tvCadastrese = findViewById(R.id.tvCadastrese);

        btVoltarTelaInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btLoginColaborador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLoginColaborador();
            }
        });
        btLoginDono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLoginAdm();
            }
        });
        tvCadastrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCadastroUsuario();
            }
        });
    }

    private void abrirCadastroUsuario() {
        Intent intent = new Intent(OpcoesLoginActivity.this, CadastroUsuarioActivity.class);

        startActivity(intent);
    }

    private void abrirLoginColaborador() {

        View viewAlert = getLayoutInflater().inflate(R.layout.dialog_login_colaborador, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(viewAlert);
        builder.setCancelable(false);

        btLogarColaborador = viewAlert.findViewById(R.id.btLogarColaborador);
        btVoltarOpcoes = viewAlert.findViewById(R.id.btVoltarOpcoes);
        edEmailColaborador = viewAlert.findViewById(R.id.edEmailColaborador);
        edSenhaColaborado = viewAlert.findViewById(R.id.edSenhaColaborador);

        cadastroDialog = builder.create();

        cadastroDialog.show();

        btVoltarOpcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastroDialog.dismiss();
            }
        });

        btLogarColaborador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarLoginColaborador();
            }
        });
    }



    private void abrirLoginAdm() {

        View viewAlert = getLayoutInflater().inflate(R.layout.dialog_login_dono, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(viewAlert);
        builder.setCancelable(false);

        btLogarAdm = viewAlert.findViewById(R.id.btLogarAdm);
        btVoltarOpcoes = viewAlert.findViewById(R.id.btVoltarOpcoes);
        edEmailAdm = viewAlert.findViewById(R.id.edEmailAdm);
        edSenhaAdm = viewAlert.findViewById(R.id.edSenhaAdm);



        cadastroDialog = builder.create();

        cadastroDialog.show();

        btVoltarOpcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastroDialog.dismiss();
            }
        });
        btLogarAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarLoginAdm();
            }
        });
    }

    private void validarLoginAdm() {
        controller = new UsuarioController(this);

        for(int i=0; i<controller.buscarTodosUsuarios().size();i++){
            if(edEmailAdm.getText().toString().toLowerCase(Locale.ROOT).equals(controller.buscarTodosUsuarios().get(i).getEmail())){
                if(edSenhaAdm.getText().toString().equals(controller.buscarTodosUsuarios().get(i).getSenha())){
                    abrirMain();
                    break;
                }else{
                    edSenhaAdm.setError("Senha Incorreta!");
                    edSenhaAdm.requestFocus();
                    break;
                }
            }else{
                edEmailAdm.setError("Email Incorreto!");
                edEmailAdm.requestFocus();
            }
        }
    }

    private void validarLoginColaborador() {
        controller = new UsuarioController(this);
        

        for(int i=0; i<controller.buscarTodosUsuarios().size();i++){
            if(edEmailColaborador.getText().toString().toLowerCase(Locale.ROOT).equals(controller.buscarTodosUsuarios().get(i).getEmail())){
                if(edSenhaColaborado.getText().toString().equals(controller.buscarTodosUsuarios().get(i).getSenha())){
                    abrirMain();
                    break;
                }else{
                    edSenhaColaborado.setError("Senha Incorreta!");
                    edSenhaColaborado.requestFocus();
                    break;
                }
            }else{
                edEmailColaborador.setError("Email Incorreto!");
                edEmailColaborador.requestFocus();
            }
        }

    }

    private void abrirMain() {
        Intent intent = new Intent(OpcoesLoginActivity.this, MainActivity.class);

        startActivity(intent);
    }
}