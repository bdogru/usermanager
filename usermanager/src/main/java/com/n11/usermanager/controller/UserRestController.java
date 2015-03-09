package com.n11.usermanager.controller;

import javax.servlet.ServletRequest;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.n11.usermanager.dao.UserRepository;
import com.n11.usermanager.domain.User;
import com.n11.usermanager.marshaller.UserMarshaller;
import com.n11.usermanager.validation.ListingParameters;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;
	@Autowired
	private ReCaptchaImpl reCaptcha;

    @RequestMapping(value="/get/{id}",method=RequestMethod.POST)
    public @ResponseBody String get(@PathVariable(value="id") String id){
    	System.out.println("get is called with id: "+id);
    	User user = userRepository.findOne(id);
    	return UserMarshaller.toJsonObject(user).toJSONString();
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody String addUser(
//			@RequestParam("recaptcha_challenge_field") String recaptcha_challenge_field, 
//			@RequestParam("recaptcha_response_field") String recaptcha_response_field, 
//			ServletRequest servletRequest, 
    		@ModelAttribute(value="user") User user
    		){
    	System.out.println("addUser is called with user: "+UserMarshaller.toJsonObject(user).toJSONString());
//    	System.out.println("challangeField: "+recaptcha_challenge_field);
//    	System.out.println("responseField: "+recaptcha_response_field);
    	JSONObject result = new JSONObject();
//		String remoteAddress = servletRequest.getRemoteAddr();
//		ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, recaptcha_challenge_field, recaptcha_response_field);
//		System.out.println(reCaptchaResponse.getErrorMessage());
//		System.out.println(reCaptchaResponse.isValid());
//		if(!reCaptchaResponse.isValid()){
//			result.put("status", false);
//			result.put("message", "captha is not correct.");
//			return result.toJSONString();
//		}
		if(StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getSurname())){
			result.put("status", false);
			result.put("message", "Name and Surname cannot be blank.");
			return result.toJSONString();
		}
        userRepository.save(user);
        result.put("status", true);
        result.put("message", "User has been added to the list. Total number of users are " + userRepository.count());
        return result.toJSONString();
    }

	@RequestMapping(value = "/list",method=RequestMethod.POST)
	public @ResponseBody String showUsers(
			@ModelAttribute(value="params") ListingParameters params
    		) {
    	System.out.println("list is called with params: "+params.toString());
		if (params == null || ((params.getMax() == null || params.getMax() <= 0) && StringUtils.isEmpty(params.getSorter()))) {
			
			return UserMarshaller.toJsonObject(userRepository.findAll()).toJSONString();
		}
		Sort srt = null;
		if( !StringUtils.isEmpty( params.getSorter() ) ) {
			if(params.getAsc() != null && !params.getAsc()) {
				srt = new Sort(Direction.DESC, params.getSorter());
			} else {
				srt = new Sort(params.getSorter());
			}
		}
		Pageable pgbl = null;
		if((params.getMax() != null && params.getMax() > 0)) {
			if((params.getPage() != null && params.getPage() > 0)) {
				if(srt != null) {
					pgbl = new PageRequest(params.getPage(), params.getMax(), srt);
				} else {
					pgbl = new PageRequest(params.getPage(), params.getMax());
				}
			} else {
				if(srt != null) {
					pgbl = new PageRequest(1, params.getMax(), srt);
				} else {
					pgbl = new PageRequest(1, params.getMax());
				}
			}
		}
		if(pgbl == null) {
			return UserMarshaller.toJsonObject(userRepository.findAll(srt)).toJSONString();
		}
		return UserMarshaller.toJsonObject(userRepository.findAll(pgbl)).toJSONString();
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
    public @ResponseBody String deleteUser(
    		@PathVariable(value="id") String id){
    	System.out.println("delete is called with id: "+id);
        String returnText;
        userRepository.delete(id);
        returnText = "User has been deleted from the list. Total number of users are " + userRepository.count();
        return returnText;
    }

}
