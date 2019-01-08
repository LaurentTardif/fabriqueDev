package com.zenika.fdevback;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FdevServicesController {
	private static final Logger logger = LogManager.getLogger(FdevServicesController.class);

	@Autowired
	ServicesRepository repository;

	/**
	 * findAll(), returns the list of all services
	 * @return JSON representation of the list of services
	 * @TODO ajouter un champ version
	 * @TODO encodage (UTF-8 par default ?) / langue ?
	 */
	@GetMapping(path = "/services", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAll() {
		logger.trace("GET /api/services");

		List<FdevService> list = (List<FdevService>) repository.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// @todo /health est-ce que le service / jvm va bien ?

	@PostMapping(
			path = "/services",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity checkService(@Valid @RequestBody ServiceCheckRequest request) {
		logger.trace("POST /api/services");

		JSONObject response = new JSONObject();
		List<FdevService> matchedServices = StreamSupport.stream(repository.findAll().spliterator(), false)
				.filter(serviceTest -> serviceTest.getServiceName().equals(request.getServiceName()))
				.collect(Collectors.toList());

		if (matchedServices.isEmpty()) {
			response.put("error", true);
			response.put("message", "Service does not exist.");
			return new ResponseEntity<>(response.toString(), HttpStatus.NOT_FOUND);
		}

		logger.trace("Checking URL : " + request.getServiceURI());

		FdevService service = matchedServices.get(0);
		if (ServiceTesters.checkService(request)) {
			logger.trace("Service is UP");

			service.setInstalled(true);
			response.put("error", false);
			response.put("result", true);
		} else {
			logger.trace("Service is DOWN");

			service.setInstalled(false);
			response.put("error", false);
			response.put("result", false);
		}
		repository.save(service);
		return new ResponseEntity<>(response.toString(), HttpStatus.OK);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public APIError handleException(MethodArgumentNotValidException exception) {
		Errors validationErrors = exception.getBindingResult();
		APIError apiError = new APIError();

		// We only want to show ONE error per field, so we store the name of the fields with error in erroredFields
		List<String> erroredFields = new ArrayList<>();

		for (ObjectError objectError : validationErrors.getAllErrors()) {
			// The field that has an error is the first word of the error string
			String erroredField = objectError.getDefaultMessage().split(" ", 2)[0];

			// Check if the current field already has an error, if so ignore this new one
			if (!erroredFields.contains(erroredField)) {
				erroredFields.add(erroredField);
				apiError.addValidationError(objectError.getDefaultMessage());
			}
		}

		// We only count one error per field, so the error count is the size of erroredField, not the size of validationErrors
		apiError.setErrorMessage("Validation failed for " + erroredFields.size() + " test(s).");
		return apiError;
	}
}
