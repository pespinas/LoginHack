package com.hackathon.security;

import org.springframework.security.core.GrantedAuthority;

import com.hackathon.models.Authority;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority{
	private static final long serialVersionUID = 3182154365831196334L;
	
	// Agregamos una propiedad de tipo user
	final private Authority authority;
	
	@Override
	public String getAuthority() {
		// Retonar� el valor del autorityName contenido en la propiedad user
		return authority.getName().toString(); 
	}

}
