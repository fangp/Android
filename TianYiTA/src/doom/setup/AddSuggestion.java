package doom.setup;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.tianyita.R;

import doom.main.MainActivity;
import doom.sources.MySources;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddSuggestion extends Fragment {
	
	Spinner spinner_addSuggestion=null;
	String type;
	EditText suggestion_to_send;
	Button btn_sendSuggestion;
	Button btn_cancelSendSuggestion;
	
	String result;
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg){
			if(msg.what==MySources.REFRESH){
				switch(Integer.valueOf(result))
				{
					case 1:
						FragmentManager fragmentManager = getFragmentManager();
						Fragment fragment =new SetUpMain();
						FragmentTransaction transaction = fragmentManager.beginTransaction();
						if (fragment !=null){
							transaction.replace(R.id.container, fragment);
							transaction.commit();
						}
						Toast.makeText(getActivity(), "建议提交成功！", Toast.LENGTH_SHORT).show();
						break;
					case 2:
						Toast.makeText(getActivity(), "建议提交失败！", Toast.LENGTH_SHORT).show();
						break;
				}
			}
		}
	};
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	 {
	        // Inflate the layout for this fragment
		 View rootview = inflater.inflate(R.layout.fragment_addsuggestion, container, false);
		 String items[]={"建议","意见","其他"};
		 ArrayAdapter<String> arrayAdapter = new 
				 ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,items);
		 suggestion_to_send=(EditText) rootview.findViewById(R.id.suggestion_to_send);
		 spinner_addSuggestion=(Spinner) rootview.findViewById(R.id.spinner_suggestionType);
		 spinner_addSuggestion.setAdapter(arrayAdapter);
		 spinner_addSuggestion.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				int i=arg2+1;
				type=""+i;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		 });
		 btn_sendSuggestion=(Button) rootview.findViewById(R.id.btn_sendSuggestion);
		 btn_sendSuggestion.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i("TestSend", suggestion_to_send.getText().toString());
				Log.i("TestSend", type);
				Log.i("TestSend", MainActivity.myAccount);
				addSuggestionToServer();
			}
		 });
		 btn_cancelSendSuggestion=(Button) rootview.findViewById(R.id.btn_cancelSendSugggestion);
		 btn_cancelSendSuggestion.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					FragmentManager fragmentManager = getFragmentManager();
					Fragment fragment =new SetUpMain();
					FragmentTransaction transaction = fragmentManager.beginTransaction();
					if (fragment !=null){
						transaction.replace(R.id.container, fragment);
						transaction.commit();
					}
				}
			 });
		 Log.i("TestMyAccount",MainActivity.myAccount);
		 return rootview;
	 }
	
	private void addSuggestionToServer(){
		Runnable runnable = new Runnable(){
		    @Override
		    public void run() {
				HttpClient hc = new DefaultHttpClient();
				HttpPost post = new HttpPost(MySources.addSuggestionUrl);
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
				String str3=new String();
				try {
					str1 = URLEncoder.encode(suggestion_to_send.getText().toString(), "UTF-8");
					str2 = URLEncoder.encode(type, "UTF-8");
					str3 = URLEncoder.encode(MainActivity.myAccount, "UTF-8");
				} catch (UnsupportedEncodingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				NameValuePair pair1 = new BasicNameValuePair("content",str1);
				NameValuePair pair2 = new BasicNameValuePair("type",str2);
				NameValuePair pair3 = new BasicNameValuePair("account",str3);
				List<NameValuePair> listParams = new ArrayList<NameValuePair>();
				listParams.add(pair1);
				listParams.add(pair2);
				listParams.add(pair3);
				
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
				    	result = EntityUtils.toString(res.getEntity());	
				    	Message msg = new Message();
				    	msg.what=MySources.REFRESH;
				    	mHandler.sendMessage(msg);
				    }
				}catch(Exception e){
					e.printStackTrace();
					Log.i("myTest", "添加失败失败");
				}finally{
					hc.getConnectionManager().shutdown();
				}
		    }
		  };
		  new Thread(runnable).start();
	} 
	
}
