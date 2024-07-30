package test.mydemo.Controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Logintroller {

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        if ("admin".equals(username) && "password".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            return new ModelAndView("redirect:/home");
        } else {
            return new ModelAndView("redirect:/error");
        }
    }

    @GetMapping("/home")
    public String home() {
        return "map";
    }
    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate(); // Invalidate the session to log out the user
        return "redirect:/login"; // Redirect to login page after logout
    }
}