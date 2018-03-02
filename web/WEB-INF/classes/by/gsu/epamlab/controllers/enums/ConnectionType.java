package by.gsu.epamlab.controllers.enums;

import by.gsu.epamlab.beans.Conference;
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


        private JSONArray getJsonArray(List<Conference> list) {
            JSONArray jsonArray = new JSONArray();

            for(Conference conference : list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", conference.getId());
                jsonObject.put("taskName", conference.getName());
                jsonArray.add(jsonObject);
            }

            return jsonArray;
        }

        @Override
        public void responseToClient(HttpServletRequest request, HttpServletResponse response) throws DaoException, IOException, ServletException {
            HttpSession session = request.getSession();
            String dateType = request.getParameter(Constants.DATE_TYPE_PARAMETER);
            dateType = dateType.toUpperCase();
            String id = (String)session.getAttribute(Constants.ID);
            List<Conference> taskList = ConferenceTypes.valueOf(dateType).getTasks(id);
            JSONArray jsonArrayTaskList = getJsonArray(taskList);
            JSONArray jsonArrayFileMap = getJsonMap(FileOperations.getFileForTask(request));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("taskList", jsonArrayTaskList);
            jsonObject.put("fileMap", jsonArrayFileMap);
            session.setAttribute("server", false); //<--- Hm...
            response.setContentType("application/x-json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonObject.toJSONString());
        }
    },
    NO_AJAX {
        @Override
        public void responseToClient(HttpServletRequest request, HttpServletResponse response) throws IOException, DaoException {
            HttpSession session = request.getSession();
            String dateType = request.getParameter(Constants.DATE_TYPE_PARAMETER);
            dateType = dateType.toUpperCase();
            String id = (String)session.getAttribute(Constants.ID);
            List<Conference> taskList = ConferenceTypes.valueOf(dateType).getTasks(id);
            session.setAttribute(Constants.CONFERENCE_LIST_NAME, taskList);
            session.setAttribute(Constants.FILE_MAP_PARAMETER, FileOperations.getFileForTask(request));
            session.setAttribute("server", true); //<--- Hm...
            response.sendRedirect(Constants.MAIN_URL);
        }
    };

    public abstract void responseToClient(HttpServletRequest request, HttpServletResponse response) throws IOException, DaoException, ServletException;
}
