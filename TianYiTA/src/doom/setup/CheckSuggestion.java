package doom.setup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.tianyita.R;

import doom.sources.MySources;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CheckSuggestion extends Fragment {
	
	private List<Map<String, Object>> mData;
	private ListView lv_suggestion;
	MyAdapter adapter;
	
	JSONArray ja;
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg){
			if(msg.what==MySources.REFRESH){
				mData=new ArrayList<Map<String,Object>>();
		    	Map<String, Object> map;
				for(int i=0;i<ja.length();i++){
		    		map = new HashMap<String, Object>();
		    		JSONObject jo;
					try {
						jo = ja.getJSONObject(i);
			    		String str1 = jo.getString("o_account");
			    		String str2 = jo.getString("type_name");
			    		String str3 = jo.getString("re_content");
			    		String str4 = jo.getString("re_reponse");
			    		map.put("suggestion_o_account", str1);
			    		map.put("suggestion_type_id", str2);
			    		map.put("suggestion_content", str3);
				    	map.put("suggestion_res_content", str4);
			    		mData.add(map);
						adapter.notifyDataSetChanged();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						Toast.makeText(getActivity(), "建议查询出现问题！", Toast.LENGTH_SHORT).show();
						e.printStackTrace();
					}
		    	}
				Toast.makeText(getActivity(), "建议查询成功！", Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	 {
	        // Inflate the layout for this fragment
		 GetAllSuggestionFromServer();
		 View rootview = inflater.inflate(R.layout.fragment_checksuggestion, container, false);
		 mData=getinitializedData();
		 adapter = new MyAdapter(this.getActivity());
		 lv_suggestion=(ListView) rootview.findViewById(R.id.lv_checksuggestion);
		 lv_suggestion.setAdapter(adapter);
		 return rootview;
	 }
	
	
	private List<Map<String, Object>> getinitializedData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("suggestion_o_account", "这里显示操作员的账号");
		map.put("suggestion_type_id", "这里显示建议类型");
		map.put("suggestion_content", "这里显示建议信息");
		map.put("suggestion_res_content", "这里显示回复内容");
		list.add(map);
		
		return list;
	}


	public final class ViewHolder{
	    public TextView suggestion_o_account;
		public TextView suggestion_type_id;
		public TextView suggestion_content;
		public TextView suggestion_res_content;
     }
	
	  public class MyAdapter extends BaseAdapter{
	    private LayoutInflater mInflater;
	    
		public MyAdapter(Context context){
		   this.mInflater = LayoutInflater.from(context);
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
	    public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {          
			   holder=new ViewHolder(); 
			   convertView = mInflater.inflate(R.layout.suggestion_unit, null);
			   holder.suggestion_o_account = (TextView)convertView.findViewById(R.id.suggestion_o_account);
			   holder.suggestion_type_id = (TextView)convertView.findViewById(R.id.suggestion_type_id);
			   holder.suggestion_content = (TextView)convertView.findViewById(R.id.suggestion_content);
			   holder.suggestion_res_content = (TextView)convertView.findViewById(R.id.suggestion_res_content);
			   convertView.setTag(holder);                   
			   }else
				   holder = (ViewHolder)convertView.getTag();       
			   holder.suggestion_o_account.setText((String)mData.get(position).get("suggestion_o_account"));
			   holder.suggestion_type_id.setText((String)mData.get(position).get("suggestion_type_id"));
			   holder.suggestion_content.setText((String)mData.get(position).get("suggestion_content"));
			   holder.suggestion_res_content.setText((String)mData.get(position).get("suggestion_res_content"));
			   
			   return convertView;
		}
	 }
	  
	  
	  private void GetAllSuggestionFromServer(){
			Runnable runnable = new Runnable(){
			    @Override
			    public void run() {
					HttpClient hc = new DefaultHttpClient();
					HttpPost post = new HttpPost(MySources.getAllSuggestionUrl);
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
}


