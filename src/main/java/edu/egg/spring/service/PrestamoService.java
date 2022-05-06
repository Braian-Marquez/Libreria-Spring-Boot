package edu.egg.spring.service;


import edu.egg.spring.entity.Libro;
import edu.egg.spring.entity.Prestamo;
import edu.egg.spring.repository.LibroRepository;
import edu.egg.spring.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;
    @Autowired
    protected LibroRepository libroRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Transactional
    public void create(Prestamo prestamoDTO) {


        Prestamo prestamo = new Prestamo();
        Libro libro = libroRepository.findById(prestamoDTO.getLibro().getId()).get();
        prestamo.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
        prestamo.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
        prestamo.setCliente(prestamoDTO.getCliente());
        prestamo.setLibro(prestamoDTO.getLibro());
        prestamo.setAPrestar(prestamoDTO.getAPrestar());
        libro.setEjemplaresRestantes(libro.getEjemplares() - prestamoDTO.getAPrestar());

        libroRepository.save(libro);
        prestamoRepository.save(prestamo);

    }

    @Transactional
    public void update(Prestamo prestamoDTO) {


    }

    @Transactional(readOnly = true)
    public List<Prestamo> getAll() {

        return prestamoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Prestamo getById(Integer id) {

        return prestamoRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Integer id) {

        Libro libro = libroRepository.findById(prestamoRepository.findById(id).get().getLibro().getId()).get();
        libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() + prestamoRepository.findById(id).get().getAPrestar());
        libroRepository.save(libro);
        prestamoRepository.deleteById(id);

    }
}
