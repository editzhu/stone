package com.jim.stone.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jim.stone.pai.Match;

/**
 * Servlet implementation class Fapai
 */
public class Fapai extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Match match = new Match();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fapai() {
	super();
	match.matchini();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub

	HttpSession session = request.getSession();
	String sessionId = session.getId();
	if ("".equals(match.session1)) {
	    match.session1 = sessionId;
	} else if ((!session.getId().equals(match.session1)) && "".equals(match.session2)) {
	    match.session2 = sessionId;
	}

	response.setContentType("text/html;charset=utf-8");
	PrintWriter out = response.getWriter();
	request.setCharacterEncoding("utf-8");
	String para = request.getParameter("para");
	String fromPai = request.getParameter("frompai");
	String toPai = request.getParameter("topai");
	System.out.println("f" + fromPai);
	String rs = "";
	System.out.println("para:" + para);
	if ("flash".equals(para))
	    rs = match.reponse("flash", sessionId, -1, -1);
	if ("zhuapai".equals(para))
	    rs = match.reponse("zhuapai", sessionId, -1, -1);
	if ("dachu".equals(para)) {
	    rs = match.reponse("dachu", sessionId, new Integer(fromPai), -1);
	}
	if ("ini".equals(para))
	    rs = match.reponse("ini", sessionId, -1, -1);
	if ("stop".equals(para))
	    rs = match.reponse("stop", sessionId, -1, -1);
	if ("attack".equals(para))
	    rs = match.reponse("attack", sessionId, new Integer(fromPai), new Integer(toPai));
	System.out.println("rs: " + rs);
	out.print(rs);
	out.flush();
	out.close();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub

    }

}
