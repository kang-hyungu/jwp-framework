package next.controller;

import core.annotation.web.Controller;
import core.annotation.web.RequestMapping;
import core.db.DataBase;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/users")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return new ModelAndView(new JspView("redirect:/users/loginForm"));
        }

        request.setAttribute("users", DataBase.findAll());

        return new ModelAndView(new JspView("/user/list.jsp"));
    }

    @RequestMapping("/users/create")
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        User user = new User(request.getParameter("userId"),
                             request.getParameter("password"),
                             request.getParameter("name"),
                             request.getParameter("email"));

        log.debug("User : {}", user);

        DataBase.addUser(user);

        return new ModelAndView(new JspView("redirect:/"));
    }

    @RequestMapping("/users/update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        User user = DataBase.findUserById(request.getParameter("userId"));
        if (!UserSessionUtils.isSameUser(request.getSession(), user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }

        User updateUser = new User(request.getParameter("userId"),
                             request.getParameter("password"),
                             request.getParameter("name"),
                             request.getParameter("email"));

        log.debug("Update User : {}", updateUser);
        user.update(updateUser);

        return new ModelAndView(new JspView("redirect:/"));
    }
}
