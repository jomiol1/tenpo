package com.tenpo.api.service;

import com.tenpo.api.entity.UserEntity;
import com.tenpo.api.handler.exception.GeneralException;
import com.tenpo.api.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
    private UserRepository usuarioRepository;


    public String login(String username, String password) {
    	String pwdEncode = Base64.getEncoder().encodeToString(password.getBytes());
    	
    	UserEntity usuarioEntity= usuarioRepository.findByUsernameAndPassword(username, pwdEncode).orElseThrow(
    			()-> new GeneralException(1, "Credenciales incorrectas"));

    	
    	return this.getJWTToken(username);

    }
    
	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("tenpoJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 6000000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
	public void registry(String username, String password) {
		Optional<UserEntity> usuarioEntityOptional = usuarioRepository.findByUsername(username);
		String pwdEncode = Base64.getEncoder().encodeToString(password.getBytes());
		
		if(usuarioEntityOptional.isPresent())
			throw new GeneralException(1, String.format("El usuario [%s] ya se encuentra registrado", username));
		
		usuarioRepository.save(UserEntity.builder().username(username).password(pwdEncode).fechaCreacion(LocalDateTime.now()).build());
    }

}
