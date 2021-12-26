package com.javabrain.filter;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class BasicAuthorizeFilter implements ContainerRequestFilter, ContainerResponseFilter {
	private static final String AUTHORIZATION = "Authorization";
	private static final String BASIC_AUTHORIZATION = "Basic ";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("Header request:" + requestContext.getHeaders());
		List<String> UserAndPass = requestContext.getHeaders().get(AUTHORIZATION);
		if (UserAndPass.size() > 0) {
			String authorizeTokien = UserAndPass.get(0);
			authorizeTokien = authorizeTokien.replaceFirst(BASIC_AUTHORIZATION, "");
			String decode = Base64.decodeAsString(authorizeTokien.getBytes("UTF-8"));
			/*
			 * byte[] decodeByte = Base64.getDecoder().decode(authorizeTokien.getBytes());
			 * String decode = new String(decodeByte);
			 */
			StringTokenizer token = new StringTokenizer(decode, ":");
			String user = token.nextToken();
			String password = token.nextToken();
			if (("nghia".equals(user) && "nguyen".equals(password))) {
				return;
			}
		}
		Response responseFail = Response.status(Response.Status.UNAUTHORIZED).entity("User can't access").build();
		requestContext.abortWith(responseFail);
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Header Reponse: " + responseContext.getHeaders());
	}

}
