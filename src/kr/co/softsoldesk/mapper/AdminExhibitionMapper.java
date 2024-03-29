package kr.co.softsoldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import kr.co.softsoldesk.Beans.ExhibitionBean;
import kr.co.softsoldesk.Beans.ExhibitionDetailBean;

public interface AdminExhibitionMapper {
	
	// ===============================================전시회 관리========================================
	
		// 전시회 관리 첫 페이지 찍혀오는 리스트 
		@Select("SELECT \r\n"
				+ "    exhibition_id, \r\n"
				+ "    title, \r\n"
				+ "    author, \r\n"
				+ "    To_char(exhibition_start, 'yyyy-mm-dd') as exhibition_start, \r\n"
				+ "    To_char(exhibition_end, 'yyyy-mm-dd') as exhibition_end, \r\n"
				+ "    state, \r\n"
				+ "    price, \r\n"
				+ "    CASE \r\n"
				+ "        WHEN SYSDATE < exhibition_start THEN '전시예정'\r\n"
				+ "        WHEN SYSDATE > exhibition_end THEN '종료됨'\r\n"
				+ "        ELSE '전시중' \r\n"
				+ "    END AS open_state\r\n"
				+ "FROM \r\n"
				+ "    exhibition\r\n"
				+ "ORDER BY exhibition_id desc")
		List<ExhibitionBean> getAdminexhibitionmange(RowBounds rowBounds);
		
		// 전시회 총개수 전시중, 전시예정, 종료전시 개수 반환
		@Select("SELECT \r\n"
				+ "    COUNT(*) AS total_exhibitions_count,\r\n"
				+ "    COUNT(CASE WHEN SYSDATE < exhibition_start THEN 1 END) AS upcoming_exhibitions_count,\r\n"
				+ "    COUNT(CASE WHEN SYSDATE > exhibition_end THEN 1 END) AS past_exhibitions_count,\r\n"
				+ "    COUNT(CASE WHEN SYSDATE BETWEEN exhibition_start AND exhibition_end THEN 1 END) AS current_exhibitions_count\r\n"
				+ "FROM \r\n"
				+ "    exhibition")
		ExhibitionBean getExhibitionCount();
		
		
		// 전시회 관리 페이징 처리
		@Select("select count(*) from exhibition")
		int getExhibitionCnt();
		
		// 전시회 관리 작가 검색
		@Select("SELECT \r\n"
				+ "    exhibition_id, \r\n"
				+ "    title, \r\n"
				+ "    author, \r\n"
				+ "    TO_CHAR(exhibition_start, 'YYYY-MM-DD') AS exhibition_start, \r\n"
				+ "    TO_CHAR(exhibition_end, 'YYYY-MM-DD') AS exhibition_end, \r\n"
				+ "    price, \r\n"
				+ "    CASE \r\n"
				+ "        WHEN SYSDATE < exhibition_start THEN '전시예정'\r\n"
				+ "        WHEN SYSDATE > exhibition_end THEN '종료됨'\r\n"
				+ "        ELSE '전시중' \r\n"
				+ "    END AS open_state\r\n"
				+ "FROM \r\n"
				+ "    exhibition\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(author) LIKE '%' || UPPER(#{search}) || '%'\r\n"
				+ "ORDER BY \r\n"
				+ "    exhibition_id ASC")
		List<ExhibitionBean> getauthorSearchExhibitionInfo(@Param("search") String search);
		
		// 전시회 관리 작가 검색 전시회 총개수 전시중, 전시예정, 종료전시 개수 반환
		@Select("SELECT \r\n"
				+ "    COUNT(*) AS total_exhibitions_count,\r\n"
				+ "    COUNT(CASE WHEN SYSDATE < exhibition_start THEN 1 END) AS upcoming_exhibitions_count,\r\n"
				+ "    COUNT(CASE WHEN SYSDATE > exhibition_end THEN 1 END) AS past_exhibitions_count,\r\n"
				+ "    COUNT(CASE WHEN SYSDATE BETWEEN exhibition_start AND exhibition_end THEN 1 END) AS current_exhibitions_count\r\n"
				+ "FROM \r\n"
				+ "    exhibition\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(author) LIKE '%' || UPPER(#{search}) || '%'")
		ExhibitionBean getauthorSearchExhibitionCount(@Param("search") String search);
		
