package br.com.fiap.springpjchamadostecnicos.resource;

import br.com.fiap.springpjchamadostecnicos.entity.Especialidade;
import br.com.fiap.springpjchamadostecnicos.entity.Tecnico;
import br.com.fiap.springpjchamadostecnicos.repository.EspecialidadeRepository;
import br.com.fiap.springpjchamadostecnicos.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/tecnico")
public class TecnicoResource {

    @Autowired
    private TecnicoRepository repo;
    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @GetMapping
    public List<Tecnico> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Tecnico findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    @PostMapping
    public Tecnico salvarTecnico(@RequestBody Tecnico tecnico) {
        return repo.save(tecnico);
    }

    @Transactional
    @PostMapping(value = "/{id}/especialidade")
    public Tecnico addEspecialidade(@PathVariable Long id, @RequestBody Especialidade a) {

        Tecnico tecnico = repo.findById(a.getId()).orElseThrow();

        if (Objects.isNull(a)) return null;

        if (Objects.nonNull(a.getId())) {
            Especialidade especialidade = especialidadeRepository.findById(id).orElseThrow();
            tecnico.getEspecialidades().add(especialidade);
            return tecnico;
        }
            tecnico.getEspecialidades().add(a);
            return tecnico;
    }

}