package es.javaschool.train.Repository;

import es.javaschool.train.Entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.javaschool.train.Entity.Admin;
@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
    public Admin findByEmail(String email);
}
