package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import org.json.*;

import data_access.DaoStudent;

/**
 * Servlet implementation class StudentById
 */
public class StudentById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentById() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		DaoStudent dao = new DaoStudent();
		String jsonObj = new BufferedReader(new InputStreamReader(request.getInputStream())).lines().collect(Collectors.joining());
		System.out.println(jsonObj);
		JSONObject obj = new JSONObject(jsonObj);
		Iterator<String> it = obj.keys();
		int id = -1;
		while(it.hasNext()) {
			String key = it.next();
			Object ob = obj.get(key);
			id = Integer.valueOf(ob.toString());
		}
		if(id != -1) {
			Student student = dao.getStudentById(id);
			System.out.println(student);
			out.print(gson.toJson(student));
		}
	}

	

}
