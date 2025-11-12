package br.cefetrj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import br.cefetrj.model.Pedido;
import br.cefetrj.service.PedidoService;
import br.cefetrj.to.input.PedidoToInput;
import br.cefetrj.to.output.PedidoToOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost")
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/pedidos", tags = { "Pedidos - PedidoController" })
public class PedidoController {
    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ApiOperation(value = "Salvar registro", notes = "Salva um novo registro no banco de dados")
    public ResponseEntity<PedidoToOutput> save(@RequestBody PedidoToInput input) {
        final Pedido pedido = input.build();

        final Pedido created = pedidoService.save(pedido);

        return new ResponseEntity<>(new PedidoToOutput(created), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Atualizar registro", notes = "Atualiza um registro existente no banco de dados")
    public ResponseEntity<PedidoToOutput> edit(@RequestBody PedidoToInput input) {

        final Pedido updated = pedidoService.update(input.build());

        return new ResponseEntity<>(new PedidoToOutput(updated), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Pesquisar por ID", notes = "Retorna o registro de acordo com o ID repassado")
    public ResponseEntity<PedidoToOutput> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(pedidoService.findById(id).map(PedidoToOutput::new).orElse(null));

    }

    @GetMapping
    @ApiOperation(value = "Listar todos", notes = "Retorna todos os registros")
    public ResponseEntity<List<PedidoToOutput>> findAll() {

        return ResponseEntity.ok(pedidoService.findAll().stream().map(PedidoToOutput::new).toList());

    }

    @DeleteExchange("/{id}")
    @ApiOperation(value = "Deletar por ID", notes = "Remove o registro de acordo com o ID repassado")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {

        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}