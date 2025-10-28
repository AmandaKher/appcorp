package br.cefetrj.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import br.cefetrj.model.Cliente;
import br.cefetrj.repository.ClienteRepository;

@Service
public class ClienteService {
    protected ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente save(Cliente entidade) {
        return repository.save(entidade);
    }

    public void update(Cliente entidade) {
        repository.save(entidade);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Cliente> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

}
