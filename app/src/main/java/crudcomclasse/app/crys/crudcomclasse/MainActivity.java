package crudcomclasse.app.crys.crudcomclasse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnCadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrar = (Button)findViewById(R.id.btnCriarContato);

        //Adicionando o Evento On Clickner List no botão Adicionar
        btnCadastrar.setOnClickListener(new CreateContatoOnClickListner());

        //Chamando o método contador de contatos
        contadorDeRegistros();

        atualizarListaDeContatos();
    }

    public void contadorDeRegistros(){
        String msg = "";

        int contador = new ContatoController(this).totaldeContatos();

        TextView txtContadorContatos = (TextView) findViewById(R.id.ContatdorContatos);

        if(contador == 0){
            msg = "Nenhum contato registrado.";
        }else if(contador == 1){
            msg = contador + " contato cadastrado";
        }else{
            msg = contador + " contatos cadastrados";
        }

        txtContadorContatos.setText(msg);
    }

    public void atualizarListaDeContatos(){
        LinearLayout linearLayoutRecords = (LinearLayout)findViewById(R.id.objetosContatos);

        linearLayoutRecords.removeAllViews();

        List<Contato> students = new ContatoController(this).listarContatos();

        if(students.size() > 0){
            for(Contato obj : students){
                int id = obj.getId();
                String nome = obj.getNome();
                String email = obj.getEmail();

                String textViewContents = nome + " - " + email;

                TextView textViewContatoItem = new TextView(this);
                textViewContatoItem.setPadding(0, 10, 0, 10);
                textViewContatoItem.setText(textViewContents);
                textViewContatoItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewContatoItem);
                textViewContatoItem.setOnLongClickListener(new RetriverOnLongClickListener());
            }
        }
    }
}
