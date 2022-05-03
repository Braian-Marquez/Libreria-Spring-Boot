package edu.egg.spring.controller;
import edu.egg.spring.entity.Libro;
import edu.egg.spring.service.AutorService;
import edu.egg.spring.service.EditorialService;
import edu.egg.spring.service.LibroService;
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
@RequestMapping("/libro")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService libroService;
    private final AutorService autorService;
    private final EditorialService editorialService;

    @GetMapping
    public ModelAndView getLibro(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("table");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("libro", libroService.getAll());
        return mav;
    }


    @GetMapping("/form-libro")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("form-libro");
        mav.addObject("libro", new Libro());
        mav.addObject("autor", autorService.getAll());
        mav.addObject("editorial", editorialService.getAll());
        mav.addObject("action", "create");
        return mav;
    }

    @GetMapping("/form-libro/{id}")
    public ModelAndView getForm(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("form-libro");
        mav.addObject("libro", libroService.getById(id));
        mav.addObject("autor", autorService.getAll());
        mav.addObject("editorial", editorialService.getAll());
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Libro libro, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/libro");
        libroService.create(libro);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Libro libro, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/libro");
        libroService.update(libro);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        RedirectView redirect = new RedirectView("/libro");
        libroService.deleteById(id);
        return redirect;
    }
}
