package semiProject.com.kh.place.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semiProject.com.kh.board.model.vo.PlaceAttachment;
import semiProject.com.kh.place.model.service.PlaceService;
import semiProject.com.kh.place.model.vo.Place;

/**
 * Servlet implementation class PlaceDetailServlet
 */
@WebServlet("/detail.pl")  //더보기 버튼 -> detail.pl로 이동하도록!
public class PlaceDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int pNo = Integer.parseInt(request.getParameter("pNo"));  //앞 페이지에서 pNo setAttribute해줘야 함

		Place p = new PlaceService().selectPlace(pNo);

		
		if(p != null)
		{
			request.setAttribute("p", p);
			request.getRequestDispatcher("views/place/placeDetail.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("msg", "일정 상세보기 실패");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
//		PlaceAttachment at = new PlaceService().selectAttachment(pNo);  //대표사진 한개
//		request.setAttribute("at", at);
	}

}
