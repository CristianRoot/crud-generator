/******************************************************************************
 * Copyright (c) 2019. QuantumStudio                                          *
 ******************************************************************************/

package com.quantumstudio.concep.services;

import com.quantumstudio.concep.exceptions.DuplicatedEntityException;
import com.quantumstudio.concep.exceptions.EntityNotFoundException;
import com.quantumstudio.concep.exceptions.IdRequiredException;
import com.quantumstudio.concep.exceptions.IllegalOperationException;
import com.quantumstudio.concep.models.%ENTITY%Model;

import java.util.List;

public interface %ENTITY%Service {

	List<%ENTITY%Model> findAll();

	%ENTITY%Model findOne(long id) throws EntityNotFoundException;

	%ENTITY%Model save(%ENTITY%Model %ENTITY_VAR%Model) throws DuplicatedEntityException;

	void update(long id, %ENTITY%Model %ENTITY_VAR%Model) throws EntityNotFoundException, DuplicatedEntityException, IdRequiredException, IllegalOperationException;

	void delete(long id) throws EntityNotFoundException;

}
