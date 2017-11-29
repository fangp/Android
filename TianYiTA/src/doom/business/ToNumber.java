package doom.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.tianyita.R;
import com.example.tianyita.R.id;
import com.example.tianyita.R.layout;

import doom.sources.MsgConfigService;



import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ToNumber extends Activity {
	
	private Map<String,String> mapp;
	private TextView txt;
	private Context context;
	private String str;
	private Button add_btn;
	private Button send_btn;
	private ListView lv2;
	private EditText input;
	private List<String> listt = null;
	private List<String> number = null;
	private String inputnumber=null;
    private SimpleAdapter simpleAdapter = null;
    private ArrayAdapter<String> adapter=null;
    private Map<String,Boolean> selectedMap;
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this.getApplicationContext();
		number = new ArrayList<String>();
		setContentView(R.layout.activity_tonumber);
		add_btn = (Button)findViewById(R.id.add_custom_btn);
		send_btn = (Button)findViewById(R.id.send_custom_btn);
		Message();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,number);
		lv2 = (ListView)findViewById(R.id.lv2);
		lv2.setAdapter(adapter);
		lv2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lv2.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				number.remove(arg2);
				adapter.notifyDataSetChanged();
				lv2.invalidate();
				return true;
			}
			
		});
	   
		add_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				inputTitleDialog();
				}
			
		});
		
		send_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//String strContent = str;  
                SmsManager smsManager = SmsManager.getDefault();  
                PendingIntent sentIntent = PendingIntent.getBroadcast(ToNumber.this, 0, new Intent(), 0);  
                  
                // 过长短信分割 
//                if (strContent.length() > 70) {  
//                    List<String> msgs = smsManager.divideMessage(strContent);  
//                    for (String msg : msgs) {  
//                        smsManager.sendTextMessage(strNo, null, msg, sentIntent, null);  
//                    }  
//                } else {  
//                    smsManager.sendTextMessage(strNo, null, strContent, sentIntent, null);  
//                }  
                
                for(int i=0;i<number.size();i++){  
                	
                	for(int j=0;j<listt.size();j++)
                	{
                        if (listt.get(j).length() > 70) {  
                            List<String> msgs = smsManager.divideMessage(listt.get(j));  
                            for (String msg : msgs) {  
                                smsManager.sendTextMessage(number.get(i), null, msg, sentIntent, null);  
                           }  
                        } else {  
                            smsManager.sendTextMessage(number.get(i), null, listt.get(j), sentIntent, null);  
                        }     
                    }  
                }
                Toast.makeText(ToNumber.this, "短信发送成功", Toast.LENGTH_LONG).show(); 
                ToNumber.this.finish();
			}
			
		});
		
	}
	private String Message(){
		Intent intent = this.getIntent();
		SerializableList list = (SerializableList)intent.getSerializableExtra("selectedBus");
		listt = list.getList();
		Toast.makeText(context, str, 0).show();
		return str;
	}
	private void inputTitleDialog() {

        input = new EditText(this);
        input.setFocusable(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("添加号码").setIcon(
                null).setView(input).setNegativeButton(
                "取消", null);
        builder.setPositiveButton(("确认"),
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        inputnumber = input.getText().toString();
                        //Log.v("test", inputnumber);
                        if(inputnumber=="")
                        {
                        	
                        }
                        else
                        {
                        number.add(inputnumber);
        			    adapter.notifyDataSetChanged();
                        }
                    }
                });
        builder.show();
    }
}
