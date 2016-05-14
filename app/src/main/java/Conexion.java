import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by edgar on 6/05/16.
 */
public class Conexion extends SQLiteOpenHelper{
    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE(id INTEGER PRIMARY KEY,t_objecto VARCHAR(200),des VARCHAR(200),Prestado VARCHAR(100),cantidad INTEGER,f_prestado DATE,f_entrega DATE,estado VARCHAR(1))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
