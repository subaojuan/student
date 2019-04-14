package com.baojuan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baojuan.pojo.PageInfo;
import com.baojuan.service.StudentService;
import com.baojuan.service.imp.StudentServiceImp;

@SuppressWarnings("serial")
@WebServlet("/show")
public class showServlet extends HttpServlet {
	private StudentService stuservice = new StudentServiceImp();

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String sname = req.getParameter("sname");
		if (sname != null && !sname.equals(""))
			sname = new String(sname.getBytes("iso-8859-1"), "utf-8");
		String tname = req.getParameter("tname");
		if (tname != null && !tname.equals(""))
			tname = new String(tname.getBytes("iso-8859-1"), "utf-8");
		String pageSize = req.getParameter("pageSize");
		String pageNumber = req.getParameter("pageNumber");
		PageInfo pi = stuservice.showPage(pageSize, pageNumber, sname, tname);
		req.setAttribute("pageInfo", pi);
		req.getRequestDispatcher("index.jsp").forward(req, res);
	}

}
