package com.itf.schulung.springboot.fullstack.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itf.schulung.springboot.fullstack.model.AccountHelper;
import com.itf.schulung.springboot.fullstack.model.Customer;
import com.itf.schulung.springboot.fullstack.model.Login;
import com.itf.schulung.springboot.fullstack.model.Role;
import com.itf.schulung.springboot.fullstack.model.User;
import com.itf.schulung.springboot.fullstack.repositorys.CustomerRepository;
import com.itf.schulung.springboot.fullstack.repositorys.LoginRepository;
import com.itf.schulung.springboot.fullstack.repositorys.RoleRepository;
import com.itf.schulung.springboot.fullstack.repositorys.UserRepository;

@RestController
@RequestMapping("/login")
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	private LoginRepository logRepository;

	@GetMapping(value = "/getCustomers")
	public ResponseEntity<?> getAllCustomers() {
		List<Customer> allCustomers = customerRepo.findAll();
		return new ResponseEntity<>(allCustomers, HttpStatus.OK);
	}

	@PostMapping(value = "/postCustomer", consumes = "application/json")
	public ResponseEntity<?> postAccountHelper(@RequestBody Customer receivedCustomer, HttpServletRequest request) {
		List<Customer> customers = (List<Customer>) customerRepo.findAll();
		receivedCustomer.setFirstName(request.getParameter("firstName"));
		receivedCustomer.setLastName(request.getParameter("lastName"));
		receivedCustomer.setAge(receivedCustomer.getAge());
		receivedCustomer.setTelephone(request.getParameter("telephone"));
		receivedCustomer.setEmail(request.getParameter("email"));

		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));

		Role role = new Role();
		user.setRoles(new HashSet<Role>(Arrays.asList(role)));
		userRepository.save(user);
		Login log = new Login();
		log.setOperation("Account helper added: ID: " + receivedCustomer.getCustomer_id());
		log.setTime(new Timestamp(System.currentTimeMillis()));

		String username = request.getRemoteUser();

		User activeUser = userRepository.findByUsername(username);
		log.setUser((activeUser));

		logRepository.save(log);

		return new ResponseEntity<Customer>(receivedCustomer, HttpStatus.OK);
	}

	@PutMapping(value = "/update", consumes = "application/json")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer receivedCustomer,
			HttpServletRequest request) {
		Optional<Customer> customer = customerRepo.findById(Long.parseLong(request.getParameter("id")));
		if (!customer.isPresent()) {
			// No customer was present, we therefore should tell the requester this
			return new ResponseEntity<>
			("No customer found with ID: " + receivedCustomer.getCustomer_id(),
					HttpStatus.BAD_REQUEST);
		}
		
		receivedCustomer.setFirstName(request.getParameter("firstName"));
		receivedCustomer.setLastName(request.getParameter("lastName"));
	    String age= request.getParameter("age");
		receivedCustomer.setAge(receivedCustomer.getAge());
		receivedCustomer.setTelephone(request.getParameter("telephone"));
		receivedCustomer.setEmail(request.getParameter("email"));


		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));

		Role role = new Role();
		user.setRoles(new HashSet<Role>(Arrays.asList(role)));
		userRepository.save(user);
		Login log = new Login();
		log.setOperation("Account helper added: ID: " + receivedCustomer.getCustomer_id());
		log.setTime(new Timestamp(System.currentTimeMillis()));

		String username = request.getRemoteUser();

		User activeUser = userRepository.findByUsername(username);
		log.setUser((activeUser));

		logRepository.save(log);

		return new ResponseEntity<Customer>(receivedCustomer, HttpStatus.OK);
	}

	@PostMapping(value = "/report")
	public ModelAndView searchCustomer(HttpServletRequest request) throws Exception {
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
