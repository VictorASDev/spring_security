package com.eazybytes.springsecuritysection5.config;

import com.eazybytes.springsecuritysection5.model.Customer;
import com.eazybytes.springsecuritysection5.repository.CustomerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EazyBankUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public EazyBankUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User details not found for the user " + username)
        );


        List<GrantedAuthority> autorities = List.of(new SimpleGrantedAuthority(customer.getRole()));

        System.out.println("User returned: " + customer.getEmail());
        System.out.println("pwd returned: " + customer.getPwd());

        return new User(customer.getEmail(), customer.getPwd(), autorities);
    }


}
