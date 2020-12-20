package hvt.proekt.web;

import hvt.proekt.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/upload")
public class UploadFileController {
    private final AdminService adminService;

    @Autowired
    public UploadFileController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping
    public String UploadFile()
    {
        return "UploadFile";
    }

    @PostMapping("")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        adminService.store(file);

        return "redirect:/upload";
    }
}
