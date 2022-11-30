package ex02.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimitiveServlet implements Servlet {

  public void init(ServletConfig config) throws ServletException {
    System.out.println("init");
  }

  public void service(ServletRequest request, ServletResponse response)
          throws ServletException, IOException {
    System.out.println("from service");
    PrintWriter out = response.getWriter();

    StringBuilder body = new StringBuilder();
    body.append("Hello. Roses are red.");
    body.append("Violets are blue.");
    int totalLen = body.length();
    String message = "HTTP/1.1 200 State OK \r\n" +
            "Content-Type: text/html\r\n" +
            "Content-Length:  " + totalLen  + "\r\n" +
            "\r\n";
    out.println(message);
    out.println(body.toString());
  }

  public void destroy() {
    System.out.println("destroy");
  }

  public String getServletInfo() {
    return null;
  }
  public ServletConfig getServletConfig() {
    return null;
  }

}
