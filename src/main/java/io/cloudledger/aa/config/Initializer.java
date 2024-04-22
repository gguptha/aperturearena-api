package io.cloudledger.aa.config;

import io.cloudledger.aa.domain.user.User;
import io.cloudledger.aa.domain.user.UserRepository;
import io.cloudledger.aa.domain.user.UserRole;
import io.cloudledger.aa.domain.user.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() ==0) {
            // Create a user for the instance
            User user = new User();
            user.setName("Gopinath B R");
            user.setEmail("gopinath.br@gmail.com");
            user.setPassword(passwordEncoder.encode("gops05"));
            user.setUserStatus(UserStatus.ACTIVE);
            user.setUserRole(UserRole.ROLE_User);
            user = userRepository.save(user);
        }
    }
}
