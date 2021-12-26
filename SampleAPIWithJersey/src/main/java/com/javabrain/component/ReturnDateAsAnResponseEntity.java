package com.javabrain.component;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("date/today")
public class ReturnDateAsAnResponseEntity {
@GET
@Produces(MediaType.APPLICATION_JSON)
public Date getToday() {
	return Calendar.getInstance().getTime();
}
}