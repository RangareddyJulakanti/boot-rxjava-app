package com.essilor.spring.reactive.app.service;

import org.springframework.stereotype.Component;

import rx.Observable;

import com.essilor.spring.reactive.app.domain.User;

@Component
public class UserService {
	
	public Observable<User> findAll() {
		return Observable.<User>from(new User[] {new User("Ranga", 29), new User("Raja", 29)});
	}
	
	public Observable<User> findByName(String name) {
		return findAll().filter(u -> u.getName().toLowerCase().contains(name.toLowerCase()));
	}
	
}
