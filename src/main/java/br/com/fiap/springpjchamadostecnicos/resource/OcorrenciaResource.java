package br.com.fiap.springpjchamadostecnicos.resource;

import br.com.fiap.springpjchamadostecnicos.entity.Ocorrencia;
import br.com.fiap.springpjchamadostecnicos.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ocorrencia")
public class OcorrenciaResource {

    @Autowired
    private OcorrenciaRepository repo;

    @GetMapping
    public List<Ocorrencia> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Ocorrencia findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    @PostMapping
    public Ocorrencia salvarOcorrencia(@RequestBody Ocorrencia ocorrencia) {
        return repo.save(ocorrencia);
    }
}