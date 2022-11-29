package Resources.login;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //request id, pwd
        String r_id = request.getParameter("id");
        String r_pwd = request.getParameter("password");

        LoginDAO dao = new LoginDAO(r_id);

        LoginVO loginVO = dao.LoginQuery();
        //String db_id = loginVO.getId();
        String db_pwd = loginVO.getPwd();

        if (db_pwd.equals(r_pwd)) {
            /*
            HttpSession session = request.getSession();
            for(int i=0;i<list.size();i++) {
                session.setAttribute("team" + i, list.team_id);
             */
            Cookie cookie = new Cookie("name", loginVO.getId());
            response.addCookie(cookie);
            response.sendRedirect("Main.html");
        } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('올바르지 않은 ID이거나 Password입니다.'); location.href='login_page.html';</script>");
            out.flush();
        }
    }
}