		// 전시회 관리 작가 검색 페이징 처리 위한 검색 총 개수 반환
		@Select("SELECT \r\n"
				+ "    count(*)\r\n"
				+ "FROM \r\n"
				+ "    exhibition\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(author) LIKE '%' || UPPER(#{search}) || '%'\r\n"
				+ "ORDER BY \r\n"
				+ "    exhibition_id ASC")
		int getauthorSearchExhibitionCnt(@Param("search") String search);
		
		// 전시회 관리 제목 검색
		@Select("SELECT \r\n"
				+ "    exhibition_id, \r\n"
				+ "    title, \r\n"
				+ "    author, \r\n"
				+ "    TO_CHAR(exhibition_start, 'YYYY-MM-DD') AS exhibition_start, \r\n"
				+ "    TO_CHAR(exhibition_end, 'YYYY-MM-DD') AS exhibition_end, \r\n"
				+ "    price, \r\n"
				+ "    CASE \r\n"
				+ "        WHEN SYSDATE < exhibition_start THEN '전시예정'\r\n"
				+ "        WHEN SYSDATE > exhibition_end THEN '종료됨'\r\n"
				+ "        ELSE '전시중' \r\n"
				+ "    END AS open_state\r\n"
				+ "FROM \r\n"
				+ "    exhibition\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(title) LIKE '%' || UPPER(#{search}) || '%'\r\n"
				+ "ORDER BY \r\n"
				+ "    exhibition_id ASC")
		List<ExhibitionBean> gettitleSearchExhibitionInfo(@Param("search") String search, RowBounds rowBounds);
		
		// 전시회 관리 제목 검색 전시회 총개수 전시중, 전시예정, 종료전시 개수 반환
		@Select("SELECT \r\n"
				+ "    COUNT(*) AS total_exhibitions_count,\r\n"
				+ "    COUNT(CASE WHEN SYSDATE < exhibition_start THEN 1 END) AS upcoming_exhibitions_count,\r\n"
				+ "    COUNT(CASE WHEN SYSDATE > exhibition_end THEN 1 END) AS past_exhibitions_count,\r\n"
				+ "    COUNT(CASE WHEN SYSDATE BETWEEN exhibition_start AND exhibition_end THEN 1 END) AS current_exhibitions_count\r\n"
				+ "FROM \r\n"
				+ "    exhibition\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(title) LIKE '%' || UPPER(#{search}) || '%'")
		ExhibitionBean gettitleSearchExhibitionCount(@Param("search") String search);

		// 전시회 관리 작가 검색 페이징 처리 위한 검색 총 개수 반환
		@Select("SELECT \r\n"
				+ "    count(*)\r\n"
				+ "FROM \r\n"
				+ "    exhibition\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(title) LIKE '%' || UPPER(#{search}) || '%'\r\n"
				+ "ORDER BY \r\n"
				+ "    exhibition_id ASC")
		int gettitleSearchExhibitionCnt(@Param("search") String search);	
		
