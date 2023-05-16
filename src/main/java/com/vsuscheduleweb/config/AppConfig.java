package com.vsuscheduleweb.config;


import com.vsuscheduleweb.repositories.AppUserRepository;
import com.vsuscheduleweb.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.apache.xmlbeans.impl.tool.CommandLine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final AppUserRepository appUserRepository;
    @Bean
    public UserDetailsService getUserDetails(){
        return username -> appUserRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("user with name "+ username +"is not found."));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(getUserDetails());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner teacherLoader(TeacherRepository teacherRepository){
        return args ->{

           Process p =  Runtime.getRuntime().exec("python " + System.getProperty("user.dir") + "\\src\\main\\scriptspython\\teacherParser.py");
            InputStream stdout = p.getInputStream();
            InputStream stderr = p.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stdout);
            InputStreamReader isrerr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            BufferedReader brerr = new BufferedReader(isrerr);

            String line = null;


            while ((line = br.readLine()) != null)
                if(line.equals("1")){
                    System.out.println("[+] teachers have been parsed!");
                    p.destroyForcibly();
                }

            while ((line = brerr.readLine()) != null)
                if(!line.isEmpty()) System.out.println("[-] Error teachers have not been parsed!" + "\n" + line );



        };
    }
}
