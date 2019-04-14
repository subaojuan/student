package com.baojuan.Util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUitl {
	// Factory实例化是一个比较耗费性能的过程
	// 保证有且仅有一个Factory
	private static SqlSessionFactory Factory;
	private static ThreadLocal<SqlSession> tl = new ThreadLocal<>();
	static {
		try {
			InputStream is = Resources.getResourceAsStream("mybatis.xml");
			Factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获取session的方法
	public static SqlSession getSession() {
		SqlSession session = tl.get();
		if (session == null) {
			tl.set(Factory.openSession());
		}
		return tl.get();
	}
	
	public static void closeSession() {
		SqlSession session = tl.get();
		if (session != null) {
			session.close();
		}
		tl.set(null);
	}
}
