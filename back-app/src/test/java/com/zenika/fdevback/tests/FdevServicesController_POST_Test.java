package com.zenika.fdevback.tests;

import com.zenika.fdevback.FdevService;
import com.zenika.fdevback.FdevServicesController;
import com.zenika.fdevback.ServicesRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
public class FdevServicesController_POST_Test {

	@Mock
	ServicesRepository repository;

	@InjectMocks
	FdevServicesController controller;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void test_Error_validParams_NonExistingService() throws Exception {
		FdevService s = new FdevService("ServiceName0", "ServiceIconUri0", "ServiceDescription0", "MDGuide0", false);
		List<FdevService> lst = new ArrayList<>();
		lst.add(s);
		when(repository.findAll()).thenReturn(lst);

		JSONObject json = new JSONObject();
		json.put("serviceName", "Artifactory");
		json.put("serviceURI", "http://google.fr");

		MvcResult res = mockMvc.perform(post("/api/services")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.toString()))
				.andExpect(status().isNotFound())
				.andReturn();

		try {
			JSONObject obj = new JSONObject(res.getResponse().getContentAsString());
			assertEquals(obj.length(), 2);

			assertTrue(obj.has("error"));
			assertTrue(obj.has("message"));

			assertTrue(obj.get("error") instanceof Boolean);
			assertTrue(obj.get("message") instanceof String);

			assertTrue(obj.getBoolean("error"));
			assertEquals(obj.getString("message"), "Service does not exist.");

		} catch (JSONException e) {
			fail("Response body is not valid JSON. Got: \n\n" + res.getResponse().getContentAsString() + "\n\nJSON Exception is: " + e.getMessage());
		} finally {
			verify(repository, times(1)).findAll();
			verifyNoMoreInteractions(repository);
		}
	}

	@Test
	public void test_Error_validParams_UnreachableURI() throws Exception {
		FdevService s = new FdevService("ServiceName", "ServiceIconUri0", "ServiceDescription0", "MDGuide0", false);
		List<FdevService> lst = new ArrayList<>();
		lst.add(s);
		when(repository.findAll()).thenReturn(lst);

		JSONObject json = new JSONObject();
		json.put("serviceName", "ServiceName");
		json.put("serviceURI", "http://qweqweqweqweqweqweq-eq-google.fr");

		MvcResult res = mockMvc.perform(post("/api/services")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(json.toString()))
				.andExpect(status().isOk())
				.andReturn();

		try {
			JSONObject obj = new JSONObject(res.getResponse().getContentAsString());
			assertEquals(obj.length(), 2);

			assertTrue(obj.has("error"));
			assertTrue(obj.has("result"));

			assertTrue(obj.get("error") instanceof Boolean);
			assertTrue(obj.get("result") instanceof Boolean);

			assertFalse(obj.getBoolean("result"));
			assertFalse(obj.getBoolean("error"));
			assertFalse(s.isInstalled());
		} catch (JSONException e) {
			fail("Response body is not valid JSON. Got: \n\n" + res.getResponse().getContentAsString() + "\n\nJSON Exception is: " + e.getMessage());
		} finally {
			verify(repository, times(1)).findAll();
			verify(repository, times(1)).save(s);
			verifyNoMoreInteractions(repository);
		}
	}

	@Test
	public void test_Success() throws Exception {
		FdevService s = new FdevService("ServiceName", "ServiceIconUri0", "ServiceDescription0", "MDGuide0", false);
		List<FdevService> lst = new ArrayList<>();
		lst.add(s);
		when(repository.findAll()).thenReturn(lst);

		JSONObject json = new JSONObject();
		json.put("serviceName", "ServiceName");
		json.put("serviceURI", "http://google.fr");

		MvcResult res = mockMvc.perform(post("/api/services")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(json.toString()))
				.andExpect(status().isOk())
				.andReturn();

		try {
			JSONObject obj = new JSONObject(res.getResponse().getContentAsString());
			assertEquals(obj.length(), 2);

			assertTrue(obj.has("error"));
			assertTrue(obj.has("result"));

			assertTrue(obj.get("error") instanceof Boolean);
			assertTrue(obj.get("result") instanceof Boolean);

			assertTrue(obj.getBoolean("result"));
			assertFalse(obj.getBoolean("error"));

			assertTrue(s.isInstalled());
		} catch (JSONException e) {
			fail("Response body is not valid JSON. Got: \n\n" + res.getResponse().getContentAsString() + "\n\nJSON Exception is: " + e.getMessage());
		} finally {
			verify(repository, times(1)).findAll();
			verify(repository, times(1)).save(s);
			verifyNoMoreInteractions(repository);
		}
	}
}