		// 전시회 수정 전시회 모든 정보 신청인까지 다 가져오기
		@Select("SELECT \r\n"
				+ "    e.exhibition_id, \r\n"
				+ "    e.title, \r\n"
				+ "    To_char(e.regdate, 'yyyy-mm-dd') as regdate, \r\n"
				+ "    e.state, \r\n"
				+ "    e.author, \r\n"
				+ "    e.price, \r\n"
				+ "    To_char(e.exhibition_start, 'yyyy-mm-dd') as exhibition_start, \r\n"
				+ "    To_char(e.exhibition_end, 'yyyy-mm-dd') as exhibition_end, \r\n"
				+ "    e.open, \r\n"
				+ "    e.holiday, \r\n"
				+ "    e.address, \r\n"
				+ "    e.place, \r\n"
				+ "    e.site,\r\n"
				+ "    e.main_poster_file_id,\r\n"
				+ "    e.detail_poster_file_id,\r\n"
				+ "    u.name AS apply_name, \r\n"
				+ "    u.email AS apply_email, \r\n"
				+ "    u.telephone AS apply_telephone,\r\n"
				+ "    mf.path AS main_poster_path, \r\n"
				+ "    mf.name AS main_poster_name,\r\n"
				+ "    df.path AS detail_poster_path, \r\n"
				+ "    df.name AS detail_poster_name\r\n"
				+ "FROM \r\n"
				+ "    exhibition e\r\n"
				+ "    JOIN file_table mf ON e.main_poster_file_id = mf.file_id\r\n"
				+ "    JOIN file_table df ON e.detail_poster_file_id = df.file_id\r\n"
				+ "    LEFT JOIN user_table u ON e.apply_person = u.user_id\r\n"
				+ "WHERE \r\n"
				+ "    e.exhibition_id = #{exhibition_id}")
		ExhibitionDetailBean getAllDetailExhibitionBean(int exhibition_id);
		
		// 전시회 수정 업데이트문 1 (file table)
		@Update("update file_table set name = #{name} where file_id=#{file_id}")
		void UpdateExhibitionInfo1(@Param("name") String name,@Param("file_id") int file_id);
		
		// 전시회 수정 업데이트문 2 (exhibition table)
		@Update("UPDATE exhibition \r\n"
				+ "SET \r\n"
				+ "    title = #{title}, \r\n"
				+ "    state = #{state}, \r\n"
				+ "    price = #{price}, \r\n"
				+ "    author = #{author}, \r\n"
				+ "    exhibition_start = #{exhibition_start}, \r\n"
				+ "    exhibition_end = #{exhibition_end}, \r\n"
				+ "    open = #{open}, \r\n"
				+ "    holiday = #{holiday}, \r\n"
				+ "    address = #{address}, \r\n"
				+ "    place = #{place}, \r\n"
				+ "    site = #{site, jdbcType=VARCHAR}\r\n"
				+ "WHERE \r\n"
				+ "    exhibition_id = #{exhibition_id}")
		void UpdateExhibitionInfo2(ExhibitionDetailBean exhibitiondetailBean);
		
		// 전시회 관리자가 직접 추가 1번 file_table 테이블
		@Insert("INSERT INTO file_table (file_id, name, path, file_date) values (file_id_seq.nextval, #{name}, #{path}, sysdate)")
		void addfiletableExhibition(ExhibitionDetailBean exhibitiondetailBean);
		
		// 전시회 관리자가 직접 추가 3번 file_table에 저장한 값들의 file_id 가져오기
		@Select("SELECT file_id from file_table where name=#{name}")
		int getFileId(String name);
		
		// 전시회 관리자가 직접 추가 2번 exhibition 테이블
		@Insert("INSERT INTO exhibition (exhibition_id, title, regdate, author, price, exhibition_start, exhibition_end, open, holiday, address, place, site, state, main_poster_file_id, detail_poster_file_id) VALUES (exhibition_id_seq.NEXTVAL, #{title}, sysdate, #{author}, #{price}, #{exhibition_start}, #{exhibition_end}, #{open}, #{holiday}, #{address}, #{place}, #{site}, #{state},#{main_poster_file_id}, #{detail_poster_file_id})")
		void addexhibitiontableExhibition(ExhibitionDetailBean exhibitiondetailBean);

		
		// ============================ 전시회 등록 신청 ============================
		// 전시회 등록신청 관리자 페이지 리스트
		@Select("SELECT e.exhibition_enroll_id, e.title, e.author, e.price, To_char(e.exhibition_start, 'yyyy-mm-dd') as exhibition_start , To_char(e.exhibition_end, 'yyyy-mm-dd') as exhibition_end, e.enroll_state, u.name as apply_name\r\n"
				+ "FROM exhibition_enroll e\r\n"
				+ "JOIN user_table u ON e.apply_person = u.user_id\r\n"
				+ "ORDER BY e.exhibition_enroll_id DESC")
		List<ExhibitionDetailBean> getAllExhibitionEnroll(RowBounds rowBounds);
		
