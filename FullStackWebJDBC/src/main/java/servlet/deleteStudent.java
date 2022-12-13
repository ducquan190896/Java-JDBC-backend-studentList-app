package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.google.gson.Gson;

import data_access.DaoStudent;


public class deleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public deleteStudent() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String obj = new BufferedReader(new InputStreamReader(request.getInputStream())).lines().collect(Collectors.joining());
		JSONObject jsonObj = new JSONObject(obj);
		System.out.println(jsonObj);
		int id = -1;
		Iterator<String> it = jsonObj.keys();
		while(it.hasNext()) {
			id = Integer.valueOf(jsonObj.get(it.next()).toString());
		}
		System.out.println(id);
		if(id != -1) {
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			DaoStudent dao = new DaoStudent();
			int result = dao.deleteStudent(id);
			out.print(gson.toJson(result));
		}
	}

}
