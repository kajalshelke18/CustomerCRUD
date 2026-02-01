package com.example.demo.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService cs;
	
	@PostMapping("add")
	public void add(@RequestBody Customer c) {
		cs.add(c);
	}
	@PostMapping("add all")
	public void addAll(@RequestBody List<Customer> List) {
		cs.addAll(List);
	}
	
	@GetMapping("display")
	public List<Customer> display(){
		return cs.display();
	}
	
	@DeleteMapping("delete/{id}")
// ? -> Any
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Customer temp = cs.delete(id);
		if(temp ==null) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Not Found !!");
	}
		return ResponseEntity.ok(temp);
	}
	
	@PostMapping("search/{id}")
	public Customer search(@PathVariable Integer id) {
		
		return cs.search(id);
	}

	@PostMapping("search/mob/{mob}")
	public Customer searchMob(@PathVariable String mob) {
		
		return cs.findByMob(mob);
	}

}
