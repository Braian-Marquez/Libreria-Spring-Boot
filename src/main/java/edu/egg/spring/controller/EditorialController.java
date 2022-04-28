package edu.egg.spring.controller;
import edu.egg.spring.entity.Editorial;
import edu.egg.spring.service.EditorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/editorial")
@RequiredArgsConstructor
public class EditorialController {

    private final EditorialService editorialService;

    @GetMapping
    public ModelAndView getEditorial(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("table-editorial");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("editorial", editorialService.getAll());
        return mav;
    }

    @GetMapping("/form-editorial")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("form-editorial");
        mav.addObject("editorial", new Editorial());
        mav.addObject("action", "create");
        return mav;
    }

    @GetMapping("/form-editorial/{id}")
    public ModelAndView getForm(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("form-editorial");
        mav.addObject("editorial", editorialService.getById(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Editorial editorial, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/editorial");
        editorialService.create(editorial);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Editorial  editorial, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/editorial");
        editorialService.update(editorial);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        RedirectView redirect = new RedirectView("/editorial");
        editorialService.deleteById(id);
        return redirect;
    }
}


