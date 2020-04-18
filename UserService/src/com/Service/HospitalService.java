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

import com.controller.Hospital;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Hospitals")
public class HospitalService {

	Hospital hosObj = new Hospital();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readHospitals() {
		return hosObj.readHospitals();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospital(@FormParam("hosName") String hosName, @FormParam("location") String location,
			@FormParam("email") String email) {
		String output = hosObj.insertHospital(hosName, location, email);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospital(String hospitalData) {
		// Convert the input string to a JSON object
		JsonObject hosObject = new JsonParser().parse(hospitalData).getAsJsonObject();
		// Read the values from the JSON object
		String hospitalID = hosObject.get("hospitalID").getAsString();
		String hosName = hosObject.get("hosName").getAsString();
		String location = hosObject.get("location").getAsString();
		String email = hosObject.get("email").getAsString();
		String output = hosObj.updateHospital(hospitalID, hosName, location, email);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(String hospitalData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(hospitalData, "", Parser.xmlParser());
		// Read the value from the element <userID>
		String hospitalID = doc.select("hospitalID").text();
		String output = hosObj.deleteHospital(hospitalID);
		return output;
	}
}
