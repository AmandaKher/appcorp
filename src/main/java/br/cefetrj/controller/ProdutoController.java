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

import br.cefetrj.model.Produto;
import br.cefetrj.service.ProdutoService;
import br.cefetrj.to.input.ProdutoToInput;
import br.cefetrj.to.output.ProdutoToOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost")
@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/produtos", tags = { "Produtos - ProdutoController" })
public class ProdutoController {
    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    @ApiOperation(value = "Salvar registro", notes = "Salva um novo registro no banco de dados")
    public ResponseEntity<ProdutoToOutput> save(@RequestBody ProdutoToInput input) {
        final Produto produto = input.build();

        final Produto created = produtoService.save(produto);

        return new ResponseEntity<>(new ProdutoToOutput(created), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Atualizar registro", notes = "Atualiza um registro existente no banco de dados")
    public ResponseEntity<ProdutoToOutput> edit(@RequestBody ProdutoToInput input) {

        final Produto updated = produtoService.update(input.build());

        return new ResponseEntity<>(new ProdutoToOutput(updated), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Pesquisar por ID", notes = "Retorna o registro de acordo com o ID repassado")
    public ResponseEntity<ProdutoToOutput> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(produtoService.findById(id).map(ProdutoToOutput::new).orElse(null));

    }

    @GetMapping
    @ApiOperation(value = "Listar todos", notes = "Retorna todos os registros")
    public ResponseEntity<List<ProdutoToOutput>> findAll() {

        return ResponseEntity.ok(produtoService.findAll().stream().map(ProdutoToOutput::new).toList());

    }

    @DeleteExchange("/{id}")
    @ApiOperation(value = "Deletar por ID", notes = "Remove o registro de acordo com o ID repassado")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {

        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
