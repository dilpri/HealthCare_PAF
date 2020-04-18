package com;

import model.Appointment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Appointment")
public class AppointmentService {
	Appointment appointmentObj = new Appointment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointment() 
	{
		return appointmentObj.readAppointment();
	}
	
	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppoinment(
			@FormParam("appointmentDate") String appointmentDate, 
			@FormParam("appointmentTime") String appointmentTime,
			@FormParam("appointmentDoctor") String appointmentDoctor, 
			@FormParam("appointmentHospital") String appointmentHospital
			) {
		String output = appointmentObj.insertAppointment(appointmentDate,appointmentTime,appointmentDoctor,appointmentHospital);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String appointmentData) {
		// Convert the input string to a JSON object
		JsonObject appoinmentObject = new JsonParser().parse(appointmentData).getAsJsonObject();
		// Read the values from the JSON object
		String appointmentID = appoinmentObject.get("appointmentID").getAsString();
		String appointmentDate = appoinmentObject.get("appointmentDate").getAsString();
		String appointmentTime = appoinmentObject.get("appointmentTime").getAsString();
		String appointmentDoctor = appoinmentObject.get("appointmentDoctor").getAsString();
		String appointmentHospital = appoinmentObject.get("appointmentHospital").getAsString();
	
		String output = appointmentObj.updateAppointment(appointmentID, appointmentDate,appointmentTime,appointmentDoctor,appointmentHospital);
		return output;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String appointmentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(appointmentData, "", Parser.xmlParser());
		// Read the value from the element <itemID>
		String appointmentID = doc.select("appointmentID").text();
		String output = appointmentObj.deleteAppointment(appointmentID);
		return output;
	}
}



