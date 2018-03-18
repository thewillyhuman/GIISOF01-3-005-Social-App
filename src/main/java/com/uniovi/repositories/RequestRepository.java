/*
 * This source file is part of the SocialApp open source project.
 *
 * Copyright (c) 2018 willy and the SocialApp project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Request;
import com.uniovi.entities.User;

public interface RequestRepository extends CrudRepository<Request, Long> {

	@Query("SELECT r FROM Request r WHERE r.receptor = ?1 ORDER BY r.id ASC ")
	Page<Request> findAllByUser(Pageable pageable, User user);

	
}
