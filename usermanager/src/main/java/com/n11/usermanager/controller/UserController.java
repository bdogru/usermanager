package com.n11.usermanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.n11.usermanager.dao.UserRepository;
import com.n11.usermanager.domain.User;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @RequestMapping(value={"/AddUser.htm"},method=RequestMethod.GET)
    public String showForm(ModelMap model){
    	User user = new User();
    	model.addAttribute("user", user);
        return "AddUser";
    }

    @RequestMapping(value="user/update/{id}.htm",method=RequestMethod.GET)
    public String showFormForUpdate(ModelMap model, @PathVariable(value="id") String id){
    	User user = userRepository.findOne(id);
    	model.addAttribute("user", user);
        return "AddUser";
    }
    
    @RequestMapping(value="/AddUser.htm",method=RequestMethod.POST)
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
}
