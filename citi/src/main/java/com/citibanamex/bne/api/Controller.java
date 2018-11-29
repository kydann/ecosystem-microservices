package com.citibanamex.bne.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citibanamex.bne.jdbcclient.model.SqlStatementRequest;
import com.citibanamex.bne.jdbcclient.model.SqlStatementResponse;
import com.citibanamex.bne.services.ApiService;
import com.citibanamex.bne.domain.employe;

@RestController
@RequestMapping("/v1")
public class Controller {
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	private ApiService apiService;
	
    @RequestMapping(value = "/test/angel")
	public ResponseEntity<Object> test() {
		
		logger.info("/test/angel");
		
		return new ResponseEntity<>("/test/angel", HttpStatus.OK);
	}

	@RequestMapping(value = "/generic/endpoint1")
	public ResponseEntity<SqlStatementResponse> directQuery(@RequestBody SqlStatementRequest request) {	
		SqlStatementResponse output = apiService.directRawQuery(request);
		
		logger.info("Calling direct query service");
		
		return new ResponseEntity<>(output, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/generic/endpoint2", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> directQuery() {	
		Map<String, Object> output = apiService.proxyQueryToMap();
		
		logger.info("Calling direct query to map service");
		
		return new ResponseEntity<>(output, HttpStatus.OK);
	}
	
	private static Map<String, List<employe>> schooDB = new HashMap<String, List<employe>>();

	static {
		schooDB = new HashMap<String, List<employe>>();

		List<employe> lst = new ArrayList<employe>();
		employe std = new employe("Chris", "Class");
		lst.add(std);
		std = new employe("Evelin", "Class");
		lst.add(std);
		schooDB.put("citiBank", lst);

		lst = new ArrayList<employe>();
		std = new employe("Diana", "Class");
		lst.add(std);
		std = new employe("Erick", "Class");
		lst.add(std);

		schooDB.put("citi", lst);

	}

	@RequestMapping(value = "/getEmployeeDetailsForCiti/{employee}", method = RequestMethod.GET)
	public List<employe> getEmployees(@PathVariable String employee) {
		System.out.println("Getting Student details for " + employee);

		List<employe> citiList = schooDB.get(employee);
		if (citiList == null) {
			citiList = new ArrayList<employe>();
			employe std = new employe("Not Found", "N/A");
			citiList.add(std);
		}
		return citiList;
	}
	
}
