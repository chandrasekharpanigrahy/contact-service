package com.example.stream.api.service.contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactService {
    // this map will hold characters to contacts to help search of contacts from mobile
    private Map<Character, List<Contact>> characterToContacts = new HashMap<>();
    private List<Contact> contacts = new ArrayList<>();

    public void add(Contact contact) {
        contacts.add(contact);
        insertContactToMap(contact);
        
    }

    private void insertContactToMap(Contact contactToInsert) {
        String name = contactToInsert.getName();
        for (int i = 0; i <= name.length()-1; i++){
            char key = name.charAt(i);
            List<Contact> contacts = characterToContacts.get(key);
            if(contacts == null || contacts.isEmpty()){
                List<Contact> contact = new ArrayList<>();
                contact.add(contactToInsert);
                characterToContacts.put(key, contact);
            }
            else{
                boolean isContactExists = false;
                for (Contact contact : contacts){
                    if (contactToInsert.equals(contact)){
                        isContactExists = true;
                    }
                }
                if (!isContactExists) contacts.add(contactToInsert);
            }
        }
    }

    public List<Contact> find(String inputToSearch) {
        List<Contact> result = new ArrayList<>();
        // create a map to check the counts for a contact appears so that
        // we can choose the best contact to return based on most appearance on the map
        Map<Contact, Integer> contactToCountSearch = new HashMap<>();
        for (int i = 0; i <= inputToSearch.length()-1; i++){
            List<Contact> contacts = characterToContacts.get(inputToSearch.charAt(i));
            for (int j = 0; j <= contacts.size()-1; j++){
                Integer count = contactToCountSearch.get(contacts.get(j));
                if (count == null) { // For first insertion to map
                    count = 0;
                }
                contactToCountSearch.put(contacts.get(j), count + 1);
            }
        }
        // at this point a map has been created with contact and count of search by user input characters
        for(Map.Entry<Contact, Integer> entry: contactToCountSearch.entrySet()) {
            // if less then input string size means user does not want that contact to appear on his screen for example if user
            // search for abc and map has a, b, c contacts(anm, bxz, cyz) then with one contact for each letter then it means user does not want these records but
            // if a contact found with count 3 means the contact contains all the three letters "abc"(ex -> abcxyz). user wants this as his all letters found
            if (entry.getValue() >= inputToSearch.length()) result.add(entry.getKey());
        }
        return result;
    }

}
