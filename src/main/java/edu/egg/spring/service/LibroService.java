package edu.egg.spring.service;


import edu.egg.spring.entity.Libro;
import edu.egg.spring.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class LibroService {


    @Autowired
    LibroRepository libroRepository;

    @Transactional
    public void create(Libro libroDTO)  {


        Libro libro=new Libro();
        libro.setIsbn(libroDTO.getIsbn());
        libro.setNombre(libroDTO.getNombre());
        libro.setAutor(libroDTO.getAutor());
        libro.setAnio(libroDTO.getAnio());
        libro.setEditorial(libroDTO.getEditorial());
        libro.setEjemplares(libroDTO.getEjemplares());
        libro.setEjemplaresPrestados(libroDTO.getEjemplaresPrestados());


        libroRepository.save(libro);
    }

    @Transactional
    public void update(Libro libroDTO) {
        Libro libro = libroRepository.findById(libroDTO.getId()).get();


        libro.setNombre(libroDTO.getNombre());
        libro.setAutor(libroDTO.getAutor());
        libro.setAnio(libroDTO.getAnio());
        libro.setEditorial(libroDTO.getEditorial());
        libro.setEjemplares(libroDTO.getEjemplares());
        libro.setEjemplaresPrestados(libroDTO.getEjemplaresPrestados());

        libroRepository.save(libro);
    }

    @Transactional(readOnly = true)
    public List<Libro> getAll() {

        return libroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Libro getById(Integer id) {

        return libroRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Integer id) {
        libroRepository.deleteById(id);
    }
}


