/******************************************************************************
 * Copyright (c) 2019. QuantumStudio                                          *
 ******************************************************************************/

package com.quantumstudio.concep.integration.%PACKAGE_NAME%;

import com.quantumstudio.concep.models.%ENTITY%Model;
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

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class Get%ENTITY%Test {

	private final String TESTED_ENDPOINT = "/%ENDPOINT_NAME%/%s";
	private final HttpMethod TESTED_ENDPOINT_METHOD = HttpMethod.GET;

	@Autowired
	private TestHttpUtil httpUtil;

	public Get%ENTITY%Test() {

	}

	@Test
	public void givenValidId_shouldSuccessWith200() {
		ResponseEntity result = httpUtil.adminRequest(String.format(TESTED_ENDPOINT, 1), TESTED_ENDPOINT_METHOD, %ENTITY%Model.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertNotNull(result.getBody());
		assertThat(result.getBody(), instanceOf(%ENTITY%Model.class));
	}

	@Test
	public void givenNonExistEntity_shouldNotFoundWith404() {
		ResponseEntity result = httpUtil.adminRequest(String.format(TESTED_ENDPOINT, 999), TESTED_ENDPOINT_METHOD, %ENTITY%Model.class);
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

}
