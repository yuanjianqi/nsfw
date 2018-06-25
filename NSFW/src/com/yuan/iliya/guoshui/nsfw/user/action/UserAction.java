package com.yuan.iliya.guoshui.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.yuan.iliya.guoshui.nsfw.user.entity.User;
import com.yuan.iliya.guoshui.nsfw.user.service.UserService;

public class UserAction extends ActionSupport{
	@Autowired
	private UserService userService;
	
	private User user;
	private List<User> users;
	private String[] selected;
	
	//头像模块
	private File headImg;
	private String headImgContentType;
	private String headImgFileName;
	
	//导入用户
	private File userExecl;
	private String userExeclContentType;
	private String userExeclFileName;
	
	
	
	public String listUI(){
		users = userService.getAllUsers();
		return "listUI";
	}
	public String addUI(){
		return "addUI";
	}
	public String add(){
		if (user != null) {
			if (headImg != null) {
				String realPath = ServletActionContext.getServletContext().getRealPath("upload/user");
				String newName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
				System.out.println(newName);
				try {
					FileUtils.copyFile(headImg, new File(newName));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				user.setHeadImage("user/" + newName);
			}
			userService.saveUser(user);
			
		}
		return "list";
	}
	public String editUI(){
		if (user != null) {
			user = userService.getUserById(user.getId());
		}
		return "editUI";
	}
	public String edit(){
		if (user != null) {
			if (headImg != null) {
				String realPath = ServletActionContext.getServletContext().getRealPath("upload/user");
				System.out.println(realPath);
				String newName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
				System.out.println(newName);
				try {
					FileUtils.copyFile(headImg, new File(realPath, newName));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				user.setHeadImage("user/" + newName);
			}
			userService.updateUser(user);
		}
		return "list";
	}
	public String delete(){
		if (user != null) {
			userService.deleteUserById(user.getId());
			
		}
		return "list";
	}
	public String deleteSelected(){
		int id;
		
		if (selected != null && selected.length > 0) {
			for (String string : selected) {
				string = string.trim();
				try {
					id = Integer.valueOf(string);
					userService.deleteUserById(id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		}
		return "list";
	}
	
	public String exportExcel(){
		users = userService.getAllUsers();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/x-execl");
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户列表.xls".getBytes(),"ISO-8859-1"));
			OutputStream outputStream = response.getOutputStream();
			userService.exportExcel(users,outputStream);
			if (outputStream != null) {
				outputStream.close();
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "list";
	}
	
	
	public String importExcel(){
		
		if (userExecl != null) {
			
			userService.importExcel(userExecl,userExeclFileName);
		}
		
		
		return "list";
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgContentType() {
		return headImgContentType;
	}
	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	public File getUserExecl() {
		return userExecl;
	}
	public void setUserExecl(File userExecl) {
		this.userExecl = userExecl;
	}
	public String getUserExeclContentType() {
		return userExeclContentType;
	}
	public void setUserExeclContentType(String userExeclContentType) {
		this.userExeclContentType = userExeclContentType;
	}
	public String getUserExeclFileName() {
		return userExeclFileName;
	}
	public void setUserExeclFileName(String userExeclFileName) {
		this.userExeclFileName = userExeclFileName;
	}
	public String[] getSelected() {
		return selected;
	}
	public void setSelected(String[] selected) {
		this.selected = selected;
	}
	
	
	
	

}
