/******************************************************************************
 * Copyright (c) 2019. QuantumStudio                                          *
 ******************************************************************************/

package com.quantumstudio.concep.services.impl;

import com.quantumstudio.concep.entities.%ENTITY%;
import com.quantumstudio.concep.exceptions.DuplicatedEntityException;
import com.quantumstudio.concep.exceptions.EntityNotFoundException;
import com.quantumstudio.concep.exceptions.IdRequiredException;
import com.quantumstudio.concep.exceptions.IllegalOperationException;
import com.quantumstudio.concep.models.%ENTITY%Model;
import com.quantumstudio.concep.repositories.%ENTITY%Repository;
import com.quantumstudio.concep.services.%ENTITY%Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class %ENTITY%ServiceImpl implements %ENTITY%Service {

	private final %ENTITY%Repository %ENTITY_VAR%Repository;

	public %ENTITY%ServiceImpl(%ENTITY%Repository %ENTITY_VAR%Repository) {
		this.%ENTITY_VAR%Repository = %ENTITY_VAR%Repository;
	}

	@Override
	public List<%ENTITY%Model> findAll() {
		return %ENTITY_VAR%Repository.findAll().stream().map(%ENTITY%Model::from).collect(Collectors.toList());
	}

	@Override
	public %ENTITY%Model findOne(long id) throws EntityNotFoundException {
		return %ENTITY_VAR%Repository.findById(id).map(%ENTITY%Model::from).orElseThrow(() -> new EntityNotFoundException(%ENTITY%.class, id));
	}

	@Override
	public %ENTITY%Model save(%ENTITY%Model %ENTITY_VAR%Model) throws DuplicatedEntityException {
		// TODO: Exceptions

		%ENTITY% %ENTITY_VAR% = new %ENTITY%();
		// TODO: Setters

		return %ENTITY%Model.from(%ENTITY_VAR%Repository.save(%ENTITY_VAR%));
	}

	@Override
	public void update(long id, %ENTITY%Model %ENTITY_VAR%Model) throws EntityNotFoundException, DuplicatedEntityException, IdRequiredException, IllegalOperationException {
		if (%ENTITY_VAR%Model.getId() == null)
			throw new IdRequiredException();
		if (id != %ENTITY_VAR%Model.getId())
			throw new IllegalOperationException("IDs doesn't match");

		%ENTITY% %ENTITY_VAR% = %ENTITY_VAR%Repository.findById(id).orElseThrow(() -> new EntityNotFoundException(%ENTITY%.class, id));

		// TODO: Exceptions

		%ENTITY_VAR%Repository.save(%ENTITY_VAR%);
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		%ENTITY% %ENTITY_VAR% = %ENTITY_VAR%Repository.findById(id).orElseThrow(() -> new EntityNotFoundException(%ENTITY%.class, id));
		%ENTITY_VAR%Repository.delete(%ENTITY_VAR%);
	}

}
