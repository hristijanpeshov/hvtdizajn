package hvt.proekt.web;

import hvt.proekt.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class AuthController {
    private final AdminService adminService;

    public AuthController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String login()
    {
        return "login";
    }
    @PostMapping
    public String auth(@RequestParam String password, HttpServletRequest request)
    {
        if(adminService.auth(password))
        {
            request.getSession().setAttribute("auth",true);
            return "redirect:/admin";
        }
        return "redirect:/login";
    }
}