		// 전시회 등록신청 관리자 페이지 페이징 처리 위한 총개수 반환 메소드
		@Select("select count(*) from exhibition_enroll")
		int getEnrollExhibitionCnt();
		
		// 전시회 1개 상세 정보 가져가기
		@Select("SELECT \r\n"
				+ "    e.exhibition_enroll_id, \r\n"
				+ "    e.title, \r\n"
				+ "    e.author, \r\n"
				+ "    e.price, \r\n"
				+ "    TO_CHAR(e.exhibition_start, 'yyyy-mm-dd') as exhibition_start, \r\n"
				+ "    TO_CHAR(e.exhibition_end, 'yyyy-mm-dd') as exhibition_end, \r\n"
				+ "    e.open, \r\n"
				+ "    e.holiday, \r\n"
				+ "    e.address, \r\n"
				+ "    e.place, \r\n"
				+ "    e.site, \r\n"
				+ "    e.enroll_state, \r\n"
				+ "    e.main_poster_file_id,       \r\n"
				+ "    e.detail_poster_file_id,     \r\n"
				+ "	   e.apply_person,		 \r\n"
				+ "    u.name AS apply_name, \r\n"
				+ "    u.email AS apply_email, \r\n"
				+ "    u.telephone AS apply_telephone,\r\n"
				+ "    f1.name AS main_poster_name,\r\n"
				+ "    f1.path AS main_poster_path,\r\n"
				+ "    f2.name AS detail_poster_name,\r\n"
				+ "    f2.path AS detail_poster_path\r\n"
				+ "FROM \r\n"
				+ "    exhibition_enroll e\r\n"
				+ "JOIN \r\n"
				+ "    user_table u ON e.apply_person = u.user_id\r\n"
				+ "LEFT JOIN \r\n"
				+ "    file_table f1 ON e.main_poster_file_id = f1.file_id\r\n"
				+ "LEFT JOIN \r\n"
				+ "    file_table f2 ON e.detail_poster_file_id = f2.file_id\r\n"
				+ "WHERE \r\n"
				+ "    e.exhibition_enroll_id = #{exhibition_enroll_id}")
		ExhibitionDetailBean getOneEnrollExhitiion(int exhibition_enroll_id);
		
		// 전시회 등록 신청 -> 전시회 등록 하는 메소드
		@Insert("INSERT INTO exhibition (exhibition_id, title, regdate, author, price, exhibition_start, exhibition_end, open, holiday, address, place, site, state, apply_person, main_poster_file_id, detail_poster_file_id) VALUES (exhibition_id_seq.NEXTVAL, #{title}, sysdate, #{author}, #{price}, #{exhibition_start}, #{exhibition_end}, #{open}, #{holiday}, #{address}, #{place}, #{site}, #{state}, #{apply_person},#{main_poster_file_id}, #{detail_poster_file_id})")
		void addEnrollexhibitiontableExhibition(ExhibitionDetailBean exhibitiondetailBean);
		
		//전시회 등록 신청 -> 전시회 등록 완료 하면 exhibition_enroll 테이블 상태값 변경해주기
		@Update("UPDATE exhibition_enroll SET enroll_state = #{enroll_state} where exhibition_enroll_id = #{exhibition_enroll_id}")
		void UpdateExhibitionEnrollState(@Param("enroll_state")int enroll_state,@Param("exhibition_enroll_id") int exhibition_enroll_id);
		
