import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactManagement {
    /*
    The main reason I've chosen ConcurrentHashMap, because doesn't allow to store null keys or values,
    and avoid NullPointerException, besides high performance.
    */
    Map<String, Contact> contactList = new ConcurrentHashMap<>();

    public void addContact(String firstName, String lastName, String phoneNumber){
        Contact contact = new Contact(firstName, lastName, phoneNumber);
        validContact(contact);
        checkIfContactExist(contact);
        contactList.put(generateKey(contact), contact);
    }

    private void validContact(Contact contact) {
        contact.validFirstName();
        contact.getLastName();
        contact.validPhoneNumber();
    }

    private void checkIfContactExist(Contact contact) {
        if(contactList.containsKey(generateKey(contact))){
            throw new RuntimeException("This will duplicate !");
        }
    }

    private String generateKey(Contact contact){
        return String.format("%s-%s", contact.getFirstName(), contact.getLastName());
    }

    public Collection<Contact> getAllContact(){
        return contactList.values();
    }

}
