package doom.apppush;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.tianyita.R;

import doom.favorite.FavoriteFragment.ViewHolder;
import doom.main.MainActivity;
import doom.sources.FavoriteService;
import doom.sources.MySources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AppSelect extends Fragment {
	private List<Map<String, Object>> mData;
	
	List<Map<String,Object>> lm;
	
	private ListView lv1;
	private Button fresh_btn;
	private Button submit_btn;
	private Button push_btn;
	private Button favorite_btn;
	private MyAdapter adapter;
	private Map<String,String> selectedApp;
	private Context context;
	private Bitmap bp;
	public final static String account="user";
	
	
	JSONArray ja;
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg){
			if(msg.what==MySources.REFRESH){
				mData=new ArrayList<Map<String,Object>>();
				mData.addAll(lm);
			}
    		adapter.notifyDataSetChanged();
		}
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
		View rootview = inflater.inflate(R.layout.fragment_apppush, container, false);
		bp = BitmapFactory.decodeResource(getResources(), R.drawable.i1);
		mData = IntilizeData();		
		getAllAppFromServer();
		context = this.getActivity();
		fresh_btn = (Button)rootview.findViewById(R.id.fresh_btn);
        submit_btn = (Button)rootview.findViewById(R.id.submit_btn);
        favorite_btn = (Button)rootview.findViewById(R.id.favorite_btn);
        push_btn = (Button)rootview.findViewById(R.id.push_btn);
        adapter = new MyAdapter(this.getActivity());
		//adapter = new MyAdapter(this);
        lv1=(ListView)rootview.findViewById(R.id.lv1);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ViewHolder holder = (ViewHolder)arg1.getTag();
				holder.checkbox.toggle();
				mData.get(arg2).put("position", holder.checkbox.isChecked());
				
			}
        	
        });
        
        submit_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				selectedApp = new HashMap<String,String>();
				for(int i=0;i<mData.size();i++)
				{
					if((Boolean)(mData.get(i).get("position"))==true)
						selectedApp.put(String.valueOf(mData.get(i).get("title")), String.valueOf(mData.get(i).get("info")));
				}
				Log.v("size", String.valueOf(selectedApp.size()));
				SerializableMap tmpmap=new SerializableMap();  
	            tmpmap.setMap(selectedApp);   
	            Intent intent = new Intent();
	            intent.setClass(context, ToNumberActivity.class);
				intent.putExtra("selectedApp", tmpmap);
				startActivity(intent);
				//AppSelect.this.getActivity().finish();
				// TODO Auto-generated method stub
				
			}
        	
        });
        push_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectedApp = new HashMap<String,String>();
				for(int i=0;i<mData.size();i++)
				{
					if((Boolean)(mData.get(i).get("position"))==true)
						selectedApp.put(String.valueOf(mData.get(i).get("title")), String.valueOf(mData.get(i).get("info")));
				}
				Log.v("size", String.valueOf(selectedApp.size()));
				SerializableMap tmpmap=new SerializableMap();  
	            tmpmap.setMap(selectedApp);   
	            Intent intent = new Intent();
	            intent.setClass(context, ToCustomerActivity.class);
				intent.putExtra("selectedApp", tmpmap);
				startActivity(intent);
				//AppSelect.this.getActivity().finish();
			}
        	
        });
        favorite_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FavoriteService favoriteservice = new FavoriteService(context);
				List<Map<String, Object>> favorite = new ArrayList<Map<String,Object>>();
				for(int i=0;i<mData.size();i++)
				{
					if((Boolean)mData.get(i).get("position")==true)
						{
						Log.v("testt", String.valueOf(i));
						favorite.add(mData.get(i));
						}
				}
				Log.v("test", String.valueOf(favorite.size()));
				favoriteservice.AddFavorite(favorite);
				Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
				
			}
        	
        });
        fresh_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getAllAppFromServer();
			}
        	
        });
		return rootview;
	}

	 private List<Map<String, Object>> IntilizeData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//Getbp bp1 = new Getbp();	
		//bp1.setbp(bp);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "G1");
		map.put("info", "google 1");
	    //map.put("img", R.drawable.i1);
	    map.put("img", bp);
	    map.put("position", false);
		list.add(map);
		
		return list;
     }
	 
	 private void getAllAppFromServer(){
		 Runnable runnable = new Runnable(){
				@Override
				public void run() {
					HttpClient hc = new DefaultHttpClient();
					HttpPost post = new HttpPost(MySources.getAllAppUrl);
					Header[] headers=new BasicHeader[6];
					headers[0]=new BasicHeader("Accept","application/x-shockwave-flash,image/gif,*/*");
					headers[1] = new BasicHeader("Accept-Language","zh-cn");
					headers[2] = new BasicHeader("Host","10.0.2.2");
					headers[3] = new BasicHeader("Connection", "keep-Alive");
					headers[4] = new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
					headers[5] = new BasicHeader("Cookie","JSESSIONID=ABC57456441F469CE68B0FDD8C2B4EDF");
					post.setHeaders(headers);
						
					HttpResponse res;
					try {
							res = hc.execute(post);
						  	if(res.getStatusLine().getStatusCode() == 200){
						    Log.i("test----","code = 200");				
						    String strJson = EntityUtils.toString(res.getEntity());
						    ja = new JSONArray(strJson);
						    Log.i("TestApp", ja.toString());
						    lm=new ArrayList<Map<String,Object>>();
						    Map<String,Object> map;
						    JSONObject jo;
						    for(int i=0;i<ja.length();i++){
						    	map=new HashMap<String,Object>();
						    	jo = ja.getJSONObject(i);
					    		String str1 = jo.getString("a_name");
					    		String str2 = jo.getString("a_address");
					    		String str3 = jo.getString("a_picture");
					    		map.put("title", str1);
					    		map.put("info", str2);
					    		map.put("img", getImage(str3));
					    		map.put("position", false);
					    		lm.add(map);
						    }
						    Message msg = new Message();
						    msg.what=MySources.REFRESH;
						    mHandler.sendMessage(msg);
						    //Log.i("myTest", "消息传输成功");
						  	}
					}catch(Exception e){
						e.printStackTrace();
						//Log.i("myTest", "消息传输失败");
					}finally{
						hc.getConnectionManager().shutdown();
					}
				}
			};
			new Thread(runnable).start();
	 }
	 
	 private Bitmap getImage(String path) throws Exception {
			URL url = new URL(path);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			byte[]data ;
			con.setRequestMethod("GET");
		    	Log.i("test---------","44444444444");
			    InputStream in = con.getInputStream();
				data = read2Byte(in);
				return BitmapFactory.decodeByteArray(data, 0, data.length);
		}
		private byte[] read2Byte(InputStream in) throws IOException {
			byte[] data;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte[]buf = new byte[1024];
			int len = 0;
			while((len = in.read(buf))!=-1){
				bout.write(buf, 0, len);
			}
			data = bout.toByteArray();
			return data;
		}
	 
	 
	    public final class ViewHolder{
	    public ImageView img;
		public TextView title;
		public TextView info;
		public CheckBox checkbox;
	    }
	    public class MyAdapter extends BaseAdapter{
	    private LayoutInflater mInflater;
	    
	    
		public MyAdapter(Context context){
		   this.mInflater = LayoutInflater.from(context);
		   //init();
		}
		public void init(){
			for(int i=0;i<mData.size();i++)
			{
				mData.get(i).put("position", false);
				//mData.get(i).put("url", "xx");
			}
		}
		@Override
		public int getCount() {
		   // TODO Auto-generated method stub
		   return mData.size();
	   }
		      
		@Override
        public Object getItem(int arg0) {
		   // TODO Auto-generated method stub
		   return null;
	   }
		      
		@Override
	    public long getItemId(int arg0) {
		   // TODO Auto-generated method stub
		   return 0;
	   }
	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	
            
			ViewHolder holder = null;
			
			if (convertView == null) {
			                      
			   holder=new ViewHolder(); 
			   convertView = mInflater.inflate(R.layout.appunit, null);
			   holder.img = (ImageView)convertView.findViewById(R.id.img);
			   holder.title = (TextView)convertView.findViewById(R.id.title);
			   holder.info = (TextView)convertView.findViewById(R.id.info);
			   holder.checkbox = (CheckBox)convertView.findViewById(R.id.check_box);
			   convertView.setTag(holder);
		
			                      
			   }else {
			                      
			        holder = (ViewHolder)convertView.getTag();
			       
			        
			     }
			                   
			   holder.img.setImageBitmap((Bitmap) mData.get(position).get("img"));
			   //holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
			   holder.title.setText((String)mData.get(position).get("title"));
			   holder.info.setText((String)mData.get(position).get("info"));
			   holder.checkbox.setChecked((Boolean) mData.get(position).get("position"));
			  return convertView;
		}
	 }
	
}
