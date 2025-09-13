package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/logout", "/dangx" })
public class LogoutController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		Cookie rm = new Cookie("username", "");
		rm.setMaxAge(0); // xóa ngay
		// đảm bảo path khớp để trình duyệt xóa đúng cookie
		String ctx = req.getContextPath();
		rm.setPath((ctx == null || ctx.isEmpty()) ? "/" : ctx);
		resp.addCookie(rm);

		// 3) Quay về trang login
		resp.sendRedirect(req.getContextPath() + "/login");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
