package es.javaschool.train.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import es.javaschool.train.Entity.Admin;

import java.util.List;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
    public Admin findByEmail(String email);


    @Query("SELECT a FROM Admin a WHERE a.name= ?1")
    public Admin findByName(String name);

    @Query("SELECT a FROM Admin a WHERE a.name= ?1")
    public List<Admin> findByNameList(String name);
}
