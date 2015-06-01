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
	match.Matchini();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub

	HttpSession session = request.getSession();

	if ("".equals(match.session1)) {
	    match.session1 = session.getId();
	} else if ((!session.getId().equals(match.session1)) && "".equals(match.session2)) {
	    match.session2 = session.getId();
	}

	if (!(match.isRound && match.session1.equals(session.getId()))) {
	    System.out.println("feifa1");
	    // return;
	} else if (!(!match.isRound && match.session2.equals(session.getId()))) {
	    System.out.println("feifa2");
	    // return;
	}

	response.setContentType("text/html;charset=utf-8");
	PrintWriter out = response.getWriter();
	request.setCharacterEncoding("utf-8");
	String para = request.getParameter("para");

	if ("1".equals(para))
	    match.zhuapai(session.getId());
	if ("2".equals(para.substring(0, 1))) {
	    System.out.println(new Integer(para.substring(2)));
	    match.dachu(session.getId(), new Integer(para.substring(2)));
	}
	if ("ini".equals(para))
	    match.Matchini();
	if ("stop".equals(para))
	    match.stop();
	if ("attack".equals(para))
	    match.attack(session.getId());
	String s = match.getAll(match);
	out.print(s);
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
