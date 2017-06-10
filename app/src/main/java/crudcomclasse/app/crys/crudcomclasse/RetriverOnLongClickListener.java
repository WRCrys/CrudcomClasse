package crudcomclasse.app.crys.crudcomclasse;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static crudcomclasse.app.crys.crudcomclasse.R.id.editTextContatoEmail;
import static crudcomclasse.app.crys.crudcomclasse.R.id.editTextContatoNome;

/**
 * Created by cryst on 30/04/2017.
 */

public class RetriverOnLongClickListener implements View.OnLongClickListener {
    Context context;
    String id;

    @Override
    public boolean onLongClick(View view) {
        context = view.getContext();
        id = view.getTag().toString();

        final CharSequence[] itens = {"Editar","Deletar"};

        new AlertDialog.Builder(context).setTitle("Detalhes do Contato").setItems(itens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0){
                    //Editar o contato
                    editContatoPeloID(Integer.parseInt(id));
                } else if (item == 1){
                    //Deletar
                    boolean isDeletouComSucesso = new ContatoController(context).delete(Integer.parseInt(id));
                    if(isDeletouComSucesso){
                        Toast.makeText(context, "Contato deletado com sucesso! =)",Toast.LENGTH_SHORT).show();
                        ((MainActivity) context).contadorDeRegistros();
                        ((MainActivity) context).atualizarListaDeContatos();
                    } else {
                        Toast.makeText(context, "Erro ao deletaro contato! =(", Toast.LENGTH_SHORT).show();
                    }
                }

                dialog.dismiss();

            }
        }).show();

        return false;
    }

    public void editContatoPeloID(final int contatoID){

        final ContatoController contatoController = new ContatoController(context);

        final Contato contato = contatoController.buscarpeloID(contatoID);
        //Injeta o layout contato_form
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View formContato = inflater.inflate(R.layout.contato_form, null, false);
        //Popular nome e email com dados da lista

        final EditText editTextNome = (EditText) formContato.findViewById(editTextContatoNome);

        final EditText editTextEmail = (EditText)formContato.findViewById(editTextContatoEmail);

        editTextNome.setText(contato.getNome());
        editTextEmail.setText(contato.getEmail());
        //Show do formulário com dados populados
        new AlertDialog.Builder(context).setView(formContato).setTitle("Editar Contato").setPositiveButton("Atualizar dados",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Contato novoContato = new Contato();
                novoContato.setId(contatoID);
                novoContato.setNome(editTextNome.getText().toString());
                novoContato.setEmail(editTextEmail.getText().toString());

                boolean isUpdate = contatoController.update(novoContato);

                if(isUpdate){
                    Toast.makeText(context, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT);
                    ((MainActivity)context).atualizarListaDeContatos();
                } else {
                    Toast.makeText(context, "Falha ao salvar o contato", Toast.LENGTH_SHORT);
                }
                dialog.cancel();
            }
        }).show();
    }
}
