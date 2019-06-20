/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khainq.controller;

import com.khainq.dao.PointInformationDao;
import com.khainq.dao.SubjectDao;
import com.khainq.model.PointInformation;
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
@WebServlet("/PointInformation/*")
public class PointInformationController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private PointInformationDao dao;

    @Override
    public void init() {
        try {
            dao = new PointInformationDao();
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
                    insertPointInformation(req, resp);
                    break;
                case "delete":
                    deletePointInformation(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "update":
                    updatePointInformation(req, resp);
                    break;
                default:
                    listPointInformation(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    private void listPointInformation(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List <PointInformation> listPointInformation = dao.selectAllPointInformation();
        request.setAttribute("listPointInformation", listPointInformation);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/View/PointInformation/pointInformation-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/View/PointInformation/pointInformation-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PointInformation existingPoint = dao.selectPointInformation(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/View/PointInformation/pointInformation-form.jsp");
        request.setAttribute("point", existingPoint);
        dispatcher.forward(request, response);

    }
    
    private void insertPointInformation(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String subjectCode = request.getParameter("subjectCode");
        String studentCode = request.getParameter("studentCode");
        float point = Float.parseFloat(request.getParameter("point"));
        PointInformation pi = new PointInformation(subjectCode,studentCode,point);
        dao.insertPointInformation(pi);
        response.sendRedirect("list");
    }
    
    private void updatePointInformation(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String subjectCode = request.getParameter("subjectCode");
        String studentCode = request.getParameter("studentCode");
        float point = Float.parseFloat(request.getParameter("point"));
        PointInformation pi = new PointInformation(id, subjectCode, studentCode,point);
        dao.updatePointInformation(pi);
        response.sendRedirect("list");
    }
    
    private void deletePointInformation(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deletePointInformation(id);
        response.sendRedirect("list");

    }
}
