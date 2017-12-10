package com.epam.university.spring.enote.services;

import com.epam.university.spring.enote.model.User;

/**
 * Interface for declaring special (not general CRUD's support operations of <code>User</code>
 * class); should be used for interactions with Spring.
 */
public interface UserService extends GenericService<User> {
}
