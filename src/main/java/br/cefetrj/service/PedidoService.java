package br.cefetrj.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import br.cefetrj.model.Pedido;
import br.cefetrj.repository.PedidoRepository;

@Service
public class PedidoService {
    protected PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido save(Pedido entidade) {
        return repository.save(entidade);
    }

    public void update(Pedido entidade) {
        repository.save(entidade);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Pedido> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Pedido> findAll() {
        return repository.findAll();
    }

}