		// 전시회 등록 신청 관리자 페이지 뱃지에 넣을 개수 반환
		@Select("SELECT\r\n"
				+ "    COUNT(*) AS total_exhibition_eroll_count,\r\n"
				+ "    COUNT(CASE WHEN enroll_state = 1 THEN 1 END) AS stay_exhibition_eroll_count,\r\n"
				+ "    COUNT(CASE WHEN enroll_state = 2 THEN 1 END) AS complete_exhibition_eroll_count\r\n"
				+ "FROM\r\n"
				+ "    exhibition_enroll")
		ExhibitionDetailBean getEnrollExhibitionbadgeCnt();
		
		// 전시회 등록 신청 제목 검색
		@Select("SELECT \r\n"
				+ "    e.exhibition_enroll_id, \r\n"
				+ "    e.title, \r\n"
				+ "    e.author, \r\n"
				+ "    e.price, \r\n"
				+ "    TO_CHAR(e.exhibition_start, 'yyyy-mm-dd') AS exhibition_start, \r\n"
				+ "    TO_CHAR(e.exhibition_end, 'yyyy-mm-dd') AS exhibition_end, \r\n"
				+ "    e.enroll_state, \r\n"
				+ "    u.name AS apply_name\r\n"
				+ "FROM \r\n"
				+ "    exhibition_enroll e\r\n"
				+ "JOIN \r\n"
				+ "    user_table u ON e.apply_person = u.user_id\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(e.title) LIKE '%' || #{search} || '%'\r\n"
				+ "ORDER BY \r\n"
				+ "    e.exhibition_enroll_id DESC\r\n")
		List<ExhibitionDetailBean> getEnrollExhibitionSearchTitle(@Param("search") String search, RowBounds rowBounds);
		
		// 전시회 등록 신청 제목 검색 배지 개수 반환
		@Select("SELECT \r\n"
				+ "    COUNT(*) AS total_exhibition_eroll_count,\r\n"
				+ "    COUNT(CASE WHEN enroll_state = 1 THEN 1 END) AS stay_exhibition_eroll_count,\r\n"
				+ "    COUNT(CASE WHEN enroll_state = 2 THEN 1 END) AS complete_exhibition_eroll_count\r\n"
				+ "FROM \r\n"
				+ "    exhibition_enroll e\r\n"
				+ "JOIN \r\n"
				+ "    user_table u ON e.apply_person = u.user_id\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(e.title) LIKE '%' || #{search} || '%'\r\n"
				+ "ORDER BY \r\n"
				+ "    e.exhibition_enroll_id DESC")
		ExhibitionDetailBean getEnrollExhibitionSearchTitleBadgeCnt(@Param("search") String search);
		
		// 전시회 등록 신청 제목 검색 페이징 처리를 위한 총 개수 반환
		@Select("SELECT \r\n"
				+ "    COUNT(*)\r\n"
				+ "FROM \r\n"
				+ "    exhibition_enroll e\r\n"
				+ "JOIN \r\n"
				+ "    user_table u ON e.apply_person = u.user_id\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(e.title) LIKE '%' || #{search} || '%'\r\n"
				+ "ORDER BY \r\n"
				+ "    e.exhibition_enroll_id DESC")
		int getEnrollExhibitionSearchTitletotalCnt(@Param("search") String search);
		
		
		// 전시회 등록 신청 신청인 검색
		@Select("SELECT \r\n"
				+ "    e.exhibition_enroll_id, \r\n"
				+ "    e.title, \r\n"
				+ "    e.author, \r\n"
				+ "    e.price, \r\n"
				+ "    TO_CHAR(e.exhibition_start, 'yyyy-mm-dd') AS exhibition_start, \r\n"
				+ "    TO_CHAR(e.exhibition_end, 'yyyy-mm-dd') AS exhibition_end, \r\n"
				+ "    e.enroll_state, \r\n"
				+ "    u.name AS apply_name\r\n"
				+ "FROM \r\n"
				+ "    exhibition_enroll e\r\n"
				+ "JOIN \r\n"
				+ "    user_table u ON e.apply_person = u.user_id\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(u.name) LIKE '%' || #{search} || '%'\r\n"
				+ "ORDER BY \r\n"
				+ "    e.exhibition_enroll_id DESC\r\n")
		List<ExhibitionDetailBean> getEnrollExhibitionSearchapply_person(@Param("search") String search, RowBounds rowBounds);
		
