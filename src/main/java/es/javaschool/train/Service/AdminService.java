package es.javaschool.train.Service;

import es.javaschool.train.Entity.Admin;
import es.javaschool.train.Entity.dto.AdminRegi;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService extends UserDetailsService  {

    Admin save(AdminRegi adminRegi);
}
