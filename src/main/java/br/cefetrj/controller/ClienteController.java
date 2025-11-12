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

import br.cefetrj.model.Cliente;
import br.cefetrj.service.ClienteService;
import br.cefetrj.to.input.ClienteToInput;
import br.cefetrj.to.output.ClienteToOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost")
@RequestMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/clientes", tags = { "Clientes - ClienteController" })
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ApiOperation(value = "Salvar registro", notes = "Salva um novo registro no banco de dados")
    public ResponseEntity<ClienteToOutput> save(@RequestBody ClienteToInput input) {
        final Cliente cliente = input.build();

        final Cliente created = clienteService.save(cliente);

        return new ResponseEntity<>(new ClienteToOutput(created), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Atualizar registro", notes = "Atualiza um registro existente no banco de dados")
    public ResponseEntity<ClienteToOutput> edit(@RequestBody ClienteToInput input) {

        final Cliente updated = clienteService.update(input.build());

        return new ResponseEntity<>(new ClienteToOutput(updated), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Pesquisar por ID", notes = "Retorna o registro de acordo com o ID repassado")
    public ResponseEntity<ClienteToOutput> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(clienteService.findById(id).map(ClienteToOutput::new).orElse(null));

    }

    @GetMapping
    @ApiOperation(value = "Listar todos", notes = "Retorna todos os registros")
    public ResponseEntity<List<ClienteToOutput>> findAll() {

        return ResponseEntity.ok(clienteService.findAll().stream().map(ClienteToOutput::new).toList());

    }

    @DeleteExchange("/{id}")
    @ApiOperation(value = "Deletar por ID", notes = "Remove o registro de acordo com o ID repassado")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {

        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}