package com.baojuan.service;

import com.baojuan.pojo.PageInfo;

public interface StudentService{
	PageInfo showPage(String pageSize,String pageNumber,String sname,String tname);
}
