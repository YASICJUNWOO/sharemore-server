package sharemore.sharemoreserver.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ViewController {

    @RequestMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @GetMapping("/members")
    public String memberList() {
        return "members";
    }

}
