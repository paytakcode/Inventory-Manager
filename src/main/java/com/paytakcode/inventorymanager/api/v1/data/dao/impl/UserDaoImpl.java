package com.paytakcode.inventorymanager.api.v1.data.dao.impl;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.paytakcode.inventorymanager.api.v1.data.dao.UserDao;
import com.paytakcode.inventorymanager.api.v1.data.emum.Role;
import com.paytakcode.inventorymanager.api.v1.data.entity.UserEntity;
import com.paytakcode.inventorymanager.api.v1.data.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User DAO Implementation
 * @Author 김태산
 * @Version 0.1.2
 * @Since 2023-05-18 오후 3:45
 */

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

	private final UserRepository userRepository;

	public UserEntity saveUser(UserEntity userEntity) {
		log.info("[saveUser] param - userEntity: {}", userEntity.toString());

		UserEntity savedUserEntity = userRepository.save(userEntity);

		log.info("[saveUser] return - savedUserEntity: {}", savedUserEntity);
		return savedUserEntity;
	}

	public UserEntity updateRole(String email, Role role) {
		log.info("[updateRole] param - email: {}, role: {}", email, role);

		UserEntity userEntity = userRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("[updateRole] User not found with email: " + email));

		userEntity.setRole(role);

		UserEntity updatedUserEntity = userRepository.save(userEntity);

		log.info("[updateRole] return - updatedUserEntity: {}", updatedUserEntity);
		return updatedUserEntity;
	}
}
