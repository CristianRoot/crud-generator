/******************************************************************************
 * Copyright (c) 2019. QuantumStudio                                          *
 ******************************************************************************/

package com.quantumstudio.concep.models;

import com.quantumstudio.concep.entities.%ENTITY%;

import javax.validation.constraints.NotNull;

public class %ENTITY%Model {

	private Long id;

	public static %ENTITY%Model from(%ENTITY% %ENTITY_VAR%) {
		%ENTITY%Model %ENTITY_VAR%Model = new %ENTITY%Model();

		// TODO: Setters

		return %ENTITY_VAR%Model;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
