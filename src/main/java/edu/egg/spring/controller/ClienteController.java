package edu.egg.spring.controller;

import edu.egg.spring.entity.Autor;
import edu.egg.spring.entity.Cliente;
import edu.egg.spring.service.AutorService;
import edu.egg.spring.service.ClienteService;
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
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ModelAndView getCliente(HttpServletRequest request ) {
        ModelAndView mav = new ModelAndView("table-cliente");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("cliente", clienteService.getAll());
        return mav;
    }

    @GetMapping("/form-cliente")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("form-cliente");
        mav.addObject("cliente", new Cliente());
        mav.addObject("action", "create");
        return mav;
    }

    @GetMapping("/form-cliente/{id}")
    public ModelAndView getForm(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("form-cliente");
        mav.addObject("cliente", clienteService.getById(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Cliente cliente, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/cliente");
        clienteService.create(cliente);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Cliente cliente, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/cliente");
        clienteService.update(cliente);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        RedirectView redirect = new RedirectView("/cliente");
        clienteService.deleteById(id);
        return redirect;
    }
}
