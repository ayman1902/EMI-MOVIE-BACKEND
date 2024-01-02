package com.example.emi_movies.securite;

import com.example.emi_movies.model.Users;
import com.example.emi_movies.repo.UsersRepo;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
    @Value("${jwt.secret}")
    private String secretKey;
    private final UsersRepo usersRepo;

    public SecurityConfig(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        PasswordEncoder passwordEncoder = passwordEncoder();
        /*return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("12345")).authorities("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("12345")).authorities("USER","ADMIN").build()
        );*/
        List<Users> userList = this.usersRepo.findAll();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        userList.forEach(users -> System.out.println("USERNAME: "+users.getNom()+users.getId()+" pass "+users.getPassword()));

        // Loop over users and add them to InMemoryUserDetailsManager
        for (Users user : userList) {
            UserDetails userDetails = User
                    .withUsername(user.getUsernamelog())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .authorities("USER") // Assuming all users have the USER role
                    .build();
            manager.createUser(userDetails);
        }

        /*UserDetails anonymousUser = User
                .withUsername("anonymousUser")
                .password("{noop}password") // You can set a password for the anonymous user
                .authorities("ROLE_ANONYMOUS")
                .build();
        manager.createUser(anonymousUser);*/

        return manager;


    }
    /*@Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Users user = usersRepo.findByNom(username);
            if (user != null) {
                return org.springframework.security.core.userdetails.User
                        .withUsername(user.getNom())
                        .password(user.getPassword())
                        .roles("USER") // Assuming all users have the USER role
                        .build();
            } else {
                throw new IllegalArgumentException("User not found with username: " + username);
            }
        };
    }*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(crsf -> crsf.disable())
                .authorizeHttpRequests(ar -> ar
                        .requestMatchers("/users/**", "/auth/login/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oa -> oa.jwt(Customizer.withDefaults()))
                .build();
    }

    @Bean
    JwtEncoder jwtEncoder(){
        //String secretKey="9faa372517ac1d389758d3750fc07acf00f542277f26fec1ce4593e93f64e338" ;
        return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));
    }
    @Bean
    JwtDecoder jwtDecoder() {
        //String secretKey = "9faa372517ac1d389758d3750fc07acf00f542277f26fec1ce4593e935646338";

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "RSA");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(daoAuthenticationProvider);
    }

}