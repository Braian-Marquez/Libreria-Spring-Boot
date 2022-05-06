package edu.egg.spring.controller;

import edu.egg.spring.entity.Libro;
import edu.egg.spring.entity.Prestamo;
import edu.egg.spring.service.*;
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
@RequestMapping("/prestamo")
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoService prestamoService;
    private final ClienteService clienteService;
    private final LibroService libroService;

    @GetMapping
    public ModelAndView getPrestamo(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("table-prestamo");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("prestamo", prestamoService.getAll());
        return mav;
    }


    @GetMapping("/form-prestamo")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("form-prestamo");
        mav.addObject("prestamo", new Prestamo());
        mav.addObject("cliente", clienteService.getAll());
        mav.addObject("libro", libroService.getAll());
        mav.addObject("action", "create");
        return mav;
    }

    @GetMapping("/form-prestamo/{id}")
    public ModelAndView getForm(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("form-prestamo");
        mav.addObject("prestamo", prestamoService.getById(id));
        mav.addObject("cliente", clienteService.getAll());
        mav.addObject("libro", libroService.getAll());
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Prestamo prestamo, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/prestamo");
        prestamoService.create(prestamo);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Prestamo prestamo, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/prestamo");
        prestamoService.update(prestamo);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        RedirectView redirect = new RedirectView("/prestamo");
        prestamoService.deleteById(id);
        return redirect;
    }
}