		// 전시회 등록 신청 제목 검색 배지 개수 반환
		@Select("SELECT \r\n"
				+ "    COUNT(*) AS total_exhibition_eroll_count,\r\n"
				+ "    COUNT(CASE WHEN enroll_state = 1 THEN 1 END) AS stay_exhibition_eroll_count,\r\n"
				+ "    COUNT(CASE WHEN enroll_state = 2 THEN 1 END) AS complete_exhibition_eroll_count\r\n"
				+ "FROM \r\n"
				+ "    exhibition_enroll e\r\n"
				+ "JOIN \r\n"
				+ "    user_table u ON e.apply_person = u.user_id\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(u.name) LIKE '%' || #{search} || '%'\r\n"
				+ "ORDER BY \r\n"
				+ "    e.exhibition_enroll_id DESC")
		ExhibitionDetailBean getEnrollExhibitionSearchapply_personBadgeCnt(@Param("search") String search);		
		
		// 전시회 등록 신청 제목 검색 페이징 처리를 위한 총 개수 반환
		@Select("SELECT \r\n"
				+ "    COUNT(*)\r\n"
				+ "FROM \r\n"
				+ "    exhibition_enroll e\r\n"
				+ "JOIN \r\n"
				+ "    user_table u ON e.apply_person = u.user_id\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(u.name) LIKE '%' || #{search} || '%'\r\n"
				+ "ORDER BY \r\n"
				+ "    e.exhibition_enroll_id DESC")
		int getEnrollExhibitionSearchapply_persontotalCnt(@Param("search") String search);		
		
		// 전시회 등록 신청 작가 검색
		@Select("SELECT \r\n"
				+ "    e.exhibition_enroll_id, \r\n"
				+ "    e.title, \r\n"
				+ "    e.author, \r\n"
				+ "    e.price, \r\n"
				+ "    TO_CHAR(e.exhibition_start, 'yyyy-mm-dd') AS exhibition_start, \r\n"
				+ "    TO_CHAR(e.exhibition_end, 'yyyy-mm-dd') AS exhibition_end, \r\n"
				+ "    e.enroll_state, \r\n"
				+ "    u.name AS apply_name\r\n"
				+ "FROM \r\n"
				+ "    exhibition_enroll e\r\n"
				+ "JOIN \r\n"
				+ "    user_table u ON e.apply_person = u.user_id\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(e.author) LIKE '%' || #{search} || '%'\r\n"
				+ "ORDER BY \r\n"
				+ "    e.exhibition_enroll_id DESC\r\n")
		List<ExhibitionDetailBean> getEnrollExhibitionSearchauthor(@Param("search") String search, RowBounds rowBounds);
		
		// 전시회 등록 신청 제목 검색 배지 개수 반환
		@Select("SELECT \r\n"
				+ "    COUNT(*) AS total_exhibition_eroll_count,\r\n"
				+ "    COUNT(CASE WHEN enroll_state = 1 THEN 1 END) AS stay_exhibition_eroll_count,\r\n"
				+ "    COUNT(CASE WHEN enroll_state = 2 THEN 1 END) AS complete_exhibition_eroll_count\r\n"
				+ "FROM \r\n"
				+ "    exhibition_enroll e\r\n"
				+ "JOIN \r\n"
				+ "    user_table u ON e.apply_person = u.user_id\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(e.author) LIKE '%' || #{search} || '%'\r\n"
				+ "ORDER BY \r\n"
				+ "    e.exhibition_enroll_id DESC")
		ExhibitionDetailBean getEnrollExhibitionSearchauthorBadgeCnt(@Param("search") String search);	
		
