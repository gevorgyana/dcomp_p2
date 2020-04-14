import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import DatabaseConnection.UniversityDatabaseConnection;

@WebServlet("/university")
public class UniversityServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", UniversityDatabaseConnection.getAllStudents());
        req.setAttribute("groups", UniversityDatabaseConnection.getAllGroups());
        getServletContext().getRequestDispatcher("/info.jsp").forward(req, resp);
    }
}
