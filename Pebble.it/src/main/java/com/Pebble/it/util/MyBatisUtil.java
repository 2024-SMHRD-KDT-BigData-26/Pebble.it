package com.Pebble.it.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // MyBatis 설정 파일 로드
            System.out.println("[DEBUG] MyBatis 설정 파일 로드 시작...");
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            System.out.println("[DEBUG] MyBatis 설정 파일 로드 성공!");
        } catch (IOException e) {
            System.err.println("[ERROR] MyBatis 설정 파일 로드 실패: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("MyBatis 설정 파일 로드 실패", e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
