/******************************************************************************
 * Copyright (c) 2019. QuantumStudio                                          *
 ******************************************************************************/

package com.quantumstudio.concep.entities;

import javax.persistence.*;

@Entity
public class %ENTITY% {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// TODO: Columns

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
