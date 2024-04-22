package io.cloudledger.aa.domain.user;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> findAll();

    User findById(UUID id);

    User findByEmail(String email);
}
