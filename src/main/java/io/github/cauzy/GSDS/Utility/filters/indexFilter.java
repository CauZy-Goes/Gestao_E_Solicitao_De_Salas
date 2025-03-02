package io.github.cauzy.GSDS.Utility.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/index.xhtml") // Protege todas as páginas dentro de "gestor/"
public class indexFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0
        res.setHeader("Expires", "0"); // Proxies

        HttpSession session = req.getSession(false);

        // Verifica se o usuário está logado
        if (session != null) {
            if(session.getAttribute("gestor") != null){
                res.sendRedirect(req.getContextPath() + "/pages/gestor/dashboardGestor.xhtml");
                return;
            }

            if(session.getAttribute("professor") != null){
                res.sendRedirect(req.getContextPath() + "/pages/professor/dashboardProfessor.xhtml");
                return;
            }
        }

        chain.doFilter(request, response); // Continua o fluxo se estiver logado
    }
}
