package com.example.contactform.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.contactform.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
