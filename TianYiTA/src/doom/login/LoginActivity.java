package doom.login;

import java.util.ArrayList;
import java.util.List;

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
import org.json.JSONObject;

import com.example.tianyita.R;

import doom.main.MainActivity;
import doom.sources.MySources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText account;
	EditText password;
	private String result;
	Handler mHandler;
	TextView resultView;
	Context context;
	CheckBox auto_login;
	CheckBox remePass;
	Button btn_login;
	
	SharedPreferences sp;
	SharedPreferences.Editor spe;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		account = (EditText) findViewById(R.id.account);
		password = (EditText) findViewById(R.id.password);
		context= this.getApplicationContext();
		auto_login=(CheckBox) findViewById(R.id.autoLogin);
		remePass=(CheckBox) findViewById(R.id.remePass);
		btn_login=(Button) findViewById(R.id.btn_login);
		auto_login.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(auto_login.isChecked())
					remePass.setChecked(true);
			}		
		});
		remePass.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(auto_login.isChecked()&&!remePass.isChecked())
					auto_login.setChecked(false);
			}
			
		});
		
		sp=getSharedPreferences("doom_account",Context.MODE_WORLD_READABLE);
		spe=sp.edit();
		String myAccount=sp.getString("account", null);
		String myPassword=sp.getString("password", null);
		if(myAccount!=null&&myPassword!=null){
			remePass.setChecked(true);
			account.setText(myAccount);
			password.setText(myPassword);
		}
		else
			remePass.setChecked(false);
		//Intent intent=this.getIntent();
		//String s=intent.getStringExtra("logout");
		//spe.putString("logout", s);
		if(sp.getInt("auto", 0)==0)
			;
		else if(sp.getInt("auto",0)==1){
			login(this.getCurrentFocus());
			auto_login.setChecked(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	

	public void login(View view){
		final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);  
		String acc = account.getText().toString();
		String pwd = password.getText().toString();
		if(!acc.equals("")&&!pwd.equals("")){
			progressDialog.setMessage("正在登陆");  
	        progressDialog.setTitle("请稍候"); 
	        progressDialog.show();
			loginToServer(acc,pwd);
		}
		else
			Toast.makeText(context, "账号密码不能为空！", Toast.LENGTH_SHORT).show();
		mHandler=new Handler(){
			@Override
			public void handleMessage(Message msg){
				switch(Integer.valueOf(result)){
					case 1:
						Toast.makeText(context, "登陆成功！", Toast.LENGTH_SHORT).show();
						if(remePass.isChecked()){
							spe.putString("account", account.getText().toString());
							spe.putString("password", password.getText().toString());
							spe.commit();
						}else{
							spe.putString("account", null);
							spe.putString("password", null);
							spe.commit();
						}
						if(auto_login.isChecked()){
							spe.putInt("auto", 1);
							spe.commit();
						}
						else{
							spe.putInt("auto", 0);
							spe.commit();
						}
						openMainActivity();
						progressDialog.dismiss();
						break;
					case 2:
						Toast.makeText(context, "账号密码错误！", Toast.LENGTH_SHORT).show();
						progressDialog.dismiss();
						remePass.setChecked(false);
						auto_login.setChecked(false);
						break;
				}
				super.handleMessage(msg);
			}
		};
	}
	

	private void openMainActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(LoginActivity.this, MainActivity.class);
		intent.putExtra("user",account.getText().toString()); //给新窗口用户名
		this.startActivity(intent);
		this.finish();
		//this.startActivityForResult(intent, 100);
	}
	
	//采用post方式发送数据
	private void loginToServer(final String acc,final String pwd){
		Runnable runnable = new Runnable(){
		    @Override
		    public void run() {
				HttpClient hc = new DefaultHttpClient();
				HttpPost post = new HttpPost(MySources.myLoginUrl);
				Header[] headers=new BasicHeader[6];
				headers[0]=new BasicHeader("Accept","application/x-shockwave-flash,image/gif,*/*");
				headers[1] = new BasicHeader("Accept-Language","zh-cn");
				headers[2] = new BasicHeader("Host","10.0.2.2");
				headers[3] = new BasicHeader("Connection", "keep-Alive");
				headers[4] = new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
				headers[5] = new BasicHeader("Cookie","JSESSIONID=ABC57456441F469CE68B0FDD8C2B4EDF");
				post.setHeaders(headers);
				NameValuePair pair1 = new BasicNameValuePair("username",acc);
				NameValuePair pair2 = new BasicNameValuePair("password",pwd);
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
				    	String strJson = EntityUtils.toString(res.getEntity());
				    	JSONObject jo = new JSONObject(strJson);
				    	result = jo.getString("result");
				    	mHandler.sendEmptyMessage(0);
				    	Log.i("myTest", "登陆完成");
				    }
				}catch(Exception e){
					e.printStackTrace();
					Log.i("myTest", "登陆失败");
				}finally{
					hc.getConnectionManager().shutdown();
				}
		    }
		  };
		  new Thread(runnable).start();
	}
	
	private long firstTime = 0; 
    public boolean onKeyUp(int keyCode, KeyEvent event) {  
        // TODO Auto-generated method stub  
        switch(keyCode)  
        {  
        case KeyEvent.KEYCODE_BACK:  
             long secondTime = System.currentTimeMillis();   
              if (secondTime - firstTime > 2000) {                                         //如果两次按键时间间隔大于2秒，则不退出  
                  Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();   
                  firstTime = secondTime;//更新firstTime  
                  return true;   
              } else {                                                    //两次按键小于2秒时，退出应用  
            	  System.exit(0);  
              }   
            break;  
        }  
      return super.onKeyUp(keyCode, event);  
    }
	
}
