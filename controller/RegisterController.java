package controller;

import java.io.IOException;

import service.UserService;
import service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REGISTER_VIEW = "/views/register.jsp";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			resp.sendRedirect(req.getContextPath() + "/admin");
			return;
		}

		// Check cookie
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					session = req.getSession(true);
					session.setAttribute("username", cookie.getValue());
					resp.sendRedirect(req.getContextPath() + "/admin");
					return;
				}
			}
		}

		req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");

		UserService service = new UserServiceImpl();
		String alertMsg = "";
		if (username.trim().isEmpty() || password.trim().isEmpty() || email.trim().isEmpty()
				|| fullname.trim().isEmpty() || phone.trim().isEmpty()) {
			alertMsg = "Dữ liệu còn trống!";
			req.setAttribute("alert", alertMsg);

			req.setAttribute("username", username.trim());
			req.setAttribute("email", email.trim());
			req.setAttribute("fullname", fullname.trim());
			req.setAttribute("phone", phone.trim());

			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
			return;
		}
		if (service.checkExistUsername(username)) {
			alertMsg = "Tài khoản đã tồn tại!";
			req.setAttribute("alert", alertMsg);
			req.setAttribute("username", username.trim());
			req.setAttribute("email", email.trim());
			req.setAttribute("fullname", fullname.trim());
			req.setAttribute("phone", phone.trim());
			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
			return;
		}
		if (service.checkExistEmail(email)) {
			alertMsg = "Email đã tồn tại!";
			req.setAttribute("alert", alertMsg);
			req.setAttribute("username", username.trim());
			req.setAttribute("email", email.trim());
			req.setAttribute("fullname", fullname.trim());
			req.setAttribute("phone", phone.trim());
			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
			return;
		}

		boolean isSuccess = service.register(email, password, username, fullname, phone);
		if (isSuccess) {
			resp.sendRedirect(req.getContextPath() + "/login");
			System.out.print("dang ki thanh cong");
		} else {
			alertMsg = "System error!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
		}
	}
	public static final String REGISTER = "/views/register.jsp";
}
