package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.demo.user.Role;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateRoles() {
		Role roleAdmin = new Role("Administrator");
		Role roleEditor = new Role("Editor");
		Role roleVisitor = new Role("Visitor");
		
		entityManager.persist(roleAdmin);
		entityManager.persist(roleEditor);
		entityManager.persist(roleVisitor);
		
	}
	
	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User user = new User("cst18023@std.uwu.ac.lk", "CST18023");
		user.addRole(roleAdmin);
		
		repo.save(user);
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		Role roleEditor = entityManager.find(Role.class, 2);
		Role roleVisitor = entityManager.find(Role.class, 3);
		User user = new User("thrivileo@gmail.com", "thriviSBV");
		user.addRole(roleEditor);
		user.addRole(roleVisitor);
		
		repo.save(user);
	}
	
	@Test
	public void assignRoleToExistingUser() {
		User user = repo.findById(1).get();
		Role roleEditor = entityManager.find(Role.class, 2);
		user.addRole(roleEditor);
	}
	
	@Test
	public void removeRoleFromExistingUser() {
		User user = repo.findById(1).get();
		Role role = new Role(2);
		user.removeRole(role);
	}
	
	@Test
	public void createNewUserWithNewRole() {
		Role roleStudent = new Role("Student");
		User user = new User("cst18095@std.uwu.ac.lk", "Draco1");
		user.addRole(roleStudent);
		
		repo.save(user);
	}
	
	@Test
	public void testGetUser() {
		User user = repo.findById(1).get();
		System.out.println(user.getEmail());
		System.out.println(user.getRoles());
	}
	
	@Test
	public void testRemoveUser() {
		repo.deleteById(4);
	}
	
}
