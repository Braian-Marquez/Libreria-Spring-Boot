package edu.egg.spring.controller;
import edu.egg.spring.entity.Autor;
import edu.egg.spring.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;



@Controller
@RequestMapping("/autor")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @GetMapping
    public ModelAndView getAutor(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("table-autores");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("autor", autorService.getAll());
        return mav;
    }

    @GetMapping("/form-autor")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("form-autor");
        mav.addObject("autor", new Autor());
        mav.addObject("action", "create");
        return mav;
    }

    @GetMapping("/form-autor/{id}")
    public ModelAndView getForm(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("form-autor");
        mav.addObject("autor", autorService.getById(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Autor autor, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/autor");
        autorService.create(autor);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Autor autor, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/autor");
        autorService.update(autor);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        RedirectView redirect = new RedirectView("/autor");
        autorService.deleteById(id);
        return redirect;
    }
}





