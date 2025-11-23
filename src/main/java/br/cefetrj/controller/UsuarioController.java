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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.cefetrj.model.Usuario;
import br.cefetrj.service.UsuarioService;
import br.cefetrj.to.output.UsuarioToOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost")
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/usuarios", tags = { "Usuarios - UsuarioController" })
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @ApiOperation(value = "Salvar registro", notes = "Salva um novo registro no banco de dados")
    public ResponseEntity<Usuario> save(@RequestBody Usuario input) {
        final Usuario usuario = input;

        final Usuario created = usuarioService.save(usuario);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Pesquisar por ID", notes = "Retorna o registro de acordo com o ID repassado")
    public ResponseEntity<Usuario> findById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(usuarioService.findById(id).orElse(null));

    }

    @GetMapping
    @ApiOperation(value = "Listar todos", notes = "Retorna todos os registros")
    public ResponseEntity<List<Usuario>> findAll() {

        return ResponseEntity.ok(usuarioService.findAll());

    }

    @GetMapping(value = "/pegaPorEmail")
    public ResponseEntity<UsuarioToOutput> findByEmail(
            @RequestParam(required = false) String email) {

        return ResponseEntity.ok(
                usuarioService.findByEmail(email)
                        .map(UsuarioToOutput::new)
                        .orElse(null));
    }
}
