package com.n11.usermanager.marshaller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;

import com.n11.usermanager.domain.User;

@SuppressWarnings("unchecked")
public class UserMarshaller {
	
	public static JSONObject toJsonObject(User user) {
		JSONObject result = new JSONObject();
		result.put("id", user.getId());
		result.put("name", user.getName());
		result.put("surname", user.getSurname());
		result.put("phone", user.getPhone());
    	System.out.println("user is marshalled: "+result.toJSONString());
		return result;
	}
	
	public static JSONObject toJsonObject(Page<User> users) {
		JSONObject result = new JSONObject();
		result.put("data", toJsonObject(users.getContent()));
		result.put("totalPages", users.getTotalPages());
		return result;
	}
	
	public static JSONObject toJsonObject(List<User> users) {
		JSONObject result = new JSONObject();
		JSONArray data = new JSONArray();
		for(User user : users) {
			data.add(toJsonObject(user));
		}
		result.put("data", data);
		return result;
	}

}
