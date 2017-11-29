package doom.favorite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tianyita.R;

import doom.apppush.AppSelect;
import doom.apppush.SerializableMap;
import doom.apppush.ToCustomerActivity;
import doom.apppush.AppSelect.ViewHolder;
import doom.apppush.ToNumberActivity;
import doom.sources.FavoriteService;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FavoriteFragment extends Fragment {
	
	private ListView lv3;
	private Button submit_btn;
	private Button push_btn;
	private Button delete_btn;
	private Context context;
	private Map<String,String> selectedApp;
	private FavorActivity adapter;
	private List<Map<String, Object>> mData;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
		View rootview = inflater.inflate(R.layout.fragment_favorite, container, false);
		context = this.getActivity();
		mData = getData();
		submit_btn = (Button)rootview.findViewById(R.id.favor_submit_btn);
		push_btn = (Button)rootview.findViewById(R.id.favor_push_btn);
		delete_btn = (Button)rootview.findViewById(R.id.favor_delete_btn);
		lv3 = (ListView)rootview.findViewById(R.id.lv3);
		adapter = new FavorActivity(this.getActivity());
		lv3.setAdapter(adapter);
		lv3.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ViewHolder holder = (ViewHolder)arg1.getTag();
				holder.checkbox.toggle();
				mData.get(arg2).put("position", holder.checkbox.isChecked());
				
			}
        	
        });
		submit_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectedApp = new HashMap<String,String>();
				for(int i=0;i<mData.size();i++)
				{
					if((Boolean)(mData.get(i).get("position"))==true)
						selectedApp.put(String.valueOf(mData.get(i).get("title")), String.valueOf(mData.get(i).get("info")));
				}
				Log.v("size", String.valueOf(selectedApp.size()));
				SerializableMap tmpmap=new SerializableMap();  
	            tmpmap.setMap(selectedApp);   
	            Intent intent = new Intent();
	            intent.setClass(context, ToNumberActivity.class);
				intent.putExtra("selectedApp", tmpmap);
				startActivity(intent);
				//FavoriteFragment.this.getActivity().finish();
			}
			
		});
		push_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectedApp = new HashMap<String,String>();
				for(int i=0;i<mData.size();i++)
				{
					if((Boolean)(mData.get(i).get("position"))==true)
						selectedApp.put(String.valueOf(mData.get(i).get("title")), String.valueOf(mData.get(i).get("info")));
				}
				Log.v("size", String.valueOf(selectedApp.size()));
				SerializableMap tmpmap=new SerializableMap();  
	            tmpmap.setMap(selectedApp);   
	            Intent intent = new Intent();
	            intent.setClass(context, ToCustomerActivity.class);
				intent.putExtra("selectedApp", tmpmap);
				startActivity(intent);
				//FavoriteFragment.this.getActivity().finish();
			}
			
		});
		delete_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FavoriteService favoriteservice = new FavoriteService(context);
				List<Map<String,Object>> del = new ArrayList<Map<String,Object>>();
				List<Map<String,Object>> upd = new ArrayList<Map<String,Object>>();
				Map<String,Object> map = new HashMap<String,Object>();
				for(int i=0;i<mData.size();i++)
				{
					
					if((Boolean)mData.get(i).get("position")==false)
					{
						map = new HashMap<String,Object>();
						map = mData.get(i);
						upd.add(map);
						Log.v("up pos", String.valueOf(i));
					}
					else
					{
						map = new HashMap<String,Object>();
						map = mData.get(i);
						del.add(map);
						Log.v("del pos", String.valueOf(i));
					}
				}
				mData = upd;
				Log.v("up", String.valueOf(upd.size()));
				Log.v("del", String.valueOf(del.size()));
				favoriteservice.DeleteFavor(del);
				adapter.notifyDataSetChanged();
			}
			
		});
		return rootview;
	}
	 private List<Map<String, Object>> getData() {
		 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 FavoriteService favoriteservice = new FavoriteService(this.getActivity());
		 list = favoriteservice.ListFavorite();
		 return list;
	 }
	 public final class ViewHolder{
		    public ImageView img;
			public TextView title;
			public TextView info;
			public CheckBox checkbox;
	     }
	 public class FavorActivity extends BaseAdapter{
		    private LayoutInflater mInflater;
		    
		    
			public FavorActivity(Context context){
			   this.mInflater = LayoutInflater.from(context);
			   init();
		   }
			public void init(){
				for(int i=0;i<mData.size();i++)
				{
					mData.get(i).put("position", false);
				}
			}
			@Override
			public int getCount() {
			   // TODO Auto-generated method stub
			   return mData.size();
		   }
			      
			@Override
	        public Object getItem(int arg0) {
			   // TODO Auto-generated method stub
			   return null;
		   }
			      
			@Override
		    public long getItemId(int arg0) {
			   // TODO Auto-generated method stub
			   return 0;
		   }
		    @Override
		    public View getView(final int position, View convertView, ViewGroup parent) {
		    	
	            
				ViewHolder holder = null;
				
				if (convertView == null) {
				                      
				   holder=new ViewHolder(); 
				   convertView = mInflater.inflate(R.layout.appunit, null);
				   holder.img = (ImageView)convertView.findViewById(R.id.img);
				   holder.title = (TextView)convertView.findViewById(R.id.title);
				   holder.info = (TextView)convertView.findViewById(R.id.info);
				   holder.checkbox = (CheckBox)convertView.findViewById(R.id.check_box);
				   convertView.setTag(holder);
			
				                      
				   }else {
				                      
				        holder = (ViewHolder)convertView.getTag();
				       
				        
				     }
				                   
				   holder.img.setImageBitmap((Bitmap) mData.get(position).get("img"));              
				   //holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
				   holder.title.setText((String)mData.get(position).get("title"));
				   holder.info.setText((String)mData.get(position).get("info"));
				   holder.checkbox.setChecked((Boolean) mData.get(position).get("position"));
				  
				                  
				                  
				                 return convertView;
			}
		 }
}
