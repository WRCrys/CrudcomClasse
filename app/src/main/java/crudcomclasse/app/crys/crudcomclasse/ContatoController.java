package crudcomclasse.app.crys.crudcomclasse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cryst on 30/04/2017.
 */

public class ContatoController extends DataBaseAdapter{

    public ContatoController(Context context){
        //Integração com Banco de Dados
        super(context);
    }

    public boolean create(Contato contato){
        ContentValues values = new ContentValues();

        values.put("nome", contato.getNome());
        values.put("email", contato.getEmail());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isCreate = db.insert("contato", null, values) > 0;
        db.close();
        return isCreate;
    }
    public int totaldeContatos(){

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM contato";

        //Guardando o contador
        int contador = db.rawQuery(sql, null).getCount();
        return contador;
    }
    public List<Contato> listarContatos(){

        List<Contato> contatos = new ArrayList<>();

        String sql = "SELECT * FROM contato ORDER BY id DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String email = cursor.getString(cursor.getColumnIndex("email"));

                Contato contato = new Contato();

                contato.setId(id);
                contato.setNome(nome);
                contato.setEmail(email);

                contatos.add(contato);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return contatos;
    }
    public Contato buscarpeloID(int contatoID){
        Contato contato = null;

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM contato WHERE id = "+contatoID;

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String email = cursor.getString(cursor.getColumnIndex("email"));

            contato = new Contato();

            contato.setId(contatoID);
            contato.setNome(nome);
            contato.setEmail(email);
        }
        return contato;
    }
    public boolean update(Contato contato){

        ContentValues values = new ContentValues();

        values.put("nome", contato.getNome());
        values.put("email", contato.getEmail());

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(contato.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isUpdate = db.update("contato", values, where, whereArgs) > 0;

        db.close();

        return isUpdate;
    }
    public boolean delete(int contatoID){
        boolean isDeletado = false;

        SQLiteDatabase db = this.getWritableDatabase();
        isDeletado = db.delete("contato", "id ='" +contatoID+ "'", null) > 0;
        db.close();
        return isDeletado;
    }
}
