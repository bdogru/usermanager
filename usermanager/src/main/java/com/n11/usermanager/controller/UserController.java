package com.n11.usermanager.controller;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.n11.usermanager.dao.UserRepository;
import com.n11.usermanager.domain.User;
import com.n11.usermanager.validation.ListingParameters;
import com.n11.usermanager.validation.View;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @RequestMapping(value={"/add","/AddUser.htm"},method=RequestMethod.GET)
    public String showForm(ModelMap model){
        return "AddUser";
    }

    @RequestMapping(value="/update/{id}",method=RequestMethod.GET)
    public String showFormForUpdate(ModelMap model, @PathVariable(value="id") String id){
    	User user = userRepository.findOne(id);
    	model.addAttribute("user", user);
        return "AddUser";
    }

    @RequestMapping(value="/get/{id}",method=RequestMethod.POST)
    @JsonView(View.Summary.class)
    public @ResponseBody User get(ModelMap model, @PathVariable(value="id") String id){
    	User user = userRepository.findOne(id);
    	return user;
    }
    
    @RequestMapping(value={"/user/save","/AddUser.htm"},method=RequestMethod.POST)
    public @ResponseBody String addUser(@ModelAttribute(value="user") User user, BindingResult result ){
        String returnText;
        if(!result.hasErrors()){
            userRepository.save(user);
            returnText = "User has been added to the list. Total number of users are " + userRepository.count();
        }else{
            returnText = "Sorry, an error has occur. User has not been added to list.";
        }
        return returnText;
    }

    @RequestMapping(value="/ShowUsers.htm",method=RequestMethod.GET)
    public String showUsers(ModelMap model){
    	List<User> users = userRepository.findAll();
        model.addAttribute("Users", users);
        return "ShowUsers";
    }
    

	@RequestMapping(value = "/list",method=RequestMethod.POST)
    @JsonView(View.Summary.class)
	public @ResponseBody Page<User> showUsers(
			@ModelAttribute(value = "params") ListingParameters params) {
//		System.out.println("hello");
//		if (!result.hasErrors()) {
//			return null;
//		}
		if (params == null || ((params.getMax() == null || params.getMax() <= 0) && StringUtils.isEmpty(params.getSorter()))) {
			Page<User> page= new PageImpl<User>(userRepository.findAll());
			System.out.println(page.getTotalElements());
			return page;
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
			return new PageImpl<User>(userRepository.findAll(srt));
		}
		return userRepository.findAll(pgbl);
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
    @JsonView(View.Summary.class)
    public @ResponseBody String deleteUser(@PathVariable(value="id") String id){
        String returnText;
        userRepository.delete(id);
        returnText = "User has been deleted from the list. Total number of users are " + userRepository.count();
        return returnText;
    }
}
