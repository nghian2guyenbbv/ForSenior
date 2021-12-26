package com.javabrain.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.javabrain.component.MyDate;

@Path("date/{dateValue}")
public class MyResource {
@GET
@Produces(MediaType.APPLICATION_JSON)
public String hello(@PathParam("dateValue")MyDate mydate) {
	return "Return:"+mydate.toString();
}
}
// Mydate will be convert by MyDataConverterProvider