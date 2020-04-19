package com;

import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payments")
public class PaymentService {
	Payment paymentObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayments() {
		return paymentObj.readPayments();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(
			 @FormParam("cardType") String cardType,
	         @FormParam("cardNo") String cardNo,
	         @FormParam("amount") String amount)
	
	{
	 String output = paymentObj.insertPayment( cardType, cardNo, amount);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
	//Convert the input string to a JSON object
	 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
	//Read the values from the JSON object
	 String paymentID = paymentObject.get("paymentID").getAsString();
	 String cardType = paymentObject.get("cardType").getAsString();
	 String cardNo = paymentObject.get("cardNo").getAsString();
	 String amount = paymentObject.get("amount").getAsString();
	 String output = paymentObj.updatePayment(paymentID, cardType, cardNo, amount);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String paymentID = doc.select("paymentID").text();
	 String output = paymentObj.deletePayment(paymentID);
	return output;
	}


}
