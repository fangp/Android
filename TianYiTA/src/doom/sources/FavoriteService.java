package doom.sources;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import doom.main.MainActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

public class FavoriteService {

	private Context context;
	
	public FavoriteService(Context context){
		super();
		this.context = context;
	}
	public void AddFavorite(List<Map<String,Object>> data){
		DBHelper  dbHelper = new DBHelper(context);
    	SQLiteDatabase db = dbHelper.getWritableDatabase();
    	//Getbp bp1 = new Getbp();
    	Log.v("size", String.valueOf(data.size()));
    	for(int i=0;i<data.size();i++)
    	    {
    	    ContentValues values = new ContentValues();
    	    final ByteArrayOutputStream os = new ByteArrayOutputStream();  
            //(bp1.getbp()).compress(Bitmap.CompressFormat.PNG, 100, os);
            ((Bitmap) data.get(i).get("img")).compress(Bitmap.CompressFormat.PNG, 100, os);
            values.put("username", MainActivity.myAccount);
            values.put("title", (String) data.get(i).get("title"));
            values.put("info", (String) data.get(i).get("info"));
            values.put("url", "xx");
            values.put("image", os.toByteArray());
       /* Log.v("title", (String) values.get("title"));
        Log.v("info", (String) values.get("info"));
        Log.v("url", (String) values.get("url"));*/
        
            db.insert("test3", null, values);
            }
    	 dbHelper.close();
         db.close();
    }
	public List<Map<String,Object>> ListFavorite(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		DBHelper  dbHelper = new DBHelper(context);
    	SQLiteDatabase db = dbHelper.getReadableDatabase();
    	//Cursor cursor = db.query("favor", new String[]{"title", "info", "url", "image"},"name=?", new String[]{name}, null,null, "name", "0,5");
    	Cursor cursor = db.rawQuery("select * from test3 where username = ?", new String[]{MainActivity.myAccount});
    	
   /* 	Log.v("title", cursor.getString(0));
		Log.v("info", cursor.getString(1));
		Log.v("url", cursor.getString(2));*/
		
    	
		while(cursor.moveToNext())
    	{
    		map = new HashMap<String,Object>();
    		map.put("title", cursor.getString(cursor.getColumnIndex("title")));
    		map.put("info", cursor.getString(cursor.getColumnIndex("info")));
    		map.put("url", cursor.getString(cursor.getColumnIndex("url")));
    		
    		/*Log.v("title", cursor.getString(0));
    		Log.v("info", cursor.getString(1));
    		Log.v("url", cursor.getString(2));*/
    		
    		byte[] in = cursor.getBlob(cursor.getColumnIndex("image"));
    		Bitmap image = BitmapFactory.decodeByteArray(in, 0, in.length);
    		in = null;
    		map.put("img", image);
    		list.add(map);
    	    }
    	
    	dbHelper.close();
        db.close();
		return list;
		
	}
	public void DeleteFavor(List<Map<String,Object>> data)
	{
		DBHelper  dbHelper = new DBHelper(context);
    	SQLiteDatabase db = dbHelper.getReadableDatabase();
    	for(int i=0;i<data.size();i++)
    	{
    		db.delete("test3", "username=? and title = ?", new String[]{MainActivity.myAccount,(String) data.get(i).get("title")});
    	}
	}
}
