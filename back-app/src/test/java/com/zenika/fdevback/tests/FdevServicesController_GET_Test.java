package com.zenika.fdevback.tests;

import com.zenika.fdevback.FdevService;
import com.zenika.fdevback.FdevServicesController;
import com.zenika.fdevback.ServicesRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringJUnit4ClassRunner.class)
public class FdevServicesController_GET_Test {

	@Mock
	ServicesRepository repository;

	@InjectMocks
	FdevServicesController controller;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private void checkInnerSimpleObject(JSONArray arr, int i) throws JSONException {
		assertTrue(arr.get(i) instanceof JSONObject);

		JSONObject object = arr.getJSONObject(i);

		assertEquals(object.length(), 6);

		assertTrue(object.has("id"));
		assertTrue(object.has("serviceName"));
		assertTrue(object.has("serviceIconUri"));
		assertTrue(object.has("serviceDescriptionShort"));
		assertTrue(object.has("serviceMdInstallationGuide"));
		assertTrue(object.has("installed"));

		assertTrue(object.get("serviceName") instanceof String);
		assertTrue(object.get("serviceIconUri") instanceof String);
		assertTrue(object.get("serviceDescriptionShort") instanceof String);
		assertTrue(object.get("serviceMdInstallationGuide") instanceof String);
		assertTrue(object.get("installed") instanceof Boolean);

		assertEquals(object.getString("serviceName"), "ServiceName" + i);
		assertEquals(object.getString("serviceIconUri"), "ServiceIconUri" + i);
		assertEquals(object.getString("serviceDescriptionShort"), "ServiceDescription" + i);
		assertEquals(object.getString("serviceMdInstallationGuide"), "MDGuide" + i);
		assertEquals(object.getBoolean("installed"), false);
		assertEquals(object.getInt("id"), i);
	}

	@Test
	public void findAll_emptyContent() throws Exception {
		MvcResult res = mockMvc.perform(get("/api/services/"))
				.andExpect(status().isOk())
				.andExpect(header().string(CONTENT_TYPE, "application/json;charset=UTF-8"))
				.andReturn();

		try {
			JSONArray arr = new JSONArray(res.getResponse().getContentAsString());
			assertEquals(arr.length(), 0);
		} catch (JSONException e) {
			fail("Response body is not valid JSON. Got: \n\n" + res.getResponse().getContentAsString() + "\n\nJSON Exception is: " + e.getMessage());
		} finally {
			verify(repository, times(1)).findAll();
			verifyNoMoreInteractions(repository);
		}
	}

	@Test
	public void findAll_oneSimpleService() throws Exception {
		FdevService s = new FdevService("ServiceName0", "ServiceIconUri0", "ServiceDescription0", "MDGuide0", false);
		s.setId(0);
		List<FdevService> lst = new ArrayList<>();
		lst.add(s);
		when(repository.findAll()).thenReturn(lst);


		MvcResult res = mockMvc.perform(get("/api/services/"))
				.andExpect(status().isOk())
				.andExpect(header().string(CONTENT_TYPE, "application/json;charset=UTF-8"))
				.andReturn();

		try {
			JSONArray arr = new JSONArray(res.getResponse().getContentAsString());
			assertEquals(arr.length(), 1);

			checkInnerSimpleObject(arr, 0);
		} catch (JSONException e) {
			fail("Response body is not valid JSON. Got: \n\n" + res.getResponse().getContentAsString() + "\n\nJSON Exception is: " + e.getMessage());
		} finally {
			verify(repository, times(1)).findAll();
			verifyNoMoreInteractions(repository);
		}
	}

	@Test
	public void findAll_twoSimpleServices() throws Exception {
		List<FdevService> lst = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			FdevService s = new FdevService("ServiceName" + i, "ServiceIconUri" + i, "ServiceDescription" + i, "MDGuide" + i, false);
			s.setId(i);
			lst.add(s);
		}

		when(repository.findAll()).thenReturn(lst);


		MvcResult res = mockMvc.perform(get("/api/services/"))
				.andExpect(status().isOk())
				.andExpect(header().string(CONTENT_TYPE, "application/json;charset=UTF-8"))
				.andReturn();

		try {
			JSONArray arr = new JSONArray(res.getResponse().getContentAsString());
			assertEquals(arr.length(), 2);


			for (int i = 0; i < 2; i++) {
				checkInnerSimpleObject(arr, i);
			}
		} catch (JSONException e) {
			fail("Response body is not valid JSON. Got: \n\n" + res.getResponse().getContentAsString() + "\n\nJSON Exception is: " + e.getMessage());
		} finally {
			verify(repository, times(1)).findAll();
			verifyNoMoreInteractions(repository);
		}
	}

	@Test
	public void findAll_manySimpleServices() throws Exception {
		List<FdevService> lst = new ArrayList<>();
		for (int i = 0; i < 999; i++) {
			FdevService s = new FdevService("ServiceName" + i, "ServiceIconUri" + i, "ServiceDescription" + i, "MDGuide" + i, false);
			s.setId(i);
			lst.add(s);
		}

		when(repository.findAll()).thenReturn(lst);


		MvcResult res = mockMvc.perform(get("/api/services/"))
				.andExpect(status().isOk())
				.andExpect(header().string(CONTENT_TYPE, "application/json;charset=UTF-8"))
				.andReturn();

		try {
			JSONArray arr = new JSONArray(res.getResponse().getContentAsString());
			assertEquals(arr.length(), 999);


			for (int i = 0; i < 999; i++) {
				checkInnerSimpleObject(arr, i);
			}
		} catch (JSONException e) {
			fail("Response body is not valid JSON. Got: \n\n" + res.getResponse().getContentAsString() + "\n\nJSON Exception is: " + e.getMessage());
		} finally {
			verify(repository, times(1)).findAll();
			verifyNoMoreInteractions(repository);
		}
	}
}