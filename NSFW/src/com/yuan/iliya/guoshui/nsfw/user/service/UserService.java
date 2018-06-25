package com.yuan.iliya.guoshui.nsfw.user.service;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import com.yuan.iliya.guoshui.nsfw.user.entity.User;

public interface UserService {
	
	public void saveUser(User user);
	public void updateUser(User user);
	public void deleteUserById(Serializable id);
	public User getUserById(Serializable id);
	public List<User> getAllUsers();
	public void exportExcel(List<User> users, OutputStream outputStream);
	public void importExcel(File file, String fileName);
}
