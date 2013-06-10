package com.indic.zwapper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.apache.log4j.Logger;

import com.indic.zwapper.entity.User;
import com.indic.zwapper.service.UserService;
import com.indic.zwapper.validator.UserValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value =
    { "/", "/home" })
@SessionAttributes(value =
    { "customer" })
public class HomeController
    {

    private static org.apache.log4j.Logger log = Logger.getLogger(HomeController.class);

    @Autowired(required = true)
    @Qualifier("daopowered")
    private UserService		    service;

    @Autowired
    private UserValidator		  validator;

    @RequestMapping(method = RequestMethod.GET)
    public String home( User activeUser, ModelMap model )
	{
	log.info("HomeController home: " + currentUserDetails());
	User user = new User();
	model.addAttribute("customer", user);
	return "customer";
	}

    public static UserDetails currentUserDetails()
	{
	SecurityContext securityContext = SecurityContextHolder.getContext();
	Authentication authentication = securityContext.getAuthentication();
	if (authentication != null)
	    {
	    Object principal = authentication.getPrincipal();
	    return (UserDetails) (principal instanceof UserDetails ? principal : null);
	    }
	return null;
	}

    @RequestMapping(method = RequestMethod.GET)
    public String processLogin( @ModelAttribute("customer")
    User user, BindingResult result, SessionStatus status, ModelMap model )
	{
	log.info("Process Login " + user);
	validator.validate(user, result);

	if (result.hasErrors())
	    {
	    return "customer";
	    }
	else
	    {
	    log.info("Before Service" + user);
	    user = service.autheticate(user.getUserId(), user.getPassword());
	    log.info("processLogin() " + user + " against" + user.getUserId() + "" + user.getPassword());
	    model.addAttribute("customer", user);
	    return "success";
	    }

	}

    public UserValidator getValidator()
	{
	return validator;
	}

    public void setValidator( UserValidator validator )
	{
	this.validator = validator;
	}

    }