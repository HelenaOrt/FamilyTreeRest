/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.FamilyTreeRest.FamilyTreeRest.services.impl;

import com.FamilyTreeRest.FamilyTreeRest.repositories.WebUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//siempre hay que sobreescribirlo porque spring no sabe que repositorio usamos
//aqui le dicen que cargue el WebUserRepository que es el que está autenticado

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	private final WebUserRepository WebUserRepository;

	public CustomUserDetailsServiceImpl(WebUserRepository WebUserRepository) {
		this.WebUserRepository = WebUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return WebUserRepository.findByNameIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException(
				String.format("No user found with username '%s'.", username)));
	}

}
