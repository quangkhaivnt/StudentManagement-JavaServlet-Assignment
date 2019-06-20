/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khainq.controller;

import com.google.gson.Gson;
import com.khainq.dao.StudentDao;
import com.khainq.model.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet("/Student/*")
public class StudentController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private StudentDao dao;

    @Override
    public void init() {
        try {
            dao = new StudentDao();
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
                    insertStudent(req, resp);
                    break;
                case "delete":
                    deleteStudent(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "update":
                    updateStudent(req, resp);
                    break;
                case "search":
                    searchStudent(req, resp);
                    break;
                default:
                    listStudent(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    private void listStudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List<Student> listStudent = dao.selectAllStudent();
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/View/Student/student-list.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    public void searchStudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        String name = request.getParameter("name");
        List<Student> listStudent = dao.searchStudent(name);
        Gson gson = new Gson();
        String object = gson.toJson(listStudent);
        out.write(object);
        out.flush();
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/View/Student/student-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = dao.selectStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/View/Student/student-form.jsp");
        request.setAttribute("student", existingStudent);
        dispatcher.forward(request, response);

    }
    
    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException,ServletException, IOException {
        String studentCode = request.getParameter("studentCode");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        int idNumber = Integer.parseInt(request.getParameter("idNumber"));
        int phone = Integer.parseInt(request.getParameter("phone"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        String email = request.getParameter("email");
        Student stu = new Student(studentCode,name,birthday,address,idNumber,phone,gender,email);
        dao.insertStudent(stu);
        response.sendRedirect("list");
    }
    
    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException,ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String studentCode = request.getParameter("studentCode");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        int idNumber = Integer.parseInt(request.getParameter("idNumber"));
        int phone = Integer.parseInt(request.getParameter("phone"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        String email = request.getParameter("email");
        int status = Integer.parseInt(request.getParameter("status"));
        Student stu = new Student(id, studentCode, name, birthday, address, idNumber, phone, gender, email, status);
        dao.updateStudent(stu);
        response.sendRedirect("list");
    }
    
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException,ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deleteStudent(id);
        response.sendRedirect("list");

    }
    
}
