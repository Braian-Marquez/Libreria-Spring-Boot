package edu.egg.spring.service;

import edu.egg.spring.entity.Editorial;

import edu.egg.spring.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EditorialService {

    @Autowired
    EditorialRepository editorialRepository;

    @Transactional
    public void create(Editorial editorialDTO)  {


        Editorial editorial=new Editorial();
        editorial.setNombre(editorialDTO.getNombre());



        editorialRepository.save(editorial);
    }

    @Transactional
    public void update(Editorial editorialDTO) {
        Editorial editorial = editorialRepository.findById(editorialDTO.getId()).get();


        editorial.setNombre(editorialDTO.getNombre());


        editorialRepository.save(editorial);
    }

    @Transactional(readOnly = true)
    public List<Editorial> getAll() {

        return editorialRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Editorial getById(Integer id) {

        return editorialRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Integer id) { editorialRepository.deleteById(id);
    }
}
