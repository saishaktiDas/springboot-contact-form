package com.example.contactform.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ContactController {
	
		@GetMapping("/contact")
	public String home() {
			
			
		return "index";
	}
		@PostMapping("/submit")
		public String submitForm(
				@RequestParam String name,
				@RequestParam String email,
				@RequestParam String message) {
			
			System.out.println(name);
			System.out.println(email);
			System.out.println(message);
			
			return "index" ;
		}
				
				

}
