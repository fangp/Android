package doom.setup;

import com.example.tianyita.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class SetUpMain extends Fragment {
	
	TextView btn_checkSuggestion;
	TextView btn_addSuggestion;
	TextView btn_Greeting;
	TextView btn_myInfo;
	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	 {
	        // Inflate the layout for this fragment
		 View rootview = inflater.inflate(R.layout.fragement_setup_main, container, false);   
		 btn_checkSuggestion=(TextView)rootview.findViewById(R.id.btn_checkSuggestion);
		 btn_addSuggestion=(TextView)rootview.findViewById(R.id.btn_addSuggestion);
		 btn_Greeting=(TextView)rootview.findViewById(R.id.btn_lookupGreeting);
		 btn_myInfo=(TextView)rootview.findViewById(R.id.btn_myInfo);
		 return rootview;
	 }
	 
	 public void onActivityCreated(Bundle savedInstanceState)
	 {
		 super.onActivityCreated(savedInstanceState);
		 btn_myInfo.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				FragmentManager fragmentManager = getFragmentManager();
				Fragment fragment =new MyInfo();
				FragmentTransaction transaction = fragmentManager.beginTransaction();
				if (fragment !=null){
					transaction.replace(R.id.container, fragment);
					transaction.addToBackStack(null);
					transaction.commit();
				}
			} 
		 });
		 btn_checkSuggestion.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				FragmentManager fragmentManager = getFragmentManager();
				Fragment fragment =new CheckSuggestion();
				FragmentTransaction transaction = fragmentManager.beginTransaction();
				if (fragment !=null){
					transaction.replace(R.id.container, fragment);
					transaction.addToBackStack(null);
					transaction.commit();
				}
			}
		 });
		 btn_addSuggestion.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				FragmentManager fragmentManager = getFragmentManager();
				Fragment fragment =new AddSuggestion();
				FragmentTransaction transaction = fragmentManager.beginTransaction();
				if (fragment !=null){
					transaction.replace(R.id.container, fragment);
					transaction.addToBackStack(null);
					transaction.commit();
				}
			}
		 });
		 btn_Greeting.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					FragmentManager fragmentManager = getFragmentManager();
					Fragment fragment =new MsgConfig();
					FragmentTransaction transaction = fragmentManager.beginTransaction();
					if (fragment !=null){
						transaction.replace(R.id.container, fragment);
						transaction.addToBackStack(null);
						transaction.commit();
					}
				}
			 });
	 }
	 
}
