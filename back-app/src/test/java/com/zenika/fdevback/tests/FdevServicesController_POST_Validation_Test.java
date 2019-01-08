package com.zenika.fdevback.tests;

import com.zenika.fdevback.FdevServicesController;
import com.zenika.fdevback.ServiceCheckRequest;
import com.zenika.fdevback.ServicesRepository;
import org.json.JSONArray;
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
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
public class FdevServicesController_POST_Validation_Test {

	@Mock
	ServicesRepository repository;

	@InjectMocks
	FdevServicesController controller;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private void validateErrorBody(String responseStr, List<String> errors) {
		try {
			JSONObject obj = new JSONObject(responseStr);
			assertEquals(obj.length(), 2);

			assertTrue("Error object must have errors", obj.has("errors"));
			assertTrue("Error object must have errorMessage", obj.has("errorMessage"));

			assertTrue("Error body should have an array of errors", obj.get("errors") instanceof JSONArray);
			assertTrue("Error body should have a String error message", obj.get("errorMessage") instanceof String);

			assertEquals("Error body should have the correct error message", obj.getString("errorMessage"), "Validation failed for "  + errors.size() + " test(s).");
			assertEquals("Error body should have the correct error count", obj.getJSONArray("errors").length(), errors.size());

			List<String> actualErrors = new ArrayList<>();
			for (Object o: obj.getJSONArray("errors")) {
				actualErrors.add(o.toString());
			}

			// we don't want to order of the errors to matter, so we sort both arrays alphabetically
			java.util.Collections.sort(errors);
			java.util.Collections.sort(actualErrors);

			assertEquals("Both errors array should be equal", actualErrors, errors);
		} catch (JSONException e) {
			fail("Response body is not valid JSON. Got: \n\n" + responseStr + "\n\nJSON Exception is: " + e.getMessage());
		}
	}

	private void testInvalidBody(String json, List<String> errorList) throws Exception {
		MvcResult result = mockMvc.perform(post("/api/services")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isBadRequest())
				.andReturn();
		String responseStr = result.getResponse().getContentAsString();

		validateErrorBody(responseStr, errorList);
	}

	private void testSingleError(String expectedError, ServiceCheckRequest scr) throws Exception {
		List<String> errorListValidString = new ArrayList<>();
		errorListValidString.add(expectedError);

		testInvalidBody(scr.toJson(), errorListValidString);
	}

	private void testServiceNameError(String expectedError, String serviceName) throws Exception {
		ServiceCheckRequest serviceCheckRequest = new ServiceCheckRequest(serviceName, "http://google.fr");
		testSingleError(expectedError, serviceCheckRequest);
	}

	private void testServiceURIError(String expectedError, String serviceURI) throws Exception {
		ServiceCheckRequest serviceCheckRequest = new ServiceCheckRequest("Service", serviceURI);
		testSingleError(expectedError, serviceCheckRequest);
	}

	@Test
	public void testError_invalidBody() throws Exception {
		ServiceCheckRequest serviceCheckRequest = new ServiceCheckRequest();

		List<String> errorList = new ArrayList<>();
		errorList.add("serviceURI must be a valid URI");
		errorList.add("serviceName must be a valid String");

		testInvalidBody(serviceCheckRequest.toJson(), errorList);
	}

	@Test
	public void testError_longRandomInvalidBody() throws Exception {
		List<String> errorList = new ArrayList<>();

		errorList.add("serviceURI must be a valid URI");
		errorList.add("serviceName must be a valid String");

		testInvalidBody("{\"_id\":\"5bd189c263c530fbcf846d0f\",\"index\":0,\"guid\":\"c0adfd2c-4373-4719-a5e5-3349bf8bfe7f\",\"isActive\":false,\"balance\":\"$1,264.13\",\"picture\":\"http://placehold.it/32x32\",\"age\":37,\"eyeColor\":\"blue\",\"name\":\"Tisha Conway\",\"gender\":\"female\",\"company\":\"VICON\",\"email\":\"tishaconway@vicon.com\",\"phone\":\"+1 (871) 549-2812\",\"address\":\"866 Alabama Avenue, Tilden, Ohio, 771\",\"about\":\"Velit ullamco nisi velit veniam esse magna anim magna excepteur. Sunt aliquip labore do non excepteur deserunt laborum duis. In sit magna ea sunt aliquip irure proident consequat incididunt cupidatat. Labore laborum velit laborum anim qui voluptate eiusmod mollit laboris cillum qui deserunt. Quis irure occaecat sint consequat laborum sint ad in minim quis fugiat elit do.\\r\\n\",\"registered\":\"2016-06-23T08:38:41 -02:00\",\"latitude\":17.20778,\"longitude\":-55.422732,\"tags\":[\"incididunt\",\"deserunt\",\"velit\",\"nostrud\",\"consectetur\",\"incididunt\",\"consequat\"],\"friends\":[{\"id\":0,\"name\":\"Campos Washington\"},{\"id\":1,\"name\":\"Lupe Park\"},{\"id\":2,\"name\":\"Jenifer Oconnor\"}],\"greeting\":\"Hello, Tisha Conway! You have 6 unread messages.\",\"favoriteFruit\":\"banana\"}", errorList);
	}

	@Test
	public void testError_Traps() throws Exception {
		List<String> errorList = new ArrayList<>();

		errorList.add("serviceURI must be a valid URI");
		errorList.add("serviceName must be a valid String");

		testInvalidBody("{}", errorList);
	}

	@Test
	public void testError_invalidServiceName_tooSmall() throws Exception {
		testServiceNameError("serviceName must be a valid String", "q");
	}

	@Test
	public void testError_invalidServiceName_emptyString() throws Exception {
		testServiceNameError("serviceName must be a valid String", "");
	}

	@Test
	public void testError_invalidServiceName_Null() throws Exception {
		testServiceNameError("serviceName must be a valid String", null);
	}

	@Test
	public void testError_invalidServiceName_invalidChars() throws Exception {
		testServiceNameError("serviceName must be a valid String", "éè-qweqweq-1231241");
	}


	@Test
	public void testError_invalidServiceURL_null() throws Exception {
		testServiceURIError("serviceURI must be a valid URI", null);
	}

	@Test
	public void testError_invalidServiceURL_emptyString() throws Exception {
		testServiceURIError("serviceURI must be a valid URI", "");
	}

	@Test
	public void testError_invalidServiceURL_invalidURI_1() throws Exception {
		testServiceURIError("serviceURI must be a valid URI", "hppt://truc.machin");
	}

	@Test
	public void testError_invalidServiceURL_invalidURI_2() throws Exception {
		testServiceURIError("serviceURI must be a valid URI", "http:/truc.machin");
	}

	@Test
	public void testError_invalidServiceURL_invalidURI_3() throws Exception {
		testServiceURIError("serviceURI must be a valid URI", "éè");
	}

	@Test
	public void testError_invalidServiceURL_invalidURI_4() throws Exception {
		testServiceURIError("serviceURI must be a valid URI", "google.fr");
	}
}