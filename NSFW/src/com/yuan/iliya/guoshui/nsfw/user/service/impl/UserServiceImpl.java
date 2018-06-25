package com.yuan.iliya.guoshui.nsfw.user.service.impl;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuan.iliya.guoshui.core.utils.ExecelUtils;
import com.yuan.iliya.guoshui.nsfw.user.dao.UserDao;
import com.yuan.iliya.guoshui.nsfw.user.entity.User;
import com.yuan.iliya.guoshui.nsfw.user.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userdao;
	@Override
	public void saveUser(User user) {
		userdao.save(user);

	}

	@Override
	public void updateUser(User user) {
		userdao.update(user);
	}

	@Override
	public void deleteUserById(Serializable id) {
		userdao.deleteById(id);
	}

	@Override
	public User getUserById(Serializable id) {
		// TODO Auto-generated method stub
		return userdao.getEntityById(id);
	}

	@Override
	public List<User> getAllUsers() {
		
		return userdao.getAllEntities();
	}
	
	public void exportExcel(List<User> users, OutputStream outputStream){
		ExecelUtils.exportToXls(users, outputStream);
	}
	
	public void importExcel(File file, String fileName){
		List<User> users = ExecelUtils.importExeclToUsers(file,fileName);
		for (User user : users) {
			saveUser(user);
		}
	}

}
