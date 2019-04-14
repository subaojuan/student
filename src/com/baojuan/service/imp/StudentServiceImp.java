package com.baojuan.service.imp;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.baojuan.Util.MybatisUitl;
import com.baojuan.mapper.StudentMapper;
import com.baojuan.mapper.TeacherMapper;
import com.baojuan.pojo.PageInfo;
import com.baojuan.pojo.Student;
import com.baojuan.pojo.Teacher;
import com.baojuan.service.StudentService;

public class StudentServiceImp implements StudentService{

	@Override
	public  PageInfo showPage(String pageSizeStr, String pageNumberStr, String sname, String tname) {
		int pageSize=2;
		if(pageSizeStr!=null && !pageSizeStr.equals("")) {
			pageSize=Integer.parseInt(pageSizeStr);
		}
		int pageNumber=1;
		if(pageNumberStr!=null && !pageNumberStr.equals("")) {
			pageNumber=Integer.parseInt(pageNumberStr);
		}
		SqlSession session=MybatisUitl.getSession();
		StudentMapper studentMapper=session.getMapper(StudentMapper.class);
		PageInfo pi=new PageInfo();
		pi.setPageSize(pageSize);
		pi.setPageNumber(pageNumber);
		pi.setPageStart((pageNumber-1)*pageSize);
		pi.setSname(sname);
		pi.setTname(tname);
		List<Student> list=studentMapper.selectByPage(pi);
		TeacherMapper teacherMapper=session.getMapper(TeacherMapper.class);
		//查询出每个学生对应的老师信息
		for (Student student : list) {
			student.setTeacher(teacherMapper.selectById(student.getTid()));
		}
		pi.setList(list);
		Long count=studentMapper.selsctCountByPageInfo(pi);
		pi.setTotal(count%pageSize==0?count/pageSize:count/pageSize+1);
		return pi;
	}

}
