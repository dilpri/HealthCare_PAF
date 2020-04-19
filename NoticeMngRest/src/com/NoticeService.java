package com;

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
import org.jsoup.parser.Parser;

import org.jsoup.nodes.Document;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Notice;

@Path("/notices")

public class NoticeService {

	Notice nObj = new Notice();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readNotices() {
		return nObj.readNotices();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertNotice(
			
			@FormParam("noticeType") String noticeType, 
			@FormParam("noticeDesc") String noticeDesc
			
			) {
		String output = nObj.insertNotice( noticeType,noticeDesc);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateNotice(String noticeData) {
		// Convert the input string to a JSON object
		JsonObject docObject = new JsonParser().parse(noticeData).getAsJsonObject();
		// Read the values from the JSON object
		String noticeID = docObject.get("noticeID").getAsString();
		String noticeType = docObject.get("noticeType").getAsString();
		String noticeDesc = docObject.get("noticeDesc").getAsString();

		String output = nObj.updateNotice(noticeID, noticeType, noticeDesc);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteNotice(String noticeData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(noticeData, "", Parser.xmlParser());
		// Read the value from the element <userID>
		String noticeID = doc.select("noticeID").text();
		String output = nObj.deleteNotice(noticeID);
		return output;
	}

}
