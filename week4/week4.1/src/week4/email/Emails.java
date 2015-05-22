package week4.email;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.*;

public class Emails {

    public void emailFunc() throws MalformedURLException, EmailException {

        EmailAttachment attachment = new EmailAttachment();
        attachment.setURL(new URL("http://www.apache.org/images/asf_logo_wide.gif"));
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Picture of John");
        attachment.setName("John");

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.bg");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("wtfaremdm@gmail.com", "wtfaremd"));
        email.setSSLOnConnect(true);

        email.setDebug(true);
        email.addTo("niki_kab@abv.bg");
        email.setFrom("wtfaremdm@gmail.com");
        email.setSubject("The picture");
        email.setMsg("Here is the picture you wanted");

        // add the attachment
        email.attach(attachment);

        // send the email
        email.send();
    }

    public static void main(String[] args) throws MalformedURLException, EmailException {
        new Emails().emailFunc();
    }

}
