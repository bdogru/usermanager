package com.n11.usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value="/get/{id}",method=RequestMethod.POST)
    public @ResponseBody String get(@PathVariable(value="id") String id){
    	User user = userRepository.findOne(id);
    	return UserMarshaller.toJsonObject(user).toJSONString();
    }
    
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody String addUser(
    		@ModelAttribute(value="user") User user, 
    		BindingResult result ){
        String returnText;
        if(!result.hasErrors()){
            userRepository.save(user);
            returnText = "User has been added to the list. Total number of users are " + userRepository.count();
        }else{
            returnText = "Sorry, an error has occur. User has not been added to list.";
        }
        return returnText;
    }

	@RequestMapping(value = "/list",method=RequestMethod.POST)
	public @ResponseBody String showUsers(
			@ModelAttribute(value = "params") ListingParameters params,
			BindingResult result ) {
		if (!result.hasErrors()) {
			return null;
		}
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
        String returnText;
        userRepository.delete(id);
        returnText = "User has been deleted from the list. Total number of users are " + userRepository.count();
        return returnText;
    }

}
