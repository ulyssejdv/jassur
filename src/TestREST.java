import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jassur.message.Message;
import com.jassur.message.RequestBuilder;
import com.jassur.model.Client;

public class TestREST {

	public static void main(String[] args) {
		
		Message message = new Message();
		
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "clients/");
		
		String rep = message.execRequest(rb.toJSONString());
		
		JSONParser parser = new JSONParser();
		
		Object obj;
		
		try {
			obj = parser.parse(rep);
			JSONArray jArray = (JSONArray)obj;
			ArrayList<Client> clientList = new ArrayList<Client>();
			for(int i = 0; i < jArray.size(); i++) {
				JSONObject job = (JSONObject)jArray.get(i);				
				Client c = new Client();
				c.parseJSON(job);
				clientList.add(c);
			}
			
			
			for (Client client : clientList) {
				System.out.println(client.getFirstName()+" : "+client.getLastName());
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
