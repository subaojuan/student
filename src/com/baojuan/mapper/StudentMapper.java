package com.baojuan.mapper;

import java.util.List;

import com.baojuan.pojo.PageInfo;
import com.baojuan.pojo.Student;

public interface StudentMapper {
	List<Student> selectByPage(PageInfo pi);

	Long selsctCountByPageInfo(PageInfo pi);
}
