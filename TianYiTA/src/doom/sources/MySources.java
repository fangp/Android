package doom.sources;


public class MySources {
	public static String hostUrl = "192.168.23.133";
	public static String myLoginUrl = "http://"+hostUrl+":8080/Doom/user/login";
	public static String getAllSuggestionUrl = "http://"+hostUrl+":8080/Doom/user/getAllSuggestion";
	public static String addSuggestionUrl = "http://"+hostUrl+":8080/Doom/user/addSuggestion";
	public static String customerActionUrl = "http://"+hostUrl+":8080/Doom/user/customerAction";
	public static String getAllAppUrl = "http://"+hostUrl+":8080/Doom/user/getAllApp";
	public static String getAllInfoUrl = "http://"+hostUrl+":8080/Doom/user/getAllInfo";
	public static String modifyInfoUrl = "http://"+hostUrl+":8080/Doom/user/modifyInfo";
	public static String getAllBusiness = "http://"+hostUrl+":8080/Doom/user/getAllBusiness";
	public static final int REFRESH=0x000001;
}
