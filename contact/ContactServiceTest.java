package com.example.stream.api.service.contact;

import com.example.stream.api.service.contact.Contact;
import com.example.stream.api.service.contact.ContactService;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ContactServiceTest {

    @Test
    public void test_contact_search_for_a_single_character(){
        ContactService service = new ContactService();
        service.add(new Contact("chandra", "9916379175"));
        service.add(new Contact("likhita", "9916379175"));
        List<Contact> contacts = service.find("a");
        for (Contact contact: contacts) System.out.println(contact);
        assertThat(contacts.size()).isEqualTo(2);
        assertThat(contacts.get(0).getName()).isEqualTo("chandra");
        assertThat(contacts.get(1).getName()).isEqualTo("likhita");
    }

    @Test
    public void test_contact_search_for_multiple_character(){
        ContactService service = new ContactService();
        service.add(new Contact("chandra", "9916379175"));
        service.add(new Contact("likhita", "9916379175"));

        List<Contact> contacts = service.find("chandra");
        for (Contact contact: contacts) System.out.println(contact);

        assertThat(contacts.size()).isEqualTo(1);
        assertThat(contacts.get(0).getName()).isEqualTo("chandra");
    }
    @Test
    public void should_return_empty_contact_for_word_which_does_not_exist_in_contact_list_but_each_character_of_input_exists_in_different_contact(){
        ContactService service = new ContactService();
        service.add(new Contact("chandra", "9916379175"));
        service.add(new Contact("likhita", "9916379175"));

        List<Contact> contacts = service.find("ld");
        for (Contact contact: contacts) System.out.println(contact);

        assertThat(contacts.size()).isEqualTo(0);
    }
}
