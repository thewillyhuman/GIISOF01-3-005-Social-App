
package com.uniovi.controllers;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uniovi.entities.User;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

/**
 * Instance of UsersController.java
 * 
 * @author
 * @version
 */
@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private SignUpFormValidator signUpFormValidator;

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute @Validated User user, BindingResult result, Model model) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}

		// user.setRole(rolesService.getRoles()[0]);
		usersService.saveUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

	@RequestMapping("/user/list")
	public String getList(Model model, Pageable pageable,
			@RequestParam(value = "", required = false) String searchText) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		if (searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUsersByEmailAndName(searchText,pageable);
		} else {
			users = usersService.getUsers(pageable);
		}
		model.addAttribute("userList", users.getContent());
		model.addAttribute("page", users);

		return "user/list";
	}

	@RequestMapping("/user/addFriendRequest/{id}")
	public String addFriendRequest(Model model, @PathVariable Long id) {
		User friend = usersService.getUser(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		friend.getRequests().add(activeUser);
		usersService.updateUser(activeUser);
		return "redirect:/user/list";
	}

	@RequestMapping("/user/acceptFriendRequest/{id}")
	public String acceptFriendRequest(Model model, @PathVariable Long id) {
		User friend = usersService.getUser(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		activeUser.acceptRequestFrom(friend);
		usersService.saveUser(activeUser);
		return "redirect:/user/peticiones";
	}

	@RequestMapping("/user/peticiones")
	public String getPeticiones(Model model, Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);

		Page<User> users = new PageImpl<User>(new LinkedList<User>(activeUser.getRequests()));

		model.addAttribute("peticiones", users.getContent());
		model.addAttribute("page", users);
		return "user/peticiones";
	}

	@RequestMapping("/user/amigos")
	public String getAmigos(Model model, Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);

		Page<User> users = new PageImpl<User>(new LinkedList<User>(activeUser.getFriends()));

		model.addAttribute("amigos", users.getContent());
		model.addAttribute("page", users);
		return "user/amigos";
	}

	@RequestMapping("/publication/add")
	public String getAmigos(Model model) {
		return "/publication/add";
	}

}
