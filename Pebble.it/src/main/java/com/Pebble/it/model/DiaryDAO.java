package com.Pebble.it.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.Pebble.it.db.SqlSessionManager;


public class DiaryDAO {

    // DB 연결
	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	private SqlSession sqlSession;
    // 일기 작성 메서드
    public int insertDiary(DiaryDTO dto) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) { // 자동 커밋
            // DiaryMapper의 insertDiary 쿼리 실행
            int cnt = sqlSession.insert("com.Pebble.it.mapper.DiaryMapper.insertDiary", dto);

            // 결과 출력
            if (cnt > 0) {
                System.out.println("일기 작성 성공: " + dto.getDiaryTitle());
            } else {
                System.out.println("일기 작성 실패");
            }
            return cnt;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("일기 작성 중 오류 발생: " + e.getMessage());
            return 0;
        }
    }

    // 추가 메서드: 일기 삭제
    public int deleteDiary(int diaryIdx) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) { // 자동 커밋
            // DiaryMapper의 deleteDiary 쿼리 실행
            int cnt = sqlSession.delete("com.Pebble.it.mapper.DiaryMapper.deleteDiary", diaryIdx);

            // 결과 출력
            if (cnt > 0) {
                System.out.println("일기 삭제 성공: " + diaryIdx);
            } else {
                System.out.println("일기 삭제 실패");
            }
            return cnt;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("일기 삭제 중 오류 발생: " + e.getMessage());
            return 0;
        }
    }

    // 추가 메서드: 일기 조회
    public DiaryDTO selectDiary(int diaryIdx) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) { // 읽기 전용
            // DiaryMapper의 selectDiary 쿼리 실행
            DiaryDTO diary = sqlSession.selectOne("com.Pebble.it.mapper.DiaryMapper.selectDiary", diaryIdx);

            if (diary != null) {
                System.out.println("일기 조회 성공: " + diary.getDiaryTitle());
            } else {
                System.out.println("조회된 일기가 없습니다: " + diaryIdx);
            }
            return diary;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("일기 조회 중 오류 발생: " + e.getMessage());
            return null;
        }
    } 
 // 모든 일기 조회 메서드
    public List<DiaryDTO> selectAllDiary() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("com.Pebble.it.mapper.DiaryMapper.selectAllDiary");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("일기 조회 중 오류 발생: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}