package com.prac.rest.webservice.restfulservicesdemo.restendpoint;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.prac.rest.webservice.restfulservicesdemo.beans.HellowWorldBean;
import com.prac.rest.webservice.restfulservicesdemo.beans.Post;
import com.prac.rest.webservice.restfulservicesdemo.beans.TestBean;
import com.prac.rest.webservice.restfulservicesdemo.beans.SocailMediaUser;
import com.prac.rest.webservice.restfulservicesdemo.exception.UserNotFoundException;
import com.prac.rest.webservice.restfulservicesdemo.service.PostService;
import com.prac.rest.webservice.restfulservicesdemo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/Restful-Services-Demo")
public class RestControllerEndPoints {
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;

	private FilterProvider filter;
	
	private SimpleBeanPropertyFilter simpleBeanPropertyFilter; 
	
	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public List<SocailMediaUser> getAllUsers() {
		return userService.fetchAll();
	}
	
	@GetMapping(path = "/user/{userId}")
	public SocailMediaUser getUserById(@PathVariable Integer userId) throws UserNotFoundException {
		SocailMediaUser socailMediaUser = userService.fetchAUser(userId);
		if (socailMediaUser == null) {
			throw new UserNotFoundException("The SocailMediaUser with Id --> " + userId + " does not exists.");
		}
		return socailMediaUser;
	}
	
	@PostMapping(path = "/user")
	public SocailMediaUser saveUser(@Valid @RequestBody SocailMediaUser socailMediaUser) {
		return userService.save(socailMediaUser);
	}
	
	@PostMapping(path = "/user/getId")
	public ResponseEntity<SocailMediaUser> saveUserAndReturnId(@RequestBody SocailMediaUser socailMediaUser) {
		SocailMediaUser savedUser = userService.save(socailMediaUser);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{newUserId}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping(path = "/user/updateuser")
	public ResponseEntity<SocailMediaUser> updateUser(@RequestBody SocailMediaUser socailMediaUser) {
		SocailMediaUser savedUser = userService.save(socailMediaUser);
		return new ResponseEntity<SocailMediaUser>(savedUser, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/user/{userId}")
	public void deleteUserById(@PathVariable Integer userId) throws UserNotFoundException {
		userService.deleteAUser(userId);
	}
	
	@RequestMapping(path = "/user/{userId}/posts", method = RequestMethod.GET)
	public List<Post> getAllPostsOfAUser(@PathVariable Integer userId) {
		return postService.fetchAllPostsOfAUser(userId);
	}
	
	@PostMapping(path = "/user/{userId}/post", consumes = "application/json" )
	public Post savePostForAUser(@PathVariable Integer userId, @Valid @RequestBody Post post) {
		return postService.savePostForAUser(userId, post);
	}
	
	@PostMapping(path = "/user/{userId}/posts")
	public List<Post> savePostsForAUser(@PathVariable Integer userId, @Valid @RequestBody List<Post> posts) {
		return postService.savePostsForAUser(userId, posts);
	}
	
	@PostMapping(path = "/user/{userId}/updatepost")
	public Post updatePostForAUser(@PathVariable Integer userId, @Valid @RequestBody Post post) {
		return postService.updatePostForAUser(userId, post);
	}
	
	@DeleteMapping(path = "/user/{userId}/posts/{postId}")
	public Post deletePostOfAUserById(@PathVariable Integer userId, @PathVariable Integer postId) throws UserNotFoundException {
		return postService.deletePostOfAUserById(userId, postId);
	}
	
	//As we have used SessionLocaleResolver in configuration so the caller should pass the the local value every time
	//for this type of implementation.
	//@RequestMapping(path = "/sayGM/internationalized", method = RequestMethod.GET)
	//public String sayGoodMorning(@RequestHeader(name = "App_Locale", required = false) Locale locale) {
	//	return messageSource.getMessage("goodmorning.message", null, locale);
	//}
	
	@Operation(summary = "Greet the user", 
	description = "Returns a greeting message to the user",
	responses = {
	    @ApiResponse(description = "Successful Operation", 
	                 responseCode = "200", 
	                 content = @Content(schema = @Schema(implementation = String.class))),
	    @ApiResponse(description = "Invalid Parameter", 
	                 responseCode = "400", 
	                 content = @Content(schema = @Schema(implementation = String.class)))
	})
	@RequestMapping(path = "/sayGM/internationalized", method = RequestMethod.GET)
	public String sayGoodMorning() {
		return messageSource.getMessage("goodmorning.message", null, LocaleContextHolder.getLocale());
	}
	
	@RequestMapping(path = "/fetchhellowworld/{passedValue}")
	public HellowWorldBean getHellowWorld(@PathVariable String passedValue) {
		HellowWorldBean hellowWorldBean = new HellowWorldBean();
		hellowWorldBean.setMessage("Hellow World, " + passedValue);
		return hellowWorldBean;
	}
	
	@RequestMapping(path = "/testBeans", method = RequestMethod.GET)
	public List<TestBean> getAllTests() {
		List<TestBean> beans = new ArrayList<TestBean>();
		beans.add(new TestBean("field1", "field2", "field3", "field4", "field5", "field6"));
		beans.add(new TestBean("field11", "field21", "field31", "field41", "field51", "field61"));
		beans.add(new TestBean("field12", "field22", "field32", "field42", "field52", "field62"));
		return beans;
	}
	
	@RequestMapping(path = "/testBean", method = RequestMethod.GET)
	public TestBean getTest() {
		TestBean bean = new TestBean("field1", "field2", "field3", "field4", "field5", "field6");
		return bean;
	}
	
	@GetMapping(path = "/dynamic/testBeans")
	public MappingJacksonValue getAllTestsDynamic() {
		List<TestBean> beans = new ArrayList<TestBean>();
		beans.add(new TestBean("field1", "field2", "field3", "field4", "field5", "field6"));
		beans.add(new TestBean("field11", "field21", "field31", "field41", "field51", "field61"));
		beans.add(new TestBean("field12", "field22", "field32", "field42", "field52", "field62"));
		
		simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field5", "field6");
		filter = new SimpleFilterProvider().addFilter("TestBeanFilter", simpleBeanPropertyFilter);
		
		MappingJacksonValue jacksonValue = new MappingJacksonValue(beans);
		jacksonValue.setFilters(filter);
		return jacksonValue;
	}
	
	@GetMapping(path = "/dynamic/testBean")
	public MappingJacksonValue getTestDynamic() {
		TestBean bean = new TestBean("field1", "field2", "field3", "field4", "field5", "field6");
		simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field5");
		filter = new SimpleFilterProvider().addFilter("TestBeanFilter", simpleBeanPropertyFilter);
		
		MappingJacksonValue jacksonValue = new MappingJacksonValue(bean);
		jacksonValue.setFilters(filter);
		return jacksonValue;
	}
}