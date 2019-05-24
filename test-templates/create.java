/******************************************************************************
 * Copyright (c) 2019. QuantumStudio                                          *
 ******************************************************************************/

package com.quantumstudio.concep.integration.%PACKAGE_NAME%;

import com.quantumstudio.concep.models.%ENTITY%Model;
import com.quantumstudio.concep.utils.PropertyMapper;
import com.quantumstudio.concep.utils.TestHttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class Create%ENTITY%Test {

	private final String TESTED_ENDPOINT = "/%ENDPOINT_NAME%";
	private final HttpMethod TESTED_ENDPOINT_METHOD = HttpMethod.POST;

	@Autowired
	private TestHttpUtil httpUtil;

	public Create%ENTITY%Test() {

	}

	@Test
	public void givenNotValidModel_shouldBadRequestWith400() throws IllegalAccessException {
		%ENTITY%Model %ENTITY_VAR%Model = new %ENTITY%Model();

		ResponseEntity<?> result = httpUtil.adminRequest(TESTED_ENDPOINT, TESTED_ENDPOINT_METHOD,
														 PropertyMapper.map(%ENTITY%Model.class, %ENTITY_VAR%Model),
														 Object.class);

		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	@Test
	public void givenValidModel_shouldSuccessWith201() throws IllegalAccessException {
		%ENTITY%Model %ENTITY_VAR%Model = new %ENTITY%Model();
		// TODO: Setters

		ResponseEntity<?> result = httpUtil.adminRequest(TESTED_ENDPOINT, TESTED_ENDPOINT_METHOD,
														 PropertyMapper.map(%ENTITY%Model.class, %ENTITY_VAR%Model),
														 Object.class);

		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

}
