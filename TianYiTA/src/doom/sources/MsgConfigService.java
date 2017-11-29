package doom.sources;

import doom.main.MainActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MsgConfigService {

    private Context context;
    private String getmsg = null;
		
    public MsgConfigService(Context context){
	    super();
	    this.context = context;
		}
	public void SaveMsg(String msg){
		DBHelper  dbHelper = new DBHelper(context);
    	SQLiteDatabase db = dbHelper.getWritableDatabase();
    	Cursor cursor = db.rawQuery("select * from msg3 where username=?", new String[]{MainActivity.myAccount});
    	if(cursor.moveToFirst()==false)
    		db.execSQL("insert into msg3(username,msg) values(?,?)", new String[]{MainActivity.myAccount,msg});
    	else
    	{
    		ContentValues values = new ContentValues(); 
    		values.put("msg", msg);
    		db.update("msg3", values, "username=?", new String[]{MainActivity.myAccount});  
    	}
    	dbHelper.close();
    	db.close();
	}
	public String ShowMsg(){
		DBHelper  dbHelper = new DBHelper(context);
    	SQLiteDatabase db = dbHelper.getReadableDatabase();
    	Cursor cursor = db.rawQuery("select * from msg3 where username=?", new String[]{MainActivity.myAccount});
    	if(cursor.moveToFirst() == false)
    		return null;
    	else
    	{
    		getmsg = cursor.getString(cursor.getColumnIndex("msg"));
    		Log.v("msg", getmsg);
		    return getmsg;
    	}
	}
	}


