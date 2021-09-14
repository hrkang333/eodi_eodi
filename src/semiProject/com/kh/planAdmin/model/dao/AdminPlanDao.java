package semiProject.com.kh.planAdmin.model.dao;

import static semiProject.com.kh.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import semiProject.com.kh.course.model.vo.Course;
import semiProject.com.kh.course.model.vo.CoursePlace;
import semiProject.com.kh.planAdmin.model.vo.AdminPlan;
import semiProject.com.kh.planMy.model.dao.PlanMyDao;

public class AdminPlanDao {
	
	private Properties prop = new Properties();


 public AdminPlanDao() {
		String fileName = PlanMyDao.class.getResource("/semiProject/sql/adminPlan/adminPlan-query.properties").getPath();
		System.out.println("fileName   " + fileName);
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	 

	//코스 리스트 가져오는 애 
	public ArrayList<CoursePlace> selectCourseList(Connection conn) {
		//관리자가 짜준 코스가 admin_place에 저장되어있고 
		//참조하는 번호만 가져오면 되는가..?
		
//		CP_NO 코스지역번호 -> 시퀀스로 순서대로 들어가는 
//		REF_COURSE 참조하는 코스번호 
//		PLACE_NO 일정번호 
//		STATUS 상태값 
		
//	adminCList=select ref_course, status from course_place; 
		
		ArrayList<CoursePlace> cList = new ArrayList<>();
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("adminCList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset=pstmt.executeQuery();

			//참조번호와(코스가 들어가 있는), y/n상태값 담아줌 
			while(rset.next()) {
				
				CoursePlace cp = new CoursePlace();
				cp.setRefCourse(rset.getInt("REF_COURSE"));
				cp.setStatus(rset.getString("STATUS"));
				
				cList.add(cp);
	
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return cList;
	}


	//AdminPlan에 값 넣기 
	public int insertAdminPlan(Connection conn, AdminPlan apList) {
//insertAdminPlan=insert into admin_plan VALUES(SEQ_APNO.NEXTVAL,?,?,?,?,SYSDATE,DEFAULT);

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertAdminPlan");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, apList.getPlanNo());
			pstmt.setInt(2, apList.getUserNo());
			pstmt.setInt(3, apList.getAreaNo());
			pstmt.setInt(4, apList.getCourseNo());
			pstmt.setString(5, apList.getPlanTitle());
			pstmt.setDate(6, apList.getCreateDate());
			pstmt.setString(7, apList.getStatus());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}

	//admin_plan_place에 값 넣기 
	public int insertAdminPlanPlace(Connection conn, ArrayList<CoursePlace> cList) {
//insertAdminPlan_Place=INSERT INTO adminPlace_Place VALUES(SEQ_APPNO.NEXTVAL,?, ?, DEFAULT);
		//왜 부모키를 찾을 수 없다고 뜨는것인가... 

//		AP_PLACE_NO
//		REF_ACNO
//		PLACE_NO
//		STATUS

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertAdminPlanPlace");

		try {
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < cList.size(); i++) {

				pstmt.setInt(1, Integer.parseInt(cList.get(i).toString()));

				result = pstmt.executeUpdate();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}



	//
	public int CreatePlan(Connection conn, AdminPlan apList) {


		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertAdminPlan");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, apList.getPlanNo());
			pstmt.setInt(2, apList.getUserNo());
			pstmt.setInt(3, apList.getAreaNo());
			pstmt.setInt(4, apList.getCourseNo());
			pstmt.setString(5, apList.getPlanTitle());
			pstmt.setDate(6, apList.getCreateDate());
			pstmt.setString(7, apList.getStatus());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
 
		return result;
	}

}
