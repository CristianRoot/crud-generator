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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class Get%ENTITY_PLURAL%Test {

	private final String TESTED_ENDPOINT = "/%ENDPOINT_NAME%";
	private final HttpMethod TESTED_ENDPOINT_METHOD = HttpMethod.GET;

	@Autowired
	private TestHttpUtil httpUtil;

	public Get%ENTITY_PLURAL%Test() {

	}

	@Test
	public void shouldSuccessWith200AndReturnList() {
		ResponseEntity<List<%ENTITY%Model>> result = httpUtil.adminRequest(TESTED_ENDPOINT,
																		   TESTED_ENDPOINT_METHOD,
																		   new ParameterizedTypeReference<List<%ENTITY%Model>>() {
																		   });

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertNotNull(result.getBody());
		assertThat(result.getBody(), instanceOf(List.class));
		List list = result.getBody();
		assertThat(list.size(), greaterThan(0));
		assertThat(list.get(0), instanceOf(%ENTITY%Model.class));
	}

}
