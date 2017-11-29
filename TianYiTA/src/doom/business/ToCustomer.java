package doom.business;


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
import org.json.JSONObject;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tianyita.R;
import com.example.tianyita.R.id;
import com.example.tianyita.R.layout;

import doom.apppush.ToNumberActivity;
import doom.login.LoginActivity;
import doom.main.MainActivity;
import doom.sources.MsgConfigService;
import doom.sources.MySources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ToCustomer extends Activity
{
    List<String> group;           //���б�
    List<List<String>> child;     //���б�
    List<String> recommend;       //�Ƽ��б�
    JSONArray ja;
    public static final int REFRESH = 0x000001;
    Handler mHandler;
    private ExpandableListView listview;
    private int SelectFlag = 0;
    private Map<String,String> mapp;
    private Context context;
    private String str=null;
    private List<String> listt=null;
    Button btn_Cancel;
    Button btn_AllCheck;
    Button btn_Recommend;
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this.getApplicationContext();
		setContentView(R.layout.activity_customer);
		btn_Cancel = (Button) findViewById(R.id.Cancel);
        btn_AllCheck = (Button) findViewById(R.id.AllCheck);
        btn_Recommend = (Button) findViewById(R.id.Recommend);

        listview = (ExpandableListView) findViewById(R.id.expandableListView);
        group = new ArrayList<String>();     //��ʼ��
        child = new ArrayList<List<String>>();
        recommend = new ArrayList<String>();
        listview.setOnItemLongClickListener(new ItemLongClick());
        listview.setOnChildClickListener(new ItemClick());

        sendToServer("request_for_data", "request_for_data", null, null, null);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == REFRESH) {
                    listview.setAdapter(new ContactsInfoAdapter());
                    listview.setCacheColorHint(0);  //�����϶��б��ʱ���ֹ���ֺ�ɫ����
                } else {
                    Toast.makeText(context, "���ڴ�ͨѶ¼���룬���Ժ�...", Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };
        
        final ContactsInfoAdapter adapter = new ContactsInfoAdapter();
        btn_Cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                recommend.clear();
                for(int i=0;i<adapter.getGroupCount();i++)
                {
                    if(adapter.getGroup(i).toString().length()>6 && adapter.getGroup(i).toString().substring(0,6).equals("select"))
                    {
                        group.set(i, adapter.getGroup(i).toString().substring(6));
                        for(int k=0;k<adapter.getChildrenCount(i);k++)
                        {
                            if(adapter.getChild(i,k).toString().substring(0,6).equals("select"))
                            {
                                child.get(i).set(k, adapter.getChild(i, k).toString().substring(6));
                                listview.collapseGroup(k);
                                listview.expandGroup(k);
                            }
                        }
                    }
                }
                SelectFlag=0;
            }
        });

        btn_AllCheck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                recommend.clear();
                SelectFlag=0;
                for(int i=0;i<adapter.getGroupCount();i++)
                {
                    for(int k=0;k<adapter.getChildrenCount(i);k++)
                    {
                        if(!child.get(i).get(k).toString().substring(0,4).equals("�ֻ��ͺ�"))
                        {
                            SelectFlag++;
                            if(!adapter.getChild(i,k).toString().substring(0,6).equals("select"))
                            {
                                if((adapter.getGroup(i).toString().length()>6 && !adapter.getGroup(i).toString().substring(0,6).equals("select")) || adapter.getGroup(i).toString().length()<6)
                                    group.set(i, "select" + adapter.getGroup(i));
                                recommend.add(adapter.getChild(i,k).toString());
                                child.get(i).set(k, "select" + adapter.getChild(i, k));
                                listview.collapseGroup(k);
                                listview.expandGroup(k);
                            }
                        }
                    }
                }
                Toast.makeText(context, "��ѡ��" + SelectFlag + "λ��ϵ�ˣ�", Toast.LENGTH_SHORT).show();
            }
        });

        btn_Recommend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //State=3;
            	//String strContent = str;  
                SmsManager smsManager = SmsManager.getDefault();  
                PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);  
                  
                // �������ŷָ� 
