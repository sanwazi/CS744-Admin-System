package com.example.EMR_Admin.department.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.department.data.Department;
import com.example.EMR_Admin.department.dao.DepartmentDao;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentDao DDao;
	
	public List<Department> searchWithInput(String input) {
		// TODO Auto-generated method stub
		List<Department> list = DDao.serachWithInput(input);
		return list;
	}
	
	public List<Department> getAll(){
		List<Department> list = DDao.displayAll();
		return list;
	}
}
