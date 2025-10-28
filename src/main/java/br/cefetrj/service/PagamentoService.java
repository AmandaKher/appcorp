package br.cefetrj.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import br.cefetrj.model.Pagamento;
import br.cefetrj.repository.PagamentoRepository;

@Service
public class PagamentoService {
    protected PagamentoRepository repository;

    public PagamentoService(PagamentoRepository repository) {
        this.repository = repository;
    }

    public Pagamento save(Pagamento entidade) {
        return repository.save(entidade);
    }

    public void update(Pagamento entidade) {
        repository.save(entidade);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Pagamento> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Pagamento> findAll() {
        return repository.findAll();
    }

}
