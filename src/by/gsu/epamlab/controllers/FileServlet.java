package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.controllers.enums.TaskTypes;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.TaskDatabaseImplementation;
import by.gsu.epamlab.interfaces.ITaskDAO;


import javax.servlet.http.Part;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;




public class FileServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
       // try {
           /* Part part = request.getPart(Constants.FILE_PARAMETER);
            String fileName = extractFileName(part);
            String filePath = FileServlet.class.getProtectionDomain().getCodeSource().getLocation().getPath() + new File(fileName).getName();
            part.write(filePath); */
           download(request, response);
            //HttpSession session = request.getSession();
            //String id = (String)session.getAttribute(Constants.ID);
            //TaskTypes.valueOf("TODAY").addTask(id, filePath, null);
            request.getRequestDispatcher(Constants.MAIN_SERVLET_URL).forward(request, response);
        //} catch (DaoException e) {
       //     e.printStackTrace();
            // }
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return Constants.EMPTY_STRING;
    }

    private void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletContext().getRealPath("Hz.csv");
        String filePath = FileServlet.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        //String fileName = "C:" + File.separator + "Users" + File.separator + "Nazg'ul" +File.separator +  "WebProject" +File.separator + "Hz.csv";
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        response.addHeader("Content-Disposition","attachment;filename="+"Hz.csv");
        File f = new File(/*getServletContext().getRealPath("/"),filePath+"/"+*/"Hz.csv");
        long len = f.length();
        response.addHeader("Content-Length", String.valueOf(len));
        response.setContentType("application/download");
        FileInputStream fileInputStream = new FileInputStream(f);
        int i;
        while((i=fileInputStream.read())!=-1){
            response.getOutputStream().write(i);
        }
        fileInputStream.close();
        response.getOutputStream().close();
    }
}
