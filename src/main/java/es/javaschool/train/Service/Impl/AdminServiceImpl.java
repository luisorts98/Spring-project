package es.javaschool.train.Service.Impl;

import org.springframework.stereotype.Service;
import es.javaschool.train.Service.AdminService;
import es.javaschool.train.Entity.Admin;
import es.javaschool.train.Entity.dto.AdminRegi;
import es.javaschool.train.Repository.AdminRepo;
import es.javaschool.train.Entity.Rol;
import java.util.Arrays;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepo adminRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepo.findByEmail(username);
        if (admin == null) {
            throw new UsernameNotFoundException("User not found with email: ");
        }

        return new User(admin.getEmail(), admin.getPassword(), mappedAuthorities(admin.getRoles())) {
            @Override
            public String getUsername() {
                return admin.getName();
            }
        };//si queremos que se vea el email como usuario quitar linea48,47,46,45,44 y poner solo el ; al final de la linea 43
    }
    private Collection<? extends GrantedAuthority> mappedAuthorities(Collection<Rol> roles) {
        System.out.println("ROLES: " + roles);
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    @Override
   public Admin save(AdminRegi adminRegi) {
        Admin admin = new Admin(adminRegi.getEmail(),
                passwordEncoder.encode(adminRegi.getPassword()),
                adminRegi.getName(),
                adminRegi.getSurname(),
                Arrays.asList(new Rol("USER")));

        return adminRepo.save(admin);
    }
}
