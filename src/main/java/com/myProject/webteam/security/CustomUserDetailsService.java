package com.myProject.webteam.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myProject.webteam.models.User;
import com.myProject.webteam.respositories.UserRespository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRespository userRepo;
	@Override
	@Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.myProject.webteam.models.User user = userRepo.findFirstByNameLogin(username);
        if(user != null) {
        	org.springframework.security.core.userdetails.User authUser = new org.springframework.security.core.userdetails.User(
                    user.getNameLogin(),
                    user.getPassword(),
                    user.getProjectRoles().stream()
                    .map(projectRole -> new SimpleGrantedAuthority(projectRole.getRole().getName()))
                    .collect(Collectors.toList())
            );
        	//System.out.println(user.getProjectRoles().getFirst().getRole().getName());
            return authUser;
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
	
}
