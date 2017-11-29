package doom.login;

import java.util.Timer;
import java.util.TimerTask;

import com.example.tianyita.R;

import doom.main.MainActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;

public class LoadingActivity extends Activity {

	
	private Timer timer;
	private int index=0;
	private Intent intent;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			timer.cancel();
			intent = new Intent(LoadingActivity.this,LoginActivity.class);
			startActivity(intent);
			finish();
			super.handleMessage(msg);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				index = index+1;
				handler.sendEmptyMessage(0);
			}
		},1500,1000);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loading, menu);
		return true;
	}

}
