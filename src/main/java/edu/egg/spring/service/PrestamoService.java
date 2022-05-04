package edu.egg.spring.service;


import edu.egg.spring.entity.Prestamo;
import edu.egg.spring.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Transactional
    public void create(Prestamo prestamoDTO) {


        Prestamo prestamo = new Prestamo();
        prestamo.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
        prestamo.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
        prestamo.setCliente(prestamoDTO.getCliente());
        prestamo.setLibro(prestamoDTO.getLibro());


        prestamoRepository.save(prestamo);
    }

    @Transactional
    public void update(Prestamo prestamoDTO) {
        Prestamo prestamo = prestamoRepository.findById(prestamoDTO.getId()).get();


        prestamo.setFechaPrestamo(prestamoDTO.getFechaPrestamo());
        prestamo.setFechaDevolucion(prestamoDTO.getFechaDevolucion());
        prestamo.setLibro(prestamoDTO.getLibro());
        prestamo.setCliente(prestamoDTO.getCliente());

        prestamoRepository.save(prestamo);
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
        prestamoRepository.deleteById(id);
    }
}
