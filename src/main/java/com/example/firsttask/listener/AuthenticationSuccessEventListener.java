package com.example.firsttask.listener;
import com.example.firsttask.dao.entity.UserEntity;
import com.example.firsttask.dao.repository.UserRepository;
import com.example.firsttask.service.MailService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final MailService emailService;
    private final UserRepository userRepository;

    public AuthenticationSuccessEventListener(MailService emailService, UserRepository userRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        String userEmail = getUserEmailByUsername(username);
        if (userEmail != null) {
            emailService.sendLoginSuccessEmail(userEmail);
        }
    }

    private String getUserEmailByUsername(String firstName) {
        UserEntity user = userRepository.findByFirstName(firstName);
        return user != null ? user.getEmail() : null;
    }
}
