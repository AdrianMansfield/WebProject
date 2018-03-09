package by.gsu.epamlab.controllers.enums;

import by.gsu.epamlab.beans.Jsonable;
import by.gsu.epamlab.beans.conference.Conference;
import by.gsu.epamlab.beans.FileOperations;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DaoException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum ConnectionType {
    AJAX {

        private JSONArray getJsonMap(Map<String, String> map) {
            JSONArray jsonArray = new JSONArray();
            for(Map.Entry<String, String> entry : map.entrySet()) {
                String taskName = entry.getKey();
                String fileName = entry.getValue();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("taskName", taskName);
                jsonObject.put("fileName", fileName);
                jsonArray.add(jsonObject);
            }

            return jsonArray;
        }


        private <T extends Jsonable> JSONArray getJsonArray(List<T> list) {
            JSONArray jsonArray = new JSONArray();

            for(T someObject : list) {
                jsonArray.add(someObject.toJson());
            }

            return jsonArray;
        }

        @Override
        public void conferenceResponse(HttpServletRequest request, HttpServletResponse response) throws DaoException, IOException, ServletException {
            HttpSession session = request.getSession();
            String dateType = request.getParameter(Constants.DATE_TYPE_PARAMETER);
            dateType = dateType.toUpperCase();
            String id = (String)session.getAttribute(Constants.ID);
            List<Conference> conferenceList = ConferencePrintTypes.valueOf(dateType).getConferences(id);
            JSONArray jsonArrayTaskList = getJsonArray(conferenceList);
            JSONArray jsonArrayFileMap = getJsonMap(FileOperations.getFileForTask(request));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("conferenceList", jsonArrayTaskList);
            jsonObject.put("fileMap", jsonArrayFileMap);
            jsonObject.put("isBasket", dateType.equals("basket".toUpperCase()));
            session.setAttribute(Constants.CONFERENCE_LIST_NAME, new ArrayList<>());
            response.setContentType("application/x-json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonObject.toJSONString());
        }


        @Override
        public void moveConferenceResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, DaoException, ServletException {
            String conferenceId = request.getParameter("deleteConferenceCheck");
            String typeLocation = request.getParameter("typeLocation");
            ConferenceLocationTypes.valueOf(typeLocation.toUpperCase()).moveConference(conferenceId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("conferenceId",conferenceId);
            response.getWriter().write(jsonObject.toJSONString());
        }
    },
    NO_AJAX {
        @Override
        public void conferenceResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, DaoException {
            HttpSession session = request.getSession();
            String dateType = request.getParameter(Constants.DATE_TYPE_PARAMETER);
            dateType = dateType.toUpperCase();
            String id = (String)session.getAttribute(Constants.ID);
            List<Conference> taskList = ConferencePrintTypes.valueOf(dateType).getConferences(id);
            session.setAttribute(Constants.CONFERENCE_LIST_NAME, taskList);
            session.setAttribute(Constants.FILE_MAP_PARAMETER, FileOperations.getFileForTask(request));
            session.setAttribute("isBasket", dateType.equals("basket"));
            response.sendRedirect(Constants.MAIN_URL);
        }


        @Override
        public void moveConferenceResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, DaoException, ServletException {
            String  conferenceId = request.getParameter("deleteConferenceCheck");
            String typeLocation = request.getParameter("typeLocation");
            ConferenceLocationTypes.valueOf(typeLocation.toUpperCase()).moveConference(conferenceId);
            response.sendRedirect(Constants.MAIN_URL);
        }
    };

    public abstract void conferenceResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, DaoException, ServletException;

    public abstract void moveConferenceResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, DaoException, ServletException;
}
