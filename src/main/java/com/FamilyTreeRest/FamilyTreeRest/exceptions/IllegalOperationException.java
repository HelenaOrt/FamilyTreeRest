/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.FamilyTreeRest.FamilyTreeRest.exceptions;

import com.FamilyTreeRest.FamilyTreeRest.exceptions.generic.ConflictException;

public class IllegalOperationException extends ConflictException {

	public IllegalOperationException(String s) {
		super(s);
	}
}
