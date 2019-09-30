package at.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyServlet", urlPatterns = { "/MyServlet" })
public class MyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		List<String> result = new ArrayList<>();

		result.addAll(Collections.list(MyServlet.class.getClassLoader().getResources("at/sample/MyServlet.class")).stream()
				.map(it -> it.toString()).collect(Collectors.toList()));
		result.addAll(Collections.list(MyServlet.class.getClassLoader().getResources("at/sample/MyUtil.class")).stream()
				.map(it -> it.toString()).collect(Collectors.toList()));
		result.addAll(Collections.list(MyServlet.class.getClassLoader().getResources("at/sample/MyTest.class")).stream()
				.map(it -> it.toString()).collect(Collectors.toList()));

		PrintWriter out = resp.getWriter();
		result.stream().map(it -> "<h1>" + it + "</it>").forEach(out::println);
	}

}
