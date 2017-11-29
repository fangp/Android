package doom.readinfo;

import java.io.UnsupportedEncodingException;
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
import com.example.tianyita.R.layout;
import com.example.tianyita.R.menu;

import doom.main.MainActivity;
import doom.sources.MySources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.Toast;

public class FragmentReadinfo extends Fragment {

	TabHost tabhost;
	ListView lv_peixun;
	ListView lv_yingxiao;
	List<Map<String,Object>> lm_yingxiao=new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> lm_peixun=new ArrayList<Map<String,Object>>();
	JSONArray ja;
	SimpleAdapter sa_peixun;
	SimpleAdapter sa_yingxiao;
	JSONObject jo;
	String con_id_peixun;
	String con_id_yingxiao;
	
	Bitmap bp1;
	Bitmap bp2;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg){
			JSONObject jo;
			lm_peixun.clear();
			lm_yingxiao.clear();
			try{
				if(msg.what==1){
					for(int i=0;i<ja.length();i++){
						jo=ja.getJSONObject(i);
						//l_peixun.add(jo.getString("con_title")+"("+jo.getString("readable")+")");
						Map<String,Object> map=new HashMap<String,Object>();
						map.put("title", jo.getString("con_title"));
						if(jo.getString("readable").equals("“—∂¡"))
							map.put("image", R.drawable.msg_read);
						else if(jo.getString("readable").equals("Œ¥∂¡"))
							map.put("image", R.drawable.msg_not_read);
						lm_peixun.add(map);
						Log.i("TestTable", jo.getString("con_title"));
					}
					sa_peixun.notifyDataSetChanged();
				}
				else if(msg.what==2){
					for(int i=0;i<ja.length();i++){
						jo=ja.getJSONObject(i);
						//l_peixun.add(jo.getString("con_title")+"("+jo.getString("readable")+")");
						Map<String,Object> map=new HashMap<String,Object>();
						map.put("title", jo.getString("con_title"));
						if(jo.getString("readable").equals("“—∂¡"))
							map.put("image", R.drawable.msg_read);
						else if(jo.getString("readable").equals("Œ¥∂¡"))
							map.put("image", R.drawable.msg_not_read);
						lm_yingxiao.add(map);
						Log.i("TestTable", jo.getString("con_title"));
					}
					sa_yingxiao.notifyDataSetChanged();
				}
			}catch(Exception e){
			
			}
		}
	};
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_readinfo, container, false);
       /* bp1 = BitmapFactory.decodeResource(getResources(), R.drawable.msg_read);
        bp2 = BitmapFactory.decodeResource(getResources(), R.drawable.msg_not_read);*/
        tabhost = (TabHost) rootview.findViewById(R.id.tabHost);
        tabhost.setup();
        tabhost.addTab(tabhost.newTabSpec("tab_1").setIndicator("≈‡—µ◊ ¡œ").setContent(R.id.tab1));
        tabhost.addTab(tabhost.newTabSpec("tab_2").setIndicator("”™œ˙–≈œ¢").setContent(R.id.tab2));
        tabhost.setOnTabChangedListener(new ChangeTab());
        //getData();
        getAllInfo(1,"1");
        //aa_peixun=new ArrayAdapter<String>(getActivity(),
        //		android.R.layout.simple_expandable_list_item_1,l_peixun);
        //aa_yingxiao=new ArrayAdapter<String>(getActivity(),
        //		android.R.layout.simple_expandable_list_item_1,l_yingxiao);
        
        sa_peixun=new SimpleAdapter(getActivity(),lm_peixun,R.layout.msgunit,
        		new String[]{"image","title"},new int[]{R.id.msg_readable,R.id.msg_title});
        sa_yingxiao=new SimpleAdapter(getActivity(),lm_yingxiao,R.layout.msgunit,
        		new String[]{"image","title"},new int[]{R.id.msg_readable,R.id.msg_title});
        
        lv_peixun=(ListView) rootview.findViewById(R.id.lv_peixun);
        lv_peixun.setAdapter(sa_peixun);
        lv_peixun.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				try {
					JSONObject jo= ja.getJSONObject(arg2);
					con_id_peixun=jo.getString("con_id");
					Intent intent = new Intent();
					intent.setClass(getActivity(), FragmentInfoContent.class);
					intent.putExtra("content", jo.getString("con_text"));
					startActivityForResult(intent, 1);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });

        lv_yingxiao=(ListView) rootview.findViewById(R.id.lv_yinxiao);
        lv_yingxiao.setAdapter(sa_yingxiao);
        lv_yingxiao.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				try {
					JSONObject jo= ja.getJSONObject(arg2);
					con_id_yingxiao=jo.getString("con_id");
					Intent intent =new Intent();
					intent.setClass(getActivity(), FragmentInfoContent.class);
					intent.putExtra("content", jo.getString("con_text"));
					startActivityForResult(intent, 2);
					Log.i("Testcon", jo.getString("con_text"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }); 
        
        return rootview;
    }
    
    
    /*public void getData(){
    	Map<String, Object> map = new HashMap<String, Object>();  
    	map.put("image", R.drawable.read);  
    	map.put("title", "≤‚ ‘"); 
    	lm_yingxiao.add(map);
    	lm_peixun.add(map);
    }*/
    
    
    @Override
    public void onActivityResult(int reqCode, int resCode, Intent intent) {
    	Log.i("Code", reqCode+":"+resCode);
        if (reqCode == 1) {
            //l_peixun.clear();
        	lm_peixun.clear();
            returnToServer(con_id_peixun);
            getAllInfo(1,"1");
        }
        else if(reqCode==2){
        	//l_yingxiao.clear();
        	lm_yingxiao.clear();
            returnToServer(con_id_yingxiao);
            getAllInfo(2,"2");
        }
    }


    private void returnToServer(final String con_id) {
		// TODO Auto-generated method stub
		
    	Runnable runnable = new Runnable(){
		    @Override
		    public void run() {
				HttpClient hc = new DefaultHttpClient();
				HttpPost post = new HttpPost(MySources.modifyInfoUrl);
				Header[] headers=new BasicHeader[6];
				headers[0]=new BasicHeader("Accept","application/x-shockwave-flash,image/gif,*/*");
				headers[0] = new BasicHeader("Accept","*/*");
				headers[1] = new BasicHeader("Accept-Language","zh-cn");
				headers[2] = new BasicHeader("Host",MySources.hostUrl);
				headers[3] = new BasicHeader("Connection", "keep-Alive");
				headers[4] = new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
				headers[5] = new BasicHeader("Cookie","JSESSIONID=ABC57456441F469CE68B0FDD8C2B4EDF");
				post.setHeaders(headers);
			   
				String str1=new String();
				String str2=new String();
				try {
					str1 = URLEncoder.encode(MainActivity.myAccount, "UTF-8");
					str2 = URLEncoder.encode(con_id, "UTF-8");
				} catch (UnsupportedEncodingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				NameValuePair pair1 = new BasicNameValuePair("o_account",str1);
				NameValuePair pair2 = new BasicNameValuePair("con_id",str2);
				List<NameValuePair> listParams = new ArrayList<NameValuePair>();
				listParams.add(pair1);
				listParams.add(pair2);
				
				HttpEntity requestHttpEntity;
				try {
					requestHttpEntity = new UrlEncodedFormEntity(listParams);
					post.setEntity(requestHttpEntity);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				HttpResponse res;
				try {
				    res = hc.execute(post);
				    if(res.getStatusLine().getStatusCode() == 200){
				    	Log.i("test----","code = 200");
				    }
				}catch(Exception e){
					e.printStackTrace();
					Log.i("myTest", "ÃÌº” ß∞‹");
				}finally{
					hc.getConnectionManager().shutdown();
				}
		    }
		  };
		  new Thread(runnable).start();
    	
	}

	private class ChangeTab implements TabHost.OnTabChangeListener
    {
        @Override
        public void onTabChanged(String tabId)
        {
            if(tabId.equals("tab_1"))
            {
                getAllInfo(1,"1");
                Log.i("TestTable", "table1");
            }
            else if(tabId.equals("tab_2"))
            {
            	getAllInfo(2,"2");
            }
        }
    }
    
    private void getAllInfo(final int i,final String type){
    		Runnable runnable = new Runnable(){
    		    @Override
    		    public void run() {
    				HttpClient hc = new DefaultHttpClient();
    				HttpPost post = new HttpPost(MySources.getAllInfoUrl);
    				Header[] headers=new BasicHeader[6];
    				headers[0]=new BasicHeader("Accept","application/x-shockwave-flash,image/gif,*/*");
    				headers[0] = new BasicHeader("Accept","*/*");
    				headers[1] = new BasicHeader("Accept-Language","zh-cn");
    				headers[2] = new BasicHeader("Host",MySources.hostUrl);
    				headers[3] = new BasicHeader("Connection", "keep-Alive");
    				headers[4] = new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
    				headers[5] = new BasicHeader("Cookie","JSESSIONID=ABC57456441F469CE68B0FDD8C2B4EDF");
    				post.setHeaders(headers);
    			   
    				String str1=new String();
    				String str2=new String();
    				try {
    					str1 = URLEncoder.encode(MainActivity.myAccount, "UTF-8");
    					str2 = URLEncoder.encode(type, "UTF-8");
    				} catch (UnsupportedEncodingException e2) {
    					// TODO Auto-generated catch block
    					e2.printStackTrace();
    				}
    				
    				NameValuePair pair1 = new BasicNameValuePair("o_account",str1);
    				NameValuePair pair2 = new BasicNameValuePair("type_id",str2);
    				List<NameValuePair> listParams = new ArrayList<NameValuePair>();
    				listParams.add(pair1);
    				listParams.add(pair2);
    				
    				HttpEntity requestHttpEntity;
    				try {
    					requestHttpEntity = new UrlEncodedFormEntity(listParams);
    					post.setEntity(requestHttpEntity);
    				} catch (Exception e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				
    				HttpResponse res;
    				try {
    				    res = hc.execute(post);
    				    if(res.getStatusLine().getStatusCode() == 200){
    				    	Log.i("test----","code = 200");				
    				    	String result = EntityUtils.toString(res.getEntity());
    				    	ja=new JSONArray(result);
    				    	Message msg=new Message();
    				    	msg.what=i;
    				    	mHandler.sendMessage(msg);
    				    }
    				}catch(Exception e){
    					e.printStackTrace();
    					Log.i("myTest", "ÃÌº” ß∞‹");
    				}finally{
    					hc.getConnectionManager().shutdown();
    				}
    		    }
    		  };
    		  new Thread(runnable).start();
    }

}
