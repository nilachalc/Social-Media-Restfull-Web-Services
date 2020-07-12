package com.prac.rest.webservice.RestfulservicesDemo.restendpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prac.rest.webservice.RestfulservicesDemo.beans.Name;
import com.prac.rest.webservice.RestfulservicesDemo.beans.PersonV1;
import com.prac.rest.webservice.RestfulservicesDemo.beans.PersonV2;

@RestController
@RequestMapping(path = "/Restful-Services-Demo/versioning")
public class VersioningEndPoints {

	@GetMapping("/personv1")
	public PersonV1 URIVersioningV1() {
		return new PersonV1("Nilachal Chakraborty");
	}
	
	@GetMapping("/personv2")
	public PersonV2 URIVersioningV2() {
		return new PersonV2(new Name("Nilachal","Chakraborty"));
	}
	
	@GetMapping(value = "/person/param", params = "version=v1")
	public PersonV1 paramVersioningV1() {
		return new PersonV1("Nilachal Chakraborty");
	}
	
	@GetMapping(value = "/person/param", params = "version=v2")
	public PersonV2 paramVersioningV2() {
		return new PersonV2(new Name("Nilachal","Chakraborty"));
	}
	
	@GetMapping(value = "/person/header", headers = "API_VERSION=1")
	public PersonV1 headerVersioningV1() {
		return new PersonV1("Nilachal Chakraborty");
	}
	
	@GetMapping(value = "/person/header", headers = "API_VERSION=2")
	public PersonV2 headerVersioningV2() {
		return new PersonV2(new Name("Nilachal","Chakraborty"));
	}
	
	@GetMapping(value = "/person/mimetype", produces = "Application/vnd.comp_ver1+json")
	public PersonV1 mimeTypeVersioningV1() {
		return new PersonV1("Nilachal Chakraborty");
	}
	
	@GetMapping(value = "/person/mimetype", produces = "Application/vnd.comp_ver2+json")
	public PersonV2 mimeTypeVersioningV2() {
		return new PersonV2(new Name("Nilachal","Chakraborty"));
	}
}
