package doom.readinfo;

import com.example.tianyita.R;
import com.example.tianyita.R.layout;
import com.example.tianyita.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FragmentInfoContent extends Activity {

	
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_content);
		tv=(TextView) findViewById(R.id.tv_info_content);
		Intent intent=this.getIntent();
		tv.setText(intent.getStringExtra("content"));
	}

	public void back(View view){
		Intent intent = new Intent();
		intent.putExtra("resultValue", "I is ReturnValue");
		this.setResult(200, intent);//设置要返回的数据
		this.finish();//关闭当前窗口
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_info_content, menu);
		return true;
	}

}
