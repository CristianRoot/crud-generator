/******************************************************************************
 * Copyright (c) 2019. QuantumStudio                                          *
 ******************************************************************************/

package com.quantumstudio.concep.controllers;

import com.quantumstudio.concep.exceptions.DuplicatedEntityException;
import com.quantumstudio.concep.exceptions.EntityNotFoundException;
import com.quantumstudio.concep.exceptions.IdRequiredException;
import com.quantumstudio.concep.exceptions.IllegalOperationException;
import com.quantumstudio.concep.models.%ENTITY%Model;
import com.quantumstudio.concep.services.%ENTITY%Service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
public class %ENTITY%Controller {

	private final %ENTITY%Service %ENTITY_VAR%Service;

	public %ENTITY%Controller(%ENTITY%Service %ENTITY_VAR%Service) {
		this.%ENTITY_VAR%Service = %ENTITY_VAR%Service;
	}

	@GetMapping("/%ENDPOINT_NAME%")
	public ResponseEntity<List<%ENTITY%Model>> findAll() {
		return ResponseEntity.ok(%ENTITY_VAR%Service.findAll());
	}

	@GetMapping("/%ENDPOINT_NAME%/{id}")
	public ResponseEntity<%ENTITY%Model> findOne(@PathVariable long id) throws EntityNotFoundException {
		return ResponseEntity.ok(%ENTITY_VAR%Service.findOne(id));
	}
	
	@PostMapping("/%ENDPOINT_NAME%")
	public ResponseEntity save(@Valid @RequestBody %ENTITY%Model %ENTITY_VAR%Model) throws DuplicatedEntityException {
		%ENTITY%Model saved%ENTITY% = %ENTITY_VAR%Service.save(%ENTITY_VAR%Model);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
												  .path("/{id}")
												  .buildAndExpand(saved%ENTITY%.getId())
												  .toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/%ENDPOINT_NAME%/{id}")
	public ResponseEntity update(@PathVariable long id, @RequestBody %ENTITY%Model %ENTITY_VAR%Model) throws DuplicatedEntityException, IllegalOperationException, IdRequiredException, EntityNotFoundException {
		%ENTITY_VAR%Service.update(id, %ENTITY_VAR%Model);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/%ENDPOINT_NAME%/{id}")
	public ResponseEntity delete(@PathVariable long id) throws EntityNotFoundException {
		%ENTITY_VAR%Service.delete(id);
		return ResponseEntity.ok().build();
	}

}