//                if (strContent.length() > 70) {  
//                    List<String> msgs = smsManager.divideMessage(strContent);  
//                    for (String msg : msgs) {  
//                        smsManager.sendTextMessage(strNo, null, msg, sentIntent, null);  
//                    }  
//                } else {  
//                    smsManager.sendTextMessage(strNo, null, strContent, sentIntent, null);  
//                }  
                
                for(int i=0;i<recommend.size();i++){  
              
                	for(int j=0;j<listt.size();j++)
                	{
                        if (listt.get(j).length() > 70) {  
                            List<String> msgs = smsManager.divideMessage(listt.get(j));  
                            for (String msg : msgs) {  
                                smsManager.sendTextMessage(recommend.get(i), null, msg, sentIntent, null);  
                            }  
                        } else {  
                            smsManager.sendTextMessage(recommend.get(i), null, listt.get(j), sentIntent, null);  
                        }     
                    }
                }
                Toast.makeText(context, "���ŷ��ͳɹ�", Toast.LENGTH_LONG).show();
                ToCustomer.this.finish();
            }
        });
        MakeMessage();
    }
    private String MakeMessage(){
		Intent intent = this.getIntent();
		SerializableList list = (SerializableList)intent.getSerializableExtra("selectedBus");
		listt = list.getList();
		Toast.makeText(context, str, 0).show();
		return str;
	}
    private void sendToServer(final String name, final String phone_num1, final String phone_num2, final String phone_num3, final String phone_model) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // ����HttpClient
                HttpClient hc = new DefaultHttpClient();
                // ָ��Web URL
                HttpPost post = new HttpPost(MySources.customerActionUrl);
                // ���߷��������ͻ����ܽ��ܵ���������
                Header[] headers = new BasicHeader[6];
                headers[0] = new BasicHeader("Accept", "application/x-shockwave-flash,image/gif,*/*");
                // ���߷��������ͻ����ܽ��ܵ���������
                headers[1] = new BasicHeader("Accept-Language", "zh-cn");
                headers[2] = new BasicHeader("Host", "10.0.2.2");
                // ���߷����������ݴ�����ɺ������������
                headers[3] = new BasicHeader("Connection", "keep-Alive");
                // �������������ͻ��˷��͵���������
                headers[4] = new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                // �ͻ��˷��͸���������Cookieֵ
                headers[5] = new BasicHeader("Cookie", "JSESSIONID=ABC57456441F469CE68B0FDD8C2B4EDF");

                
                String str1=new String();
				String str2=new String();
				String str3=new String();
				String str4=new String();
				String str5=new String();
				String str6=new String();
				try {
					str1 = URLEncoder.encode(name, "UTF-8");
					str2 = URLEncoder.encode(phone_num1, "UTF-8");
					if(phone_num2!=null)
						str3 = URLEncoder.encode(phone_num2, "UTF-8");
					if(phone_num3!=null)
						str4 = URLEncoder.encode(phone_num3, "UTF-8");
					if(phone_model!=null)
					str5 = URLEncoder.encode(phone_model, "UTF-8");
					str6 = URLEncoder.encode(MainActivity.myAccount, "UTF-8");
				} catch (UnsupportedEncodingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
                
                
                // �趨����ͷ����
                post.setHeaders(headers);
                NameValuePair pair1 = new BasicNameValuePair("name", str1);
                NameValuePair pair2 = new BasicNameValuePair("phone_num1", str2);
                NameValuePair pair3 = new BasicNameValuePair("phone_num2", str3);
                NameValuePair pair4 = new BasicNameValuePair("phone_num3", str4);
                NameValuePair pair5 = new BasicNameValuePair("phone_model", str5);
                NameValuePair pair6 = new BasicNameValuePair("userName", str6);
                List<NameValuePair> listParams = new ArrayList<NameValuePair>();
                listParams.add(pair1);
                listParams.add(pair2);
                listParams.add(pair3);
                listParams.add(pair4);
                listParams.add(pair5);
                listParams.add(pair6);

                HttpEntity requestHttpEntity;
                try {
                    requestHttpEntity = new UrlEncodedFormEntity(listParams);
                    post.setEntity(requestHttpEntity);
                } catch (UnsupportedEncodingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                HttpResponse res;
                try {
                    Message msg = new Message();
                    res = hc.execute(post);  // �������󵽷�����
                    if (res.getStatusLine().getStatusCode() == 200) // �ж�״̬���Ƿ�Ϊ200��200��������ȷ�����ݷ���
                    {
                        // �ѷ���ֵ������ת�����ַ���
                        String strJson = EntityUtils.toString(res.getEntity());
                        // �������ڷ���ֵ��һ��json��ʽ���ַ��������������ǽ���json�е�ֵ
                        ja = new JSONArray(strJson);
                        if (ja == null) {
                            msg.what = 111;
                            mHandler.sendMessage(msg);
                        } else {
                            for (int i = 0; i < ja.length(); i++) {
                                group.add(((JSONObject) ja.get(i)).getString("name"));
                                ArrayList detail = new ArrayList<String>();
                                detail.add(((JSONObject) ja.get(i)).getString("phone_num1"));
                                if (!((JSONObject) ja.get(i)).getString("phone_num2").equals("")) {
                                    detail.add(((JSONObject) ja.get(i)).getString("phone_num2"));
                                    if (!((JSONObject) ja.get(i)).getString("phone_num3").equals(""))
                                        detail.add(((JSONObject) ja.get(i)).getString("phone_num3"));
                                }
                                detail.add("�ֻ��ͺ�:" + ((JSONObject) ja.get(i)).getString("phone_model"));
                                child.add(detail);
                            }
                            msg.what = REFRESH;
                            mHandler.sendMessage(msg);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    hc.getConnectionManager().shutdown();
                }
            }
        };
        new Thread(runnable).start();
    }
    
    
    private class ItemLongClick implements ExpandableListView.OnItemLongClickListener
    {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
        {
            if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD)
            {
                long packedPos = ((ExpandableListView) parent).getExpandableListPosition(position);
                int groupPosition = ExpandableListView.getPackedPositionGroup(packedPos);
                int childPosition = ExpandableListView.getPackedPositionChild(packedPos);
                SelectMode(groupPosition,childPosition);
                return true;
            }
            return false;
        }
    }

    private class ItemClick implements ExpandableListView.OnChildClickListener
    {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
        {
            if (SelectFlag != 0)
            {
                SelectMode(groupPosition,childPosition);
                return true;
            }
            return  false;
        }
    }

    private void SelectMode(int groupPosition, int childPosition)
    {
        ContactsInfoAdapter adapter = new ContactsInfoAdapter();
        if(!child.get(groupPosition).get(childPosition).toString().substring(0,4).equals("�ֻ��ͺ�"))
        {
            if(!adapter.getChild(groupPosition,childPosition).toString().substring(0,6).equals("select"))
            {
                recommend.add(adapter.getChild(groupPosition,childPosition).toString());
                child.get(groupPosition).set(childPosition,"select" + adapter.getChild(groupPosition,childPosition));
                if((group.get(groupPosition).toString().length()>6 && !adapter.getGroup(groupPosition).toString().substring(0,6).equals("select")) || group.get(groupPosition).toString().length()<6)
                    group.set(groupPosition, "select" + adapter.getGroup(groupPosition));
                SelectFlag++;
            }
            else
            {
                child.get(groupPosition).set(childPosition,adapter.getChild(groupPosition,childPosition).toString().substring(6));
                if(group.get(groupPosition).toString().length()>6 && adapter.getGroup(groupPosition).toString().substring(0,6).equals("select"))
                {
                    int fflag =0;
                    for (int i = 0; i < adapter.getChildrenCount(groupPosition); i++)
                    {
                        if (adapter.getChild(groupPosition,i).toString().substring(0,6).equals("select"))
                            fflag++;
                    }
                    if(fflag == 0)
                        group.set(groupPosition, adapter.getGroup(groupPosition).toString().substring(6));
                }
                recommend.remove(adapter.getChild(groupPosition, childPosition).toString());
                SelectFlag--;
            }
            System.out.println(adapter.getGroup(groupPosition) + ":" + adapter.getChild(groupPosition,childPosition));
            listview.collapseGroup(groupPosition);
            listview.expandGroup(groupPosition);
            Toast.makeText(context, "��ѡ��" + SelectFlag + "λ��ϵ�ˣ�", Toast.LENGTH_SHORT).show();
        }
    }
    
    
    class ContactsInfoAdapter extends BaseExpandableListAdapter {
        public ContactsInfoAdapter() {

        }

        //--------------Group-------------------------
        @Override
        public int getGroupCount() {
            return group.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return group.get(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String string = group.get(groupPosition);
            return getGenericView(string, 1);
        }
        //------------------Children-----------------------
        @Override
        public int getChildrenCount(int groupPosition) {
            return child.get(groupPosition).size();
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child.get(groupPosition).get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            String string = child.get(groupPosition).get(childPosition);
            return getGenericView(string, 0);
        }

        public TextView getGenericView(String s, int flag)
        {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp;
            if (flag == 1)
                lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 100);
            else
                lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 80);
            TextView text = new TextView(context);
            text.setLayoutParams(lp);
            // Center the text vertically
            text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            // Set the text starting position
            if (flag == 1)      //groupδѡ��
            {
                text.setPadding(50, 15, 0, 15);
                if(s.length()>6 && s.substring(0,6).equals("select"))
                {
                    text.setBackgroundColor(Color.rgb(128, 255, 255));
                    s=s.substring(6);
                }
            }
            else if (flag == 0)     //childδѡ��
            {
                text.setPadding(70, 10, 0, 10);
                if(s.substring(0,6).equals("select"))
                {
                    text.setBackgroundColor(Color.rgb(135, 206, 250));
                    s=s.substring(6);
                }
                else
                    text.setBackgroundColor(Color.WHITE);
            }
            text.setText(s);
            return text;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

    }
   
}
