package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String printUsers(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/users");
        User user = userService.getUserById(id);
        if (user != null) {
            userService.delete(user);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/users");
        userService.add(user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        User tempUser = userService.getUserById(id);
        modelAndView.addObject("user", tempUser);
        modelAndView.setViewName("edit");
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@RequestParam(value = "id") Long id, @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        user.setId(id);
        modelAndView.setViewName("redirect:/users");
        userService.update(user);
        return modelAndView;
    }

}
