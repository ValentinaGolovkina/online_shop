package ru.valensiya.online_shop;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    // GET http://localhost:8080/online_shop/products
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        out.println("<html><body>");
        for (int i=0; i<10; i++) {
            out.println("<h1>" + new Product(i, "Продукт"+i, i*57).toString() + "</h1>");
        }
        out.println("</body></html>");
        out.close();
    }
}
