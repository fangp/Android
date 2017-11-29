package doom.setup;

import com.example.tianyita.R;

import doom.sources.MsgConfigService;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MsgConfig extends Fragment {

	private Context context;
	private Button msgsave_btn;
	private EditText msgtxt;
	private String msg = null;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	 {
		View rootview = inflater.inflate(R.layout.fragment_msgconfig, container, false);
		context = this.getActivity();
		final MsgConfigService msgconfigservice = new MsgConfigService(this.getActivity());
		msgtxt = (EditText)rootview.findViewById(R.id.msg_add_txt);
		msgtxt.setText(msgconfigservice.ShowMsg());
		msgsave_btn = (Button)rootview.findViewById(R.id.msg_save_btn);
		msgsave_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				msg = msgtxt.getText().toString();
				msgconfigservice.SaveMsg(msg);
				Toast.makeText(context, "±£´æ³É¹¦", 0).show();
			}
			
		});
		return rootview;
	}
}
