package io.github.fabiocintra.event_management.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.UUID;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true,jsr250Enabled = true)
public class SecurityApplication {

    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(securityCsrf -> securityCsrf.disable())
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> {
                    form.loginProcessingUrl("/login");
                    form.successHandler((request, response, authentication) -> {
                        String jwt = jwtService.generateToken(authentication);

                        ResponseCookie cookie = ResponseCookie.from("ACCESS_TOKEN",jwt)
                                .httpOnly(true)
                                .path("/")
                                .maxAge(Duration.ofHours(1))
                                .secure(false)
                                .build();

                        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
                        response.setStatus(HttpServletResponse.SC_OK);
                    });
                }) // botar minha tela de login depois
                .authorizeHttpRequests(authRequest -> {
                    authRequest.anyRequest().authenticated();
                })
                .exceptionHandling(Customizer.withDefaults()) //botar depois prara mandar para a tela de login
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() throws Exception {
        RSAKey rsaKey = generateRSAKey();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    private RSAKey generateRSAKey() throws Exception {
        KeyPairGenerator keyPair = KeyPairGenerator.getInstance("RSA");
        keyPair.initialize(2048);
        KeyPair keys = keyPair.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keys.getPublic();
        RSAPrivateKey  privateKey = (RSAPrivateKey) keys.getPrivate();
        return new RSAKey
                .Builder(publicKey)
                .keyID(UUID.randomUUID().toString())
                .privateKey(privateKey)
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext>  jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext>  jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

}
