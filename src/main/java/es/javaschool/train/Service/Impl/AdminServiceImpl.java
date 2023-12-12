package es.javaschool.train.Service.Impl;

import org.springframework.stereotype.Service;
import es.javaschool.train.Service.AdminService;
import es.javaschool.train.Entity.Admin;
import es.javaschool.train.Entity.dto.AdminRegi;
import es.javaschool.train.Repository.AdminRepo;
import es.javaschool.train.Entity.Rol;
import java.util.Arrays;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
    public List<Admin> consultAdmins() {
        return this.adminRepo.findAll();
    }

    @Override
    public Admin findByname(String name) {
        return this.adminRepo.findByName(name);
    }
    @Override
    public List<Admin> findByNameList(String name) {
        return this.adminRepo.findByNameList(name);
    }

    @Override
    public boolean emailExists(String email) {
        return adminRepo.findByEmail(email) != null;
    }
    @Override
    public Admin consultAdmin(int id) {
        return this.adminRepo.findById(id).get();
    }
    @Override
    public Admin save(AdminRegi adminRegi) {
        System.out.println("Fecha de nacimiento (antes de la conversión): " + adminRegi.getDateOfBirth());

        Admin admin = new Admin(adminRegi.getEmail(),
                passwordEncoder.encode(adminRegi.getPassword()),
                adminRegi.getName(),
                adminRegi.getSurname(),
                adminRegi.getDateOfBirth(),
                Arrays.asList(new Rol("USER")));

        return adminRepo.save(admin);
    }
}
