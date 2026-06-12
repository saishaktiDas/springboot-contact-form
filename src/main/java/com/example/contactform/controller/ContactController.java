package com.example.contactform.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.contactform.entity.Contact;
import com.example.contactform.repository.ContactRepository;
import java.util.List;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contact")
	public String home() {
			
			
		return "index";
	}
		@PostMapping("/submit")
		public String submitForm(
				@RequestParam String name,
				@RequestParam String email,
				@RequestParam String message) {

            Contact contact = new Contact();

            contact.setName(name);
            contact.setEmail(email);
            contact.setMessage(message);

            contactRepository.save(contact);
			
			return "index" ;
		}
	@GetMapping("/contacts")
	@ResponseBody
	public List<Contact> getAllContacts() {

		return contactRepository.findAll();
	}
				

}
