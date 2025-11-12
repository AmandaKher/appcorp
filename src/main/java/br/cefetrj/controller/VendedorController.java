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

import br.cefetrj.model.Vendedor;
import br.cefetrj.service.VendedorService;
import br.cefetrj.to.input.VendedorToInput;
import br.cefetrj.to.output.VendedorToOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost")
@RequestMapping(value = "/vendedores", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/vendedores", tags = { "Vendedores - VendedorController" })
public class VendedorController {
    private final VendedorService vendedorService;

    @Autowired
    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @PostMapping
    @ApiOperation(value = "Salvar registro", notes = "Salva um novo registro no banco de dados")
    public ResponseEntity<VendedorToOutput> save(@RequestBody VendedorToInput input) {
        final Vendedor vendedor = input.build();

        final Vendedor created = vendedorService.save(vendedor);

        return new ResponseEntity<>(new VendedorToOutput(created), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Atualizar registro", notes = "Atualiza um registro existente no banco de dados")
    public ResponseEntity<VendedorToOutput> edit(@RequestBody VendedorToInput input) {

        final Vendedor updated = vendedorService.update(input.build());

        return new ResponseEntity<>(new VendedorToOutput(updated), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Pesquisar por ID", notes = "Retorna o registro de acordo com o ID repassado")
    public ResponseEntity<VendedorToOutput> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(vendedorService.findById(id).map(VendedorToOutput::new).orElse(null));

    }

    @GetMapping
    @ApiOperation(value = "Listar todos", notes = "Retorna todos os registros")
    public ResponseEntity<List<VendedorToOutput>> findAll() {

        return ResponseEntity.ok(vendedorService.findAll().stream().map(VendedorToOutput::new).toList());

    }

    @DeleteExchange("/{id}")
    @ApiOperation(value = "Deletar por ID", notes = "Remove o registro de acordo com o ID repassado")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {

        vendedorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
