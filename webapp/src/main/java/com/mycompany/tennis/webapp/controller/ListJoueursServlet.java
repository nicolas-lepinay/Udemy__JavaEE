package com.mycompany.tennis.webapp.controller;

import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.service.JoueurService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/listes") // URL
public class ListJoueursServlet extends HttpServlet {

    private JoueurService joueurService;

    public ListJoueursServlet() {
        this.joueurService = new JoueurService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<JoueurDto> listeHommes = joueurService.getListeJoueurs('H');
        List<JoueurDto> listeFemmes = joueurService.getListeJoueurs('F');

        req.setAttribute("listeHommes", listeHommes);
        req.setAttribute("listeFemmes", listeFemmes);
        RequestDispatcher disp = req.getRequestDispatcher("listes.jsp"); // Nom du fichier JSP
        disp.forward(req, resp);
    }
}
