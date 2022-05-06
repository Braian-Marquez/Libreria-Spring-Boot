package edu.egg.spring.controller;

import edu.egg.spring.entity.Role;
import edu.egg.spring.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/form")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("role-form");
        mav.addObject("role", new Role());
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Role roleDto) {
        RedirectView redirect = new RedirectView("/");
        roleService.create(roleDto);
        return redirect;
    }
}
