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

import br.cefetrj.model.Pagamento;
import br.cefetrj.service.PagamentoService;
import br.cefetrj.to.input.PagamentoToInput;
import br.cefetrj.to.output.PagamentoToOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost")
@RequestMapping(value = "/pagamentos", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/pagamentos", tags = { "Pagamentos - PagamentoController" })
public class PagamentoController {
    private final PagamentoService pagamentoService;

    @Autowired
    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    @ApiOperation(value = "Salvar registro", notes = "Salva um novo registro no banco de dados")
    public ResponseEntity<PagamentoToOutput> save(@RequestBody PagamentoToInput input) {
        final Pagamento pagamento = input.build();

        final Pagamento created = pagamentoService.save(pagamento);

        return new ResponseEntity<>(new PagamentoToOutput(created), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Atualizar registro", notes = "Atualiza um registro existente no banco de dados")
    public ResponseEntity<PagamentoToOutput> edit(@RequestBody PagamentoToInput input) {

        final Pagamento updated = pagamentoService.update(input.build());

        return new ResponseEntity<>(new PagamentoToOutput(updated), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Pesquisar por ID", notes = "Retorna o registro de acordo com o ID repassado")
    public ResponseEntity<PagamentoToOutput> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(pagamentoService.findById(id).map(PagamentoToOutput::new).orElse(null));

    }

    @GetMapping
    @ApiOperation(value = "Listar todos", notes = "Retorna todos os registros")
    public ResponseEntity<List<PagamentoToOutput>> findAll() {

        return ResponseEntity.ok(pagamentoService.findAll().stream().map(PagamentoToOutput::new).toList());

    }

    @DeleteExchange("/{id}")
    @ApiOperation(value = "Deletar por ID", notes = "Remove o registro de acordo com o ID repassado")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {

        pagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}