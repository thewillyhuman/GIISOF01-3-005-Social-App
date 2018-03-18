
package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.Request;
import com.uniovi.entities.User;
import com.uniovi.services.RequestsService;
import com.uniovi.services.UsersService;

@Controller
public class RequestsController {

	@Autowired
	private RequestsService requestsService;

	@Autowired
	private UsersService usersService;

	@RequestMapping("/peticiones/list")
	public String getList(Model model, Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		Page<Request> friendRequests = requestsService.getRequestsForUser(activeUser, pageable);
		model.addAttribute("peticiones", friendRequests.getContent());
		model.addAttribute("page", friendRequests);
		return "peticiones/list";
	}

	@RequestMapping("peticiones/user/acceptRequest/{idEmisor}/{idRequest}")
	public String acceptFriendRequest(Model model, @PathVariable Long idEmisor, @PathVariable Long idRequest) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User receptor = usersService.getUserByEmail(email);
		User emisor = usersService.getUser(idEmisor);
		usersService.acceptRequest(emisor, receptor);
		requestsService.deleteRequest(emisor, receptor, idRequest);
		return "redirect:/peticiones/list/update";
	}

	@RequestMapping("/peticiones/list/update")
	public String updateList(Model model, Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		Page<Request> requests = requestsService.getRequestsForUser(user, pageable);
		model.addAttribute("peticiones", requests.getContent());
		model.addAttribute("page", requests);
		return "peticiones/list :: tablePeticiones";
	}

}