		// 전시회 등록 신청 제목 검색 페이징 처리를 위한 총 개수 반환
		@Select("SELECT \r\n"
				+ "    COUNT(*)\r\n"
				+ "FROM \r\n"
				+ "    exhibition_enroll e\r\n"
				+ "JOIN \r\n"
				+ "    user_table u ON e.apply_person = u.user_id\r\n"
				+ "WHERE \r\n"
				+ "    UPPER(e.author) LIKE '%' || #{search} || '%'\r\n"
				+ "ORDER BY \r\n"
				+ "    e.exhibition_enroll_id DESC")
		int getEnrollExhibitionSearchauthortotalCnt(@Param("search") String search);	
		
		// 전시회 등록 신청 상태 검색
		@Select("SELECT \r\n"
				+ "    e.exhibition_enroll_id, \r\n"
				+ "    e.title, \r\n"
				+ "    e.author, \r\n"
				+ "    e.price, \r\n"
				+ "    TO_CHAR(e.exhibition_start, 'yyyy-mm-dd') AS exhibition_start, \r\n"
				+ "    TO_CHAR(e.exhibition_end, 'yyyy-mm-dd') AS exhibition_end, \r\n"
				+ "    e.enroll_state, \r\n"
				+ "    u.name AS apply_name\r\n"
				+ "FROM \r\n"
				+ "    exhibition_enroll e\r\n"
				+ "JOIN \r\n"
				+ "    user_table u ON e.apply_person = u.user_id\r\n"
				+ "WHERE \r\n"
				+ "    e.enroll_state = #{search} \r\n"
				+ "ORDER BY \r\n"
				+ "    e.exhibition_enroll_id DESC")
		List<ExhibitionDetailBean> getEnrollExhibitionSearchstate(@Param("search") int search, RowBounds rowBounds);
		
		// 전시회 등록 신청 상태 검색 배지 개수 반환
		@Select("SELECT \r\n"
				+ "    COUNT(*) AS total_exhibition_eroll_count,\r\n"
				+ "    COUNT(CASE WHEN enroll_state = 1 THEN 1 END) AS stay_exhibition_eroll_count,\r\n"
				+ "    COUNT(CASE WHEN enroll_state = 2 THEN 1 END) AS complete_exhibition_eroll_count\r\n"
				+ "FROM \r\n"
				+ "    exhibition_enroll e\r\n"
				+ "JOIN \r\n"
				+ "    user_table u ON e.apply_person = u.user_id\r\n"
				+ "WHERE \r\n"
				+ "    e.enroll_state = #{search} \r\n"
				+ "ORDER BY \r\n"
				+ "    e.exhibition_enroll_id DESC")
		ExhibitionDetailBean getEnrollExhibitionSearchstateBadgeCnt(@Param("search") int search);	
		
		// 전시회 등록 신청 제목 검색 페이징 처리를 위한 총 개수 반환
		@Select("SELECT \r\n"
				+ "    COUNT(*)\r\n"
				+ "FROM \r\n"
				+ "    exhibition_enroll e\r\n"
				+ "JOIN \r\n"
				+ "    user_table u ON e.apply_person = u.user_id\r\n"
				+ "WHERE \r\n"
				+ "    e.enroll_state = #{search} \r\n"
				+ "ORDER BY \r\n"
				+ "    e.exhibition_enroll_id DESC")
		int getEnrollExhibitionSearchstatetotalCnt(@Param("search") int search);	
		

}
