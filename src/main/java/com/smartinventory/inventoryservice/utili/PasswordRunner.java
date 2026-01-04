package com.smartinventory.inventoryservice.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Hashed password: " + encoder.encode("admin123"));
    }
}
