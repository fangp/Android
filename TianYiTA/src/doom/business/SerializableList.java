package doom.business;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SerializableList implements Serializable{
	private List<String> list;
	
	public List<String> getList(){
		return list;
		
	}
	public void setMap(List<String> list){
		this.list=list;
	}

}
