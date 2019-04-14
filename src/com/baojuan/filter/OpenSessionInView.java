package com.baojuan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


import org.apache.ibatis.session.SqlSession;

import com.baojuan.Util.MybatisUitl;
@WebFilter("/*")
public class OpenSessionInView implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest ServletRequest, ServletResponse ServletResponse, FilterChain filterchain)
			throws IOException, ServletException {
		SqlSession session=MybatisUitl.getSession();
		try {
		filterchain.doFilter(ServletRequest, ServletResponse);
		session.commit();
		}catch(Exception e) {
			session.rollback();
			// e.printStackTrace();
		}finally {
			MybatisUitl.closeSession();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
