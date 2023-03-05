package pl.interal.app.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.interal.app.base.data.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
}
