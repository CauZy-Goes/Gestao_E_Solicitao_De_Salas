package io.github.cauzy.GSDS.Utility.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/pages/professor/*") // Protege todas as páginas dentro de "professor/"
public class professorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);


        if (session == null || session.getAttribute("professor") == null) {
            res.sendRedirect(req.getContextPath() + "/index.xhtml"); // Redireciona para login
            return;
        }

        chain.doFilter(request, response); // Continua o fluxo se estiver logado
    }
}
