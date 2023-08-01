package com.basicapp.springbootbasicapp.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utilizado para encriptar las contraseñas ya en la base de datos
 */
public class PassGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
        System.out.println(passwordEncoder.encode("123"));
        // $2a$04$gMDPp/IG5sMh1/WYLNhOqOZjflW0TREQAxn.sJ2Ky3phF13./UjES
    }
}
