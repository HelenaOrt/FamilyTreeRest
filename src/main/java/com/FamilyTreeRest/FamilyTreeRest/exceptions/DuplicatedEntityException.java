/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.FamilyTreeRest.FamilyTreeRest.exceptions;

import com.FamilyTreeRest.FamilyTreeRest.exceptions.generic.ConflictException;

public class DuplicatedEntityException extends ConflictException {

	public DuplicatedEntityException() {
		super("Duplicated entity");
	}
}
