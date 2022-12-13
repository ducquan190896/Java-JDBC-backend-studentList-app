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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.google.gson.Gson;

import data_access.DaoStudent;

/**
 * Servlet implementation class addStudent
 */
public class addStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String obj = new BufferedReader(new InputStreamReader(request.getInputStream())).lines().collect(Collectors.joining());
		System.out.println(obj);
		JSONObject jsonObj = new JSONObject(obj);
		System.out.println(jsonObj);
		Iterator<String> it = jsonObj.keys();
		Student student = new Student();
		Map<String, String> map= new HashMap<>();
		while(it.hasNext()) {
			String key = it.next();
			Object ob = jsonObj.get(key);
			//System.out.println(key + " _ " + ob.toString());
			map.put(key, ob.toString());
		}
		System.out.println(map.toString());
		student.setId(Integer.valueOf(map.get("id")));
		student.setFirstname(map.get("firstname"));
		student.setLastname(map.get("lastname"));
		student.setStreetAddress(map.get("streetAddress"));
		student.setPostCode(map.get("postCode"));
		student.setPostOffice(map.get("postOffice"));
		System.out.println(student);
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		DaoStudent dao = new DaoStudent();
		int errorCode = dao.addStudent(student);
		out.print(gson.toJson(errorCode));
	}

}
