/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.FamilyTreeRest.FamilyTreeRest.exceptions.generic;

public class ErrorModel {

	private String error;

	public static ErrorModel of(String errorMessage) {
		ErrorModel errorModel = new ErrorModel();
		errorModel.error = errorMessage;
		return errorModel;
	}

	public String getError() {
		return error;
	}

}
