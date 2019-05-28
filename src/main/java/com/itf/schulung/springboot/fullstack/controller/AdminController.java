package com.itf.schulung.springboot.fullstack.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itf.schulung.springboot.fullstack.model.AccountHelper;
import com.itf.schulung.springboot.fullstack.model.Customer;
import com.itf.schulung.springboot.fullstack.model.Login;
import com.itf.schulung.springboot.fullstack.model.Role;
import com.itf.schulung.springboot.fullstack.model.User;
import com.itf.schulung.springboot.fullstack.repositorys.AccountHelperRepository;
import com.itf.schulung.springboot.fullstack.repositorys.LoginRepository;
import com.itf.schulung.springboot.fullstack.repositorys.RoleRepository;
import com.itf.schulung.springboot.fullstack.repositorys.UserRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AccountHelperRepository accountHelperRepo;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	private LoginRepository logRepository;

	@PostMapping(value = "/postAccountHelper", consumes = "application/json")
	public ResponseEntity<?> postAccountHelper(@RequestBody AccountHelper receivedAccountHelper,
			HttpServletRequest request) {
		List<AccountHelper> accountHelpers = accountHelperRepo.findAll();
		receivedAccountHelper.setName(request.getParameter("name"));
		String salary = request.getParameter("salary");
		receivedAccountHelper.setSalary(Double.parseDouble("salary"));
		receivedAccountHelper.setEmail(request.getParameter("email"));
		receivedAccountHelper.setPhone(request.getParameter("phone"));

		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));

		Role role = new Role();
		user.setRoles(new HashSet<Role>(Arrays.asList(role)));
		userRepository.save(user);
		Login log = new Login();
		log.setOperation("Account helper added: ID: " + receivedAccountHelper.getId());
		log.setTime(new Timestamp(System.currentTimeMillis()));

		String username = request.getRemoteUser();

		User activeUser = userRepository.findByUsername(username);
		log.setUser((activeUser));

		logRepository.save(log);

		return new ResponseEntity<AccountHelper>(receivedAccountHelper, HttpStatus.OK);
	}

	@PostMapping(value = "/report")
	public ModelAndView searchEmployee(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("admin/showReport");
		User user = userRepository.findByUsername((request.getParameter("id")));
		DateFormat format = new SimpleDateFormat("yyyy-M-dd");
		Date from = format.parse(request.getParameter("from"));
		Date to = format.parse(request.getParameter("to"));

		List<Login> report = logRepository.findByUserAndTimeBetween(user, from, to);
		modelAndView.addObject("report", report);
		return modelAndView;
	}

	@GetMapping(value = "/showReport")
	public String showReport() {
		return "admin/showReport";
	}
}