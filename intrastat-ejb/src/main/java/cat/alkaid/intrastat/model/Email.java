package cat.alkaid.intrastat.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xavier on 3/09/15.
 */


public class Email {
    private final String subject;
    private final String body;

    private final List<String> address;

    public Email(String subject, String body, String ...address){
        this.subject = subject;
        this.body = body;
        this.address = Arrays.asList(address);

    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public List<String> getAddress() {
        return address;
    }
}
