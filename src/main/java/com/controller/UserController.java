package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.UserDao;

@RestController
public class UserController {
	@Autowired
	UserDao userDao;

	@PostMapping("/users")
	public ResponseBean<UserBean> saveUser(UserBean user) {

		userDao.insertUser(user);
		ResponseBean<UserBean> resp = new ResponseBean<>();
		resp.setStatus(200);
		resp.setMessage("user added");
		resp.setData(user);

		return resp;

	}

	@GetMapping("/users")
	public ResponseBean<List<UserBean>> getAllUsers() {

		ResponseBean<List<UserBean>> resp = new ResponseBean<>();
		resp.setData(userDao.getAllUsers());
		resp.setMessage("users ret...");
		resp.setStatus(200);
		return resp;
	}

	@GetMapping("/users/{userId}")
	public ResponseBean<UserBean> getUserById(@PathVariable("userId") int userId) {
		UserBean user = userDao.getUserById(userId);

		ResponseBean<UserBean> resp = new ResponseBean<>();

		if (user == null) {
			resp.setStatus(-1);
			resp.setMessage("Invalid userId");
		} else {
			resp.setStatus(200);
			resp.setMessage("user ret...");
			resp.setData(user);

		}

		return resp;
	}

	@DeleteMapping("/users/{userId}")
	public List<UserBean> deleteUser(@PathVariable("userId") int userId) {
		userDao.deleteUser(userId);

		return userDao.getAllUsers();

	}

	@GetMapping("/getuserbyemail/{email}")
	public ResponseEntity<UserBean> getUserByEmail(@PathVariable("email") String email) {
		UserBean user = userDao.getUserByEmail(email);
		ResponseEntity<UserBean> resp = new ResponseEntity<UserBean>(user, HttpStatus.ACCEPTED);
		return resp;
	}

	@PutMapping("/users")
	public ResponseBean<UserBean> updateUser(UserBean user) {

		//userid 
		//update - data
		userDao.updateUser(user);
		ResponseBean<UserBean> res = new ResponseBean<>();
		res.setStatus(200);
		res.setData(user);
		res.setMessage("user updated");
		return res;

	}

}
