package br.cefetrj.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import br.cefetrj.model.Produto;
import br.cefetrj.repository.ProdutoRepository;

@Service
public class ProdutoService {
    protected ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto save(Produto entidade) {
        return repository.save(entidade);
    }

    public Produto update(Produto entidade) {
        return repository.save(entidade);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Produto> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }

}
