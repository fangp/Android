package doom.customer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

import com.example.tianyita.R;

import doom.main.MainActivity;
import doom.sources.MySources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class NewCustomerActivity extends Activity
{
    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);

        mHandler=new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                if(msg.what==1)
                {
                    Toast.makeText(getApplicationContext(), "��ӳɹ���",Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
                    NewCustomerActivity.this.setResult(0,intent);
                    NewCustomerActivity.this.finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "���ʧ�ܣ�",Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };

    }

    public void clickHandler_Complete(View v)
    {
        EditText editName = (EditText) findViewById(R.id.editCName);
        EditText editCPhoneModel = (EditText) findViewById(R.id.editCPhoneModel);
        EditText editPhoneNumber1 = (EditText) findViewById(R.id.editCPhoneNumber1);
        EditText editPhoneNumber2 = (EditText) findViewById(R.id.editCPhoneNumber2);
        EditText editPhoneNumber3 = (EditText) findViewById(R.id.editCPhoneNumber3);
        if(editName.getText().equals(""))
            Toast.makeText(getApplicationContext(), "��������Ϊ�գ�", Toast.LENGTH_SHORT).show();
        else if(editPhoneNumber1.getText().equals(""))
            Toast.makeText(getApplicationContext(), "�ֻ����벻��Ϊ�գ�", Toast.LENGTH_SHORT).show();
        else
        {
            if(editCPhoneModel.getText().length()==0)
                editCPhoneModel.setText("default");
            System.out.println(editCPhoneModel.getText());
            SendtoServer(editName.getText().toString(), editPhoneNumber1.getText().toString(), editPhoneNumber2.getText().toString(),
                    editPhoneNumber3.getText().toString(), editCPhoneModel.getText().toString());
        }
    }

    public void clickHandler_Cancel(View v)
    {
        Intent intent = getIntent();
        NewCustomerActivity.this.setResult(0,intent);
        NewCustomerActivity.this.finish();
    }

    private void SendtoServer(final String Name, final String Phone_num1, final String Phone_num2, final String Phone_num3, final String Phone_model)
    {
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                HttpClient hc = new DefaultHttpClient();
                HttpPost post = new HttpPost(MySources.customerActionUrl);
                Header[] headers=new BasicHeader[6];
                headers[0]=new BasicHeader("Accept","application/x-shockwave-flash,image/gif,*/*");
                headers[1] = new BasicHeader("Accept-Language","zh-cn");
                headers[2] = new BasicHeader("Host","10.0.2.2");
                headers[3] = new BasicHeader("Connection", "keep-Alive");
                headers[4] = new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                headers[5] = new BasicHeader("Cookie","JSESSIONID=ABC57456441F469CE68B0FDD8C2B4EDF");
                post.setHeaders(headers);
                
                String str1=new String();
				String str2=new String();
				String str3=new String();
				String str4=new String();
				String str5=new String();
				String str6=new String();
				try {
					str1 = URLEncoder.encode(Name, "UTF-8");
					str2 = URLEncoder.encode(Phone_num1, "UTF-8");
					str3 = URLEncoder.encode(Phone_num2, "UTF-8");
					str4 = URLEncoder.encode(Phone_num3, "UTF-8");
					str5 = URLEncoder.encode(Phone_model, "UTF-8");
					str6 = URLEncoder.encode(MainActivity.myAccount, "UTF-8");
				} catch (UnsupportedEncodingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
                NameValuePair pair1 = new BasicNameValuePair("name",str1);
                NameValuePair pair2 = new BasicNameValuePair("phone_num1",str2);
                NameValuePair pair3 = new BasicNameValuePair("phone_num2",str3);
                NameValuePair pair4 = new BasicNameValuePair("phone_num3",str4);
                NameValuePair pair5 = new BasicNameValuePair("phone_model",str5);
                NameValuePair pair6 = new BasicNameValuePair("userName",str6);
                List<NameValuePair> listParams = new ArrayList<NameValuePair>();
                listParams.add(pair1);
                listParams.add(pair2);
                listParams.add(pair3);
                listParams.add(pair4);
                listParams.add(pair5);
                listParams.add(pair6);
                HttpEntity requestHttpEntity;
                try
                {
                    requestHttpEntity = new UrlEncodedFormEntity(listParams);
                    post.setEntity(requestHttpEntity);
                }
                catch (UnsupportedEncodingException e1)
                {
                    e1.printStackTrace();
                }
                HttpResponse res;
                try
                {
                    Message msg = new Message();
                    res = hc.execute(post);  // �������󵽷�����
                    if(res.getStatusLine().getStatusCode() == 200) // �ж�״̬���Ƿ�Ϊ200��200��������ȷ�����ݷ���
                    {
                        // �ѷ���ֵ������ת�����ַ���
                        String strJson = EntityUtils.toString(res.getEntity());
                        // �������ڷ���ֵ��һ��json��ʽ���ַ��������������ǽ���json�е�ֵ
                        //ja=new JSONArray(strJson);
                        if(strJson.equals(""))
                        {
                            msg.what=1;
                            mHandler.sendMessage(msg);
                        }
                        else
                        {
                            msg.what=2;
                            mHandler.sendMessage(msg);
                        }
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    hc.getConnectionManager().shutdown();
                }
            }
        };
        new Thread(runnable).start();
    }

}
