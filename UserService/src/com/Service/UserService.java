package com.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.controller.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Users")
public class UserService {
	User userObj = new User();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers() {
		return userObj.readUsers();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("username") String username, @FormParam("password") String password,
			@FormParam("email") String email, @FormParam("address") String address,
			@FormParam("phoneNo") String phoneNo, @FormParam("age") String age, @FormParam("sex") String sex,
			@FormParam("userType") String userType) {
		String output = userObj.insertUser(username, password, email, address, phoneNo, age, sex, userType);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData) {
		// Convert the input string to a JSON object
		JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		// Read the values from the JSON object
		String userID = userObject.get("userID").getAsString();
		String username = userObject.get("username").getAsString();
		String password = userObject.get("password").getAsString();
		String email = userObject.get("email").getAsString();
		String address = userObject.get("address").getAsString();
		String phoneNo = userObject.get("phoneNo").getAsString();
		String age = userObject.get("age").getAsString();
		String sex = userObject.get("sex").getAsString();
		String userType = userObject.get("userType").getAsString();
		String output = userObj.updateUser(userID, username, password, email, address, phoneNo, age, sex, userType);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
		// Read the value from the element <userID>
		String userID = doc.select("userID").text();
		String output = userObj.deleteUser(userID);
		return output;
	}

}
