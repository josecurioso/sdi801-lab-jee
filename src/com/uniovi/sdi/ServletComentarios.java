package com.uniovi.sdi;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletComentarios
 */
@WebServlet("/comentarios")
public class ServletComentarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletComentarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		ComentariosService cService = new ComentariosService();
		System.out.println("estamos");
		
		//Se ha hecho un comentario
		String user = (String) request.getParameter("user");
		String content = (String) request.getParameter("content");
		if(user != null) {
			System.out.println(user + content);
			cService.addComentario(new Comentario(user, content));
		}
		
		
		List<Comentario> comentarios = cService.getComentarios();
		comentarios.add(new Comentario("test", "tContent"));
		// Retornar la vista con parámetro "productosTienda"
		request.setAttribute("comentarios", comentarios);
		getServletContext().getRequestDispatcher("/vista-comentarios.jsp").forward(request,
		response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
