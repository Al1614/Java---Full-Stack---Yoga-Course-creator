package com.alext.yoga.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alext.yoga.models.Course;
import com.alext.yoga.models.LoginUser;
import com.alext.yoga.models.User;
import com.alext.yoga.services.CourseService;
import com.alext.yoga.services.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService users;
	@Autowired
	private CourseService courses;
    
    @GetMapping("/")
    public String index(Model model) {
    
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
    	User user = users.register(newUser, result);
    	
        
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        
        session.setAttribute("userId", user.getId());
    
        return "redirect:/home";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        User user = users.login(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("userId", user.getId());
    
        return "redirect:/home";
    }
   
    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
    	
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	Long userId = (Long) session.getAttribute("userId");
		User user = users.findById(userId);
    	model.addAttribute("courses", courses.all());
    	model.addAttribute("user", user);
    	model.addAttribute("user", users.findById((Long)session.getAttribute("userId")));
    	return "home.jsp";
    }
    
    @GetMapping("/addCourse")
    public String addCourse(@ModelAttribute("course") Course course, Model model, HttpSession session) {
    	
    	User user = users.findById((Long)session.getAttribute("userId"));
    	model.addAttribute("user", user);
    	
    	return "create_yoga.jsp";
    }
    
    @PostMapping("/courses")
    public String createCourse(@Valid @ModelAttribute("course") Course course, BindingResult result) {

    	if (result.hasErrors()) {
    		return "create_yoga.jsp";
    	}
    	
    	courses.create(course);
    	
    	return "redirect:/home";
    }
    
    @GetMapping("/courses/{id}")
    public String showCourse(Model model, @PathVariable("id") Long id, HttpSession session) {
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	
    	Course course = courses.findById(id);
    	model.addAttribute("course", course);
    	model.addAttribute("user", users.findById((Long)session.getAttribute("userId")));
    	
    	return "view_yoga.jsp";
    }
    
    @GetMapping("/courses/edit/{id}")
	public String openEdit(@PathVariable("id") Long id, HttpSession session, Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Course course = courses.findById(id);
		model.addAttribute("course", course);
		return "edit_yoga.jsp";
	}
	
    @PostMapping("/courses/edit/{id}")
	public String editCourse(
			@PathVariable("id") Long id, 
			@Valid @ModelAttribute("course") Course course, 
			BindingResult result, 
			HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		Long userId = (Long) session.getAttribute("userId");
		
		User user = users.findById(userId);
		
		if(result.hasErrors()) {
			return "edit_yoga.jsp";
		} else {
			course.setUser(user);
			courses.updateCourse(course);
			return "redirect:/home";
		}
	}
    
   @RequestMapping("/courses/delete/{id}")
	public String deleteCourse(@PathVariable("id") Long id, HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/logout";
		}
		
		Course course = courses.findById(id);
		courses.deleteCourse(course);
		
		return "redirect:/home";
	}
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
}

