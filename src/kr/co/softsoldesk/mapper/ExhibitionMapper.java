package kr.co.softsoldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import kr.co.softsoldesk.Beans.ExhibitionBean;
import kr.co.softsoldesk.Beans.ExhibitionDetailBean;
import kr.co.softsoldesk.Beans.ReviewBean;

public interface ExhibitionMapper {
	
	// 전시회 인기 페이지 조회	(상태값 1:현재 공개여부 공개된값)
	@Select("SELECT e.exhibition_id, e.title, e.regdate, e.author, e.price, e.exhibition_start, e.exhibition_end, e.open, e.holiday, e.ticket_cnt, e.address, e.place, e.site, e.views, e.state, f1.path as main_poster_path, f1.name as main_poster_name, f2.path as detail_poster_path, f2.name as detail_poster_name FROM exhibition e JOIN file_table f1 ON e.main_poster_file_id = f1.file_id JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id AND SYSDATE BETWEEN e.exhibition_start AND e.exhibition_end AND e.state = 1 AND e.price != 0 ORDER BY e.ticket_cnt desc")
	List<ExhibitionBean> getPopularExhibitionInfo(RowBounds rowBounds);
	
	// 전시회 인기 페이지 페이징처리를 위한 개수 반환 메소드
	@Select("SELECT count(e.exhibition_id) FROM exhibition e JOIN file_table f1 ON e.main_poster_file_id = f1.file_id JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id AND SYSDATE BETWEEN e.exhibition_start AND e.exhibition_end AND e.state = 1 AND e.price != 0 ORDER BY e.ticket_cnt desc")
	int getPopularExhibitionCnt();
	
	// 전시회 상세페이지 조회
	@Select("SELECT e.exhibition_id, e.title, e.regdate, e.author, e.price, e.exhibition_start, e.exhibition_end, e.open, e.holiday, e.ticket_cnt, e.address, e.place, e.site, e.views, e.state, f1.path as main_poster_path, f1.name as main_poster_name, f2.path as detail_poster_path, f2.name as detail_poster_name FROM exhibition e JOIN file_table f1 ON e.main_poster_file_id = f1.file_id JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id AND exhibition_id = #{exhibition_id}")
	ExhibitionBean getExhibitionDetailInfo(int exhibition_id);
	
	// 전시회 클릭시 조회수 증가
	@Update("UPDATE exhibition SET views = views + 1 WHERE exhibition_id = #{exhibition_id}")
	void increaseViewsExhibition(int exhibition_id);
	 
