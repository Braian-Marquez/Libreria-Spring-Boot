package edu.egg.spring.service;


import edu.egg.spring.entity.Cliente;
import edu.egg.spring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public void create(Cliente clienteDTO) {


        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setDocumento(clienteDTO.getDocumento());
        cliente.setTelefono(clienteDTO.getTelefono());



        clienteRepository.save(cliente);
    }

    @Transactional
    public void update(Cliente clienteDTO) {
        Cliente cliente = clienteRepository.findById(clienteDTO.getId()).get();


        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setDocumento(clienteDTO.getDocumento());
        cliente.setTelefono(clienteDTO.getTelefono());


        clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> getAll() {

        return clienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cliente getById(Integer id) {

        return clienteRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Integer id) {
        clienteRepository.deleteById(id);
    }
}
