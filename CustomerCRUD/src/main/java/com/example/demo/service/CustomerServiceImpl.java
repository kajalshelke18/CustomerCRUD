package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InvalidId;
import com.example.demo.exception.InvalidMobileNumber;
import com.example.demo.model.Customer;
import com.example.demo.repositry.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository cr;
	
	@Override
	public void add(Customer customer) {
		// TODO Auto-generated method stub
		String mob = customer.getMob();
		

		if(mob.length() == 10) {
			if(mob.charAt(0) == '0' ||mob.charAt(0) == '1'||  mob.charAt(0) == '2' || mob.charAt(0) == '3' ||mob.charAt(0) == '4' ||mob.charAt(0) == '5' )
			throw new InvalidMobileNumber("INVALID MOBILE NUMBER");
			
			for (int i = 0; i < mob.length(); i++ ) {
				if(!Character.isDigit(mob.charAt(i)))
					throw new InvalidMobileNumber("INVALID MOBILE NUMBER");
			}
		}else
			throw new InvalidMobileNumber("INVALID MOBILE NUMBER");
		
		customer.setMob(mob);
		
		Integer id=customer.getId();
		
		if(id <= 0) {
			throw new InvalidId("Invalid ID");		
		}
		List<Customer> list=cr.findAll();
		
		for(Customer customer1:list) {
			if(id.equals(customer1.getId())) {
				throw new InvalidId("Id Already Exists!!!");
			}
		}
		
		cr.save(customer);   //Insert	
	}
	

	@Override
	public List<Customer> display() {
		// TODO Auto-generated method stub
		return cr.findAll();   //Select *from customer
	}

	@Override
	public Customer delete(Integer id) {
		// TODO Auto-generated method stub
		
		//search
		if(cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			cr.deleteById(id);  //Delete
			return temp;
		}
		return null;
	}

	@Override
	public void update(Customer customer, Integer id) {
		// TODO Auto-generated method stub
		customer.setId(id);
		cr.save(customer);
		
	}

	@Override
	public Customer search(Integer id) {
		// TODO Auto-generated method stub
		
		if(cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			
			return temp;
		}
		return null;
	}
	
	@Override
	public void addAll(List<Customer>List) {
		
		cr.saveAll(List);
	}


	@Override
	public Customer findByMob(String mob) {
		// TODO Auto-generated method stub
		return cr.findByMob(mob);
	}
}
