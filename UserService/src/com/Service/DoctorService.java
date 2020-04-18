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

import com.controller.Doctor;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Doctors")
public class DoctorService {
	Doctor docObj = new Doctor();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctors() {
		return docObj.readDoctors();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(@FormParam("doctorName") String doctorName, @FormParam("NIC") String NIC,
			@FormParam("specialization") String specialization, @FormParam("hospitalID") String hospitalID,
			@FormParam("email") String email, @FormParam("mobileNo") String mobileNo) {
		String output = docObj.insertDoctor(doctorName, NIC, specialization, hospitalID, email, mobileNo);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(String doctorData) {
		// Convert the input string to a JSON object
		JsonObject docObject = new JsonParser().parse(doctorData).getAsJsonObject();
		// Read the values from the JSON object
		String doctorID = docObject.get("doctorID").getAsString();
		String doctorName = docObject.get("doctorName").getAsString();
		String NIC = docObject.get("NIC").getAsString();
		String specialization = docObject.get("specialization").getAsString();
		String hospitalID = docObject.get("hospitalID").getAsString();
		String email = docObject.get("email").getAsString();
		String mobileNo = docObject.get("mobileNo").getAsString();
		String output = docObj.updateDoctor(doctorID, doctorName, NIC, specialization, hospitalID, email, mobileNo);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctor(String doctorData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(doctorData, "", Parser.xmlParser());
		// Read the value from the element <userID>
		String doctorID = doc.select("doctorID").text();
		String output = docObj.deleteDoctor(doctorID);
		return output;
	}

}
