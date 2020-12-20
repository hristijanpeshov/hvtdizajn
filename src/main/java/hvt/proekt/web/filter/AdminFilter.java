package hvt.proekt.web.filter;

import org.springframework.context.annotation.ComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        String path = req.getServletPath();
        if((path.contains("/admin") || path.contains("upload")) && req.getSession().getAttribute("auth")==null) {
            resp.sendRedirect("/login");
        }
        else
        {
            filterChain.doFilter(req,resp);
        }

    }
}
