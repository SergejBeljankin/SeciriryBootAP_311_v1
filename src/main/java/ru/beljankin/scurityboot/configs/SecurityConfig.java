package ru.beljankin.scurityboot.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.beljankin.scurityboot.services.UserServiceImplDS;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserServiceImplDS userService;

    @Autowired
    public void setUserService(UserServiceImplDS userService){
        this.userService = userService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/only_for_admins/**").hasRole("ADMIN")
                .and()
                .formLogin()
//                .loginProcessingUrl("/hello_login") // можно указать по какому адресу будет обрабатываться пароль
                .and()
                .logout().logoutSuccessUrl("/");
    }

    // InMemory *
//    @Bean
//    public UserDetailsService users(){
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$10$LgDpXUu.37U2lhasLJ5EFu/IHge8wy/mP9YgJ46TZvzbVTbBT5y4C")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$10$LgDpXUu.37U2lhasLJ5EFu/IHge8wy/mP9YgJ46TZvzbVTbBT5y4C")
//                .roles("ADMIN","USER")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//
//    }

    // JdbcAuthentication
//    @Bean
//    public JdbcUserDetailsManager user(DataSource dataSource){
//            UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$10$LgDpXUu.37U2lhasLJ5EFu/IHge8wy/mP9YgJ46TZvzbVTbBT5y4C")
//                .roles("USER")
//                .build();
//            UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$10$LgDpXUu.37U2lhasLJ5EFu/IHge8wy/mP9YgJ46TZvzbVTbBT5y4C")
//                .roles("ADMIN","USER")
//                .build();
//
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        if(jdbcUserDetailsManager.userExists(user.getUsername())){
//            jdbcUserDetailsManager.deleteUser(user.getUsername());
//        }
//        if(jdbcUserDetailsManager.userExists(admin.getUsername())){
//            jdbcUserDetailsManager.deleteUser(admin.getUsername());
//        }
//        jdbcUserDetailsManager.createUser(user);
//        jdbcUserDetailsManager.createUser(admin);
//        return jdbcUserDetailsManager;
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);

        return authenticationProvider;
    }

}
