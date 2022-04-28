package edu.egg.spring.service;

import edu.egg.spring.entity.Autor;

import edu.egg.spring.repository.AutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public void create(Autor autorDTO)  {


        Autor autor=new Autor();
        autor.setNombre(autorDTO.getNombre());


        autorRepository.save(autor);
    }

    @Transactional
    public void update(Autor autorDTO) {
        Autor autor = autorRepository.findById(autorDTO.getId()).get();


        autor.setNombre(autorDTO.getNombre());


        autorRepository.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> getAll() {

        return autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Autor getById(Integer id) {

        return autorRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Integer id) { autorRepository.deleteById(id);
    }
}

