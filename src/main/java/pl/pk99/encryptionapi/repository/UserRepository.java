package pl.pk99.encryptionapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pk99.encryptionapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String username);
}
