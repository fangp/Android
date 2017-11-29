package doom.sources;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	public DBHelper(Context context) {
		super(context, "test1.db", null, 1);
}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table test3(username varchar(20),title varchar(20) , info varchar(40), url varchar(40), image BLOB,primary key(username, title));");
		db.execSQL("create table msg3(username varchar(20) primary key, msg TEXT);");
		Log.d("DBTag", "onCreate .... ");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("alter table person add age int");
		Log.d("DBTag", "onUpgrade .... ");
	}

}