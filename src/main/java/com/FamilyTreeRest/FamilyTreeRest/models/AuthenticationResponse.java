/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.FamilyTreeRest.FamilyTreeRest.models;

public class AuthenticationResponse {

	private String token;

	public AuthenticationResponse() {

	}

	public AuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
