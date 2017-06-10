package crudcomclasse.app.crys.crudcomclasse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cryst on 30/04/2017.
 */

public class DataBaseAdapter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "CrudCompleto.DB";
    public DataBaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE contato" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, email TEXT )";

        //Comando para criar a tabela acima no banco
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*OBS: É de responsabilidade do desenvolvedor criar os procedimentos
        * de Backup de seu banco de dados e seus dados antes de efetuar um
        * upgrade de versão!*/

        String sql = "DROP TABLE IF EXISTS contato";
        db.execSQL(sql);
        onCreate(db);

    }
}
