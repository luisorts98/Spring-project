package es.javaschool.train.Service;

import es.javaschool.train.Entity.Admin;
import es.javaschool.train.Entity.dto.AdminRegi;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService extends UserDetailsService  {

    Admin save(AdminRegi adminRegi);

    public List<Admin> consultAdmins();

    public Admin consultAdmin(int id);

    public Admin findByname(String name);

    public boolean emailExists(String email);
    public List<Admin> findByNameList(String name);
}
