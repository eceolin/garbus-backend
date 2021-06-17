package pucrs.ages.garbus.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceSendGrid {

    private Session session;

    @Value("${mail.sender.username}")
    private String username;

    @Value("${mail.sender.api-key}")
    private String sendGridAPIKey;

    @Value("${mail.server.host}")
    private String serverHost;

    @Value("${mail.server.port}")
    private String serverPort;


    public void sendTo(String recipient, String subject, String text) throws IOException {
        Email from = new Email(username);
        Email to = new Email(recipient);
        Content content = new Content("text/plain", text);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridAPIKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
