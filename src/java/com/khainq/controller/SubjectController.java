/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khainq.controller;

import com.khainq.dao.StudentDao;
import com.khainq.dao.SubjectDao;
import com.khainq.model.Student;
import com.khainq.model.Subjects;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet("/Subject/*")
public class SubjectController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private SubjectDao dao;

    @Override
    public void init() {
        try {
            dao = new SubjectDao();
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo().substring(1);

        try {
            switch (action) {
                case "new":
                    showNewForm(req, resp);
                    break;
                case "insert":
                    insertSubject(req, resp);
                    break;
                case "delete":
                    deleteSubject(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "update":
                    updateSubject(req, resp);
                    break;
                default:
                    listSubject(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    private void listSubject(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List <Subjects> listSubject;
        listSubject = dao.selectAllSubject();
        request.setAttribute("listSubject", listSubject);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/View/Subject/subject-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/View/Subject/subject-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Subjects existingSubject;
        existingSubject = dao.selectSubject(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/View/Subject/subject-form.jsp");
        request.setAttribute("subject", existingSubject);
        dispatcher.forward(request, response);

    }
    
    private void insertSubject(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String subjectCode = request.getParameter("subjectCode");
        String subjectName = request.getParameter("subjectName");
        int status = Integer.parseInt(request.getParameter("status"));
        Subjects sub = new Subjects(subjectCode,subjectName,status);
        dao.insertSubject(sub);
        response.sendRedirect("list");
    }
    
    private void updateSubject(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String subjectCode = request.getParameter("subjectCode");
        String subjectName = request.getParameter("subjectName");
        int status = Integer.parseInt(request.getParameter("status"));
        Subjects sub = new Subjects(id, subjectCode, subjectName,status);
        dao.updateSubject(sub);
        response.sendRedirect("list");
    }
    
    private void deleteSubject(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deleteSubject(id);
        response.sendRedirect("list");

    }
}