	// 전시회 최신 페이지 리스트				페이징 처리 해야지이이이~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Select("SELECT \r\n"
			+ "    e.exhibition_id, \r\n"
			+ "    e.title, \r\n"
			+ "    e.regdate, \r\n"
			+ "    e.author, \r\n"
			+ "    e.price, \r\n"
			+ "    e.exhibition_start, \r\n"
			+ "    e.exhibition_end, \r\n"
			+ "    e.open, \r\n"
			+ "    e.holiday, \r\n"
			+ "    e.ticket_cnt, \r\n"
			+ "    e.address, \r\n"
			+ "    e.place, \r\n"
			+ "    e.site, \r\n"
			+ "    e.views, \r\n"
			+ "    e.state, \r\n"
			+ "    f1.path as main_poster_path, \r\n"
			+ "    f1.name as main_poster_name, \r\n"
			+ "    f2.path as detail_poster_path, \r\n"
			+ "    f2.name as detail_poster_name \r\n"
			+ "FROM \r\n"
			+ "    exhibition e \r\n"
			+ "    JOIN file_table f1 ON e.main_poster_file_id = f1.file_id \r\n"
			+ "    JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id \r\n"
			+ "WHERE \r\n"
			+ "    e.exhibition_start BETWEEN SYSDATE - INTERVAL '30' DAY AND SYSDATE AND e.state = 1 "
			+ "ORDER BY e.exhibition_start desc")
	List<ExhibitionBean> getRecentExhibitionInfo(RowBounds rowBounds);
	
	// 전시회 페이지 최근 페이징 처리를 위한 개수 반환 메소드
	@Select("SELECT count(exhibition_id) from exhibition WHERE exhibition_start BETWEEN SYSDATE - INTERVAL '30' DAY AND SYSDATE AND state = 1")
	int getRecentExhibitionCnt();
	
	// 전시회 페이지 곧종료 리스트				페이징 처리 해야지이이이~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Select("SELECT \r\n"
			+ "    e.exhibition_id, \r\n"
			+ "    e.title, \r\n"
			+ "    e.regdate, \r\n"
			+ "    e.author, \r\n"
			+ "    e.price, \r\n"
			+ "    e.exhibition_start, \r\n"
			+ "    e.exhibition_end, \r\n"
			+ "    e.open, \r\n"
			+ "    e.holiday, \r\n"
			+ "    e.ticket_cnt, \r\n"
			+ "    e.address, \r\n"
			+ "    e.place, \r\n"
			+ "    e.site, \r\n"
			+ "    e.views, \r\n"
			+ "    e.state, \r\n"
			+ "    f1.path as main_poster_path, \r\n"
			+ "    f1.name as main_poster_name, \r\n"
			+ "    f2.path as detail_poster_path, \r\n"
			+ "    f2.name as detail_poster_name \r\n"
			+ "FROM \r\n"
			+ "    exhibition e \r\n"
			+ "    JOIN file_table f1 ON e.main_poster_file_id = f1.file_id \r\n"
			+ "    JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id \r\n"
			+ "WHERE \r\n"
			+ "    e.exhibition_end BETWEEN SYSDATE AND SYSDATE + INTERVAL '30' DAY AND e.state = 1"
			+ "ORDER BY e.exhibition_end asc")
	List<ExhibitionBean> getSoonEndExhibitionInfo(RowBounds rowBounds);
	
	// 전시회 곧종료 페이징 처리
	@Select("SELECT count(exhibition_id) FROM exhibition WHERE exhibition_end BETWEEN SYSDATE AND SYSDATE + INTERVAL '30' DAY AND state = 1")
	int getsoonEndExhibitionCnt();
	
	// 전시회 페이지 무료				페이징 처리 해야지이이이~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Select("SELECT \r\n"
			+ "    e.exhibition_id, \r\n"
			+ "    e.title, \r\n"
			+ "    e.regdate, \r\n"
			+ "    e.author, \r\n"
			+ "    e.price, \r\n"
			+ "    e.exhibition_start, \r\n"
			+ "    e.exhibition_end, \r\n"
			+ "    e.open, \r\n"
			+ "    e.holiday, \r\n"
			+ "    e.ticket_cnt, \r\n"
			+ "    e.address, \r\n"
			+ "    e.place, \r\n"
			+ "    e.site, \r\n"
			+ "    e.views, \r\n"
			+ "    e.state, \r\n"
			+ "    f1.path as main_poster_path, \r\n"
			+ "    f1.name as main_poster_name, \r\n"
			+ "    f2.path as detail_poster_path, \r\n"
			+ "    f2.name as detail_poster_name \r\n"
			+ "FROM \r\n"
			+ "    exhibition e \r\n"
			+ "    JOIN file_table f1 ON e.main_poster_file_id = f1.file_id \r\n"
			+ "    JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id \r\n"
			+ "WHERE \r\n"
			+ "    e.price = 0 AND e.state = 1")
	List<ExhibitionBean> getFreeExhibitionInfo(RowBounds rowBounds);
	
	// 전시회 페이지 무료 페이징 처리 위한 개수 반환 메소드
	@Select("SELECT count(exhibition_id) FROM exhibition WHERE price = 0 AND state = 1")
	int getFreeExhibitionCnt();
	
	// 메인 페이지 인덱스 인기 캐러셀
	@Select("SELECT e.exhibition_id, e.title, e.ticket_cnt, f.path AS main_poster_path, f.name AS main_poster_name\r\n"
			+ "FROM exhibition e\r\n"
			+ "JOIN file_table f ON e.main_poster_file_id = f.file_id\r\n"
			+ "ORDER BY e.ticket_cnt DESC\r\n"
			+ "FETCH FIRST 8 ROWS ONLY")
	List<ExhibitionBean> getIndexPagePopularExhibitionInfo();
	
	// 메인 페이지 인덱스 전시 예정 캐러셀
	@Select("SELECT e.exhibition_id, e.title, e.ticket_cnt, f.path AS main_poster_path, f.name AS main_poster_name\r\n"
			+ "FROM exhibition e\r\n"
			+ "JOIN file_table f ON e.main_poster_file_id = f.file_id\r\n"
			+ "WHERE e.exhibition_start >= CURRENT_DATE\r\n"
			+ "ORDER BY e.exhibition_start ASC\r\n"
			+ "FETCH FIRST 8 ROWS ONLY")
	List<ExhibitionBean> getIndexPageSoonExhibitionInfo();
	
	// 메인 페이지 인덱스 현재 전시 캐러셀
	@Select("SELECT e.exhibition_id, e.title, e.ticket_cnt, f.path AS main_poster_path, f.name AS main_poster_name\r\n"
			+ "FROM exhibition e\r\n"
			+ "JOIN file_table f ON e.main_poster_file_id = f.file_id\r\n"
			+ "WHERE e.exhibition_start <= CURRENT_DATE AND e.exhibition_end >= CURRENT_DATE\r\n"
			+ "ORDER BY DBMS_RANDOM.VALUE\r\n"
			+ "FETCH FIRST 8 ROWS ONLY")
	List<ExhibitionBean> getIndexPagecurrentExhibitionInfo();
	
	// 리뷰 가져오기
	@Select("SELECT\r\n"
			+ "    r.contents,\r\n"
			+ "    CASE \r\n"
			+ "        WHEN LENGTH(r.contents) > 47 THEN SUBSTR(r.contents, 1, 47) || '...'\r\n"
			+ "        ELSE r.contents \r\n"
			+ "    END AS short_contents,\r\n"
			+ "    u.nickname,\r\n"
			+ "    r.expose,\r\n"
			+ "    r.rating,\r\n"
			+ "    (SELECT g.grade FROM grade g WHERE u.exp >= g.start_exp AND (u.exp <= g.end_exp OR g.end_exp IS NULL)) AS grade\r\n"
			+ "FROM\r\n"
			+ "    review r\r\n"
			+ "    JOIN reserve rv ON r.reserve_id = rv.reserve_id\r\n"
			+ "    JOIN user_table u ON rv.user_id = u.user_id\r\n"
			+ "WHERE\r\n"
			+ "    r.contents IS NOT NULL\r\n"
			+ "    AND r.expose = 1\r\n"
			+ "    AND rv.exhibition_id = #{exhibition_id}\r\n"
			+ "ORDER BY\r\n"
			+ "    r.review_id DESC")
	List<ReviewBean> getExhibition_clickReviewAllInfo(int exhibition_id, RowBounds rowBounds);
	
	// 해당전시 리뷰 평균 점수 반환
	@Select("SELECT COALESCE(AVG(r.rating), 0.0) AS average_rating\r\n"
			+ "FROM review r\r\n"
			+ "JOIN reserve rv ON r.reserve_id = rv.reserve_id\r\n"
			+ "WHERE rv.exhibition_id = #{exhibition_id}\r\n")
	double getExhibitionReviewAVG(@Param("exhibition_id") int exhibition_id);

	
	// 페이징 처리에서 해당 전시 리뷰 총 개수 리턴받기
	@Select("SELECT\r\n"
			+ "    COUNT(*)\r\n"
			+ "FROM\r\n"
			+ "    review r\r\n"
			+ "    JOIN reserve rv ON r.reserve_id = rv.reserve_id\r\n"
			+ "    JOIN user_table u ON rv.user_id = u.user_id\r\n"
			+ "WHERE\r\n"
			+ "    r.expose = 1\r\n"
			+ "    AND rv.exhibition_id = #{exhibition_id}")
	int getExhibitionReviewCnt(int exhibition_id);
	//전시회 exhibition_id 로 제목 조회 
	@Select("select title from exhibition where exhibition_id=#{exhibition_id}")
	String getExhibitionTitle(int exhibition_id);
	
	@Update("update exhibition set ticket_cnt= ticket_cnt + #{ticket_cnt}  where exhibition_id=#{exhibition_id}")
	void increase_exhibitionTotalTicket(@Param("exhibition_id")int exhibition_id,@Param("ticket_cnt")int ticket_cnt);
	
	@Select("SELECT \r\n"
			+ "    e.exhibition_id, \r\n"
			+ "    e.title, \r\n"
			+ "    e.regdate, \r\n"
			+ "    e.author, \r\n"
			+ "    e.price, \r\n"
			+ "    e.exhibition_start, \r\n"
			+ "    e.exhibition_end, \r\n"
			+ "    e.open, \r\n"
			+ "    e.holiday, \r\n"
			+ "    e.ticket_cnt, \r\n"
			+ "    e.address, \r\n"
			+ "    e.place, \r\n"
			+ "    e.site, \r\n"
			+ "    e.views, \r\n"
			+ "    e.state, \r\n"
			+ "    f1.path as main_poster_path, \r\n"
			+ "    f1.name as main_poster_name, \r\n"
			+ "    f2.path as detail_poster_path, \r\n"
			+ "    f2.name as detail_poster_name \r\n"
			+ "FROM \r\n"
			+ "    exhibition e \r\n"
			+ "    JOIN file_table f1 ON e.main_poster_file_id = f1.file_id \r\n"
			+ "    JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id \r\n"
			+ "WHERE \r\n"
			+ "    UPPER(e.title) LIKE '%' || upper(#{title}) || '%' and state = 1"
			+ "order by exhibition_id desc")
	List<ExhibitionBean>SearchExhibition(@Param("title")String keyword, RowBounds rowBounds);
	
	@Select("SELECT \r\n"
			+ "    count(*)\r\n"
			+ "FROM \r\n"
			+ "    exhibition e \r\n"
			+ "    JOIN file_table f1 ON e.main_poster_file_id = f1.file_id \r\n"
			+ "    JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id \r\n"
			+ "WHERE \r\n"
			+ "    UPPER(e.title) LIKE '%' || upper(#{title}) || '%' and state = 1")
	int SearchExhibitionCnt(@Param("title")String title);
	
	
	@Select("SELECT \r\n"
			+ "    e.exhibition_id, \r\n"
			+ "    e.title, \r\n"
			+ "    e.regdate, \r\n"
			+ "    e.author, \r\n"
			+ "    e.price, \r\n"
			+ "    e.exhibition_start, \r\n"
			+ "    e.exhibition_end, \r\n"
			+ "    e.open, \r\n"
			+ "    e.holiday, \r\n"
			+ "    e.ticket_cnt, \r\n"
			+ "    e.address, \r\n"
			+ "    e.place, \r\n"
			+ "    e.site, \r\n"
			+ "    e.views, \r\n"
			+ "    e.state, \r\n"
			+ "    f1.path as main_poster_path, \r\n"
			+ "    f1.name as main_poster_name, \r\n"
			+ "    f2.path as detail_poster_path, \r\n"
			+ "    f2.name as detail_poster_name \r\n"
			+ "FROM \r\n"
			+ "    exhibition e \r\n"
			+ "    JOIN file_table f1 ON e.main_poster_file_id = f1.file_id \r\n"
			+ "    JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id\r\n"
			+ "where state = 1 order by exhibition_id desc")
	List<ExhibitionBean>AllExhibition(RowBounds rowBounds);
	
	
	@Select("SELECT \r\n"
			+ "    count(*)\r\n"
			+ "FROM \r\n"
			+ "    exhibition e \r\n"
			+ "    JOIN file_table f1 ON e.main_poster_file_id = f1.file_id \r\n"
			+ "    JOIN file_table f2 ON e.detail_poster_file_id = f2.file_id where state = 1")
	int AllExhibitionCnt();
	
	
	// =================================== 전시회 등록 신청 ==================================
	
	// 전시회 등록 신청 폼 작성
	@Insert("INSERT INTO exhibition_enroll (title, place, regdate, author, address, price, enroll_state, exhibition_start, exhibition_end, open, holiday, site, apply_person, main_poster_file_id, detail_poster_file_id) VALUES (#{title}, #{place}, sysdate, #{author}, #{address, jdbcType=VARCHAR}, #{price}, #{enroll_state} ,#{exhibition_start}, #{exhibition_end}, #{open}, #{holiday}, #{site}, #{apply_person}, #{main_poster_file_id}, #{detail_poster_file_id})")
	void AddExhibition_Enroll(ExhibitionDetailBean exhibitionDetailBean);
	
	// ================================== 관리자 페이지 메인 배너 추가==========================
	
	// 메인 베너 추가시 모든 전시회 이름, id 반환
	@Select("Select exhibition_id, title from exhibition")
	List<ExhibitionBean> getexhibitionallTitle();
	
	// 전시회 예매 취소 .. 티켓 수 회수 
	@Update("update exhibition set ticket_cnt  = ticket_cnt - #{ticket_count} where exhibition_id=#{exhibition_id}")
	public void setExhibitionTicketCntMinus(@Param("ticket_count")int ticket_count,@Param("exhibition_id")int exhibition_id );
}	
