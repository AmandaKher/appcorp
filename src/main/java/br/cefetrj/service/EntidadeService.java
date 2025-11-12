package br.cefetrj.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import br.cefetrj.model.Entidade;
import br.cefetrj.repository.EntidadeRepository;

@Service
public class EntidadeService {
    protected EntidadeRepository repository;

    public EntidadeService(EntidadeRepository repository) {
        this.repository = repository;
    }

    public Entidade save(Entidade entidade) {
        return repository.save(entidade);
    }

    public Entidade update(Entidade entidade) {
        return repository.save(entidade);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Entidade> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Entidade> findAll() {
        return repository.findAll();
    }

}
