package doom.business;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.tianyita.R.id;
import com.example.tianyita.R.layout;

import doom.apppush.AppSelect;
import doom.apppush.SerializableMap;
import doom.apppush.ToCustomerActivity;
import doom.apppush.ToNumberActivity;
import doom.customer.CustomerManage;
import doom.sources.MySources;


public class FragmentBusiness extends Fragment
{
    List<String> group;           //组列表
    List<List<String>> child;     //子列表
    List<String> recommend;       //推荐列表
    private ExpandableListView listview;
    private Context context;
    private Button submit_btn;
    private Button push_btn;

    private int SelectFlag = 0;
    JSONArray ja;
    private Handler mHandler = new Handler(){
		public void handleMessage(Message msg){
			for(int i=0;i<ja.length();i++){
		    	JSONObject jo;
				try {
					jo = ja.getJSONObject(i);
			    	String str1 = jo.getString("b_name");
			    	String str2 = jo.getString("b_content");
			    	ArrayList<String> detail = new ArrayList<String>();
			        group.add(str1);
			        detail.add(str2);
			        child.add(detail);     
				} catch (JSONException e) {
						// TODO Auto-generated catch block
					Toast.makeText(getActivity(), "获得业务出现问题！", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
		    }
			listview.setAdapter(new ContactsInfoAdapter());
		    listview.setCacheColorHint(0);
			Toast.makeText(getActivity(), "业务查询成功！", Toast.LENGTH_SHORT).show();
		}
	};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_business, container, false);
        context = this.getActivity();

        submit_btn = (Button)rootview.findViewById(R.id.bus_number_btn);
        push_btn = (Button)rootview.findViewById(R.id.bus_customer_btn);
        submit_btn.setVisibility(View.INVISIBLE);
        push_btn.setVisibility(View.INVISIBLE);
        
        listview = (ExpandableListView) rootview.findViewById(R.id.expandableListView);
        group = new ArrayList<String>();     //初始化
        child = new ArrayList<List<String>>();
        getBusinessFromSever();
        recommend = new ArrayList<String>();  
        listview.setOnItemLongClickListener(new ItemLongClick());
        listview.setOnChildClickListener(new ItemClick());
        
        submit_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SerializableList list=new SerializableList();  
	            list.setMap(recommend);   
	            Intent intent = new Intent();
	            intent.setClass(context, ToNumber.class);
				intent.putExtra("selectedBus", list);
				startActivity(intent);
				//FragmentBusiness.this.getActivity().finish();
			}
        	
        });
        push_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SerializableList list=new SerializableList();  
	            list.setMap(recommend);   
	            Intent intent = new Intent();
	            intent.setClass(context, ToCustomer.class);
				intent.putExtra("selectedBus", list);
				startActivity(intent);
				//FragmentBusiness.this.getActivity().finish();
				
			}
        	
        });
       /* ArrayList detail = new ArrayList<String>();
        detail.clear();
        group.add("001");
        detail.add("1");
        child.add(detail);
        listview.setAdapter(new ContactsInfoAdapter());
        listview.setCacheColorHint(0);  //设置拖动列表的时候防止出现黑色背景
*/        
        return rootview;
    }

    public void getBusinessFromSever(){
    	Runnable runnable = new Runnable(){
		    @Override
		    public void run() {
				HttpClient hc = new DefaultHttpClient();
				HttpPost post = new HttpPost(MySources.getAllBusiness);
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
				    	mHandler.sendEmptyMessage(0);
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
            TextView text = new TextView(FragmentBusiness.this.getActivity());
            text.setLayoutParams(lp);
            // Center the text vertically
            text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            // Set the text starting position
            if (flag == 1)      //group未选中
            {
                text.setPadding(50, 15, 0, 15);
                if(s.length()>6 && s.substring(0,6).equals("select"))
                {
                    text.setBackgroundColor(Color.rgb(128, 255, 255));
                    s=s.substring(6);
                }
            }
            else if (flag == 0)     //child未选中
            {
                text.setPadding(70, 10, 0, 10);
                if(s.length()>6 && s.substring(0,6).equals("select"))
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
        if(child.get(groupPosition).get(childPosition).toString().length()<6 || !adapter.getChild(groupPosition,childPosition).toString().substring(0,6).equals("select"))
        {
        	submit_btn.setVisibility(View.VISIBLE);
            push_btn.setVisibility(View.VISIBLE);
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
        			if (child.get(groupPosition).get(i).toString().length()>6 && adapter.getChild(groupPosition,i).toString().substring(0,6).equals("select"))
        				fflag++;
        		}
        		if(fflag == 0)
        			group.set(groupPosition, adapter.getGroup(groupPosition).toString().substring(6));
        	}
        	recommend.remove(adapter.getChild(groupPosition, childPosition).toString());
        	SelectFlag--;
        	if(SelectFlag == 0)
            {
        		submit_btn.setVisibility(View.INVISIBLE);
                push_btn.setVisibility(View.INVISIBLE);
            }
        }
        System.out.println(adapter.getGroup(groupPosition) + ":" + adapter.getChild(groupPosition,childPosition));
        listview.collapseGroup(groupPosition);
        listview.expandGroup(groupPosition);
    }


}


