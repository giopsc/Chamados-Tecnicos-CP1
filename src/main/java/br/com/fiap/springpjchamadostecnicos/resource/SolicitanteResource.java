package br.com.fiap.springpjchamadostecnicos.resource;

import br.com.fiap.springpjchamadostecnicos.entity.Endereco;
import br.com.fiap.springpjchamadostecnicos.entity.Solicitante;
import br.com.fiap.springpjchamadostecnicos.entity.Telefone;
import br.com.fiap.springpjchamadostecnicos.repository.SolicitanteRepository;
import br.com.fiap.springpjchamadostecnicos.repository.EnderecoRepository;
import br.com.fiap.springpjchamadostecnicos.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = "/solicitante")
public class SolicitanteResource {

    @Autowired
    private SolicitanteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @GetMapping
    public List<Solicitante> findAll() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Solicitante findById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @GetMapping(value = "/{id}/endereco")
    public Solicitante findSolicitante(@PathVariable Long id) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow();
        return endereco.getSolicitante();
    }
    @GetMapping(value = "/{id}/telefone")
    public Solicitante findTelefone(@PathVariable Long id) {
        Telefone telefone = telefoneRepository.findById(id).orElseThrow();
        return telefone.getSolicitante();
    }
    @Transactional
    @PostMapping(value = "/{id}/endereco")
    public Solicitante addEndereco(@PathVariable Long id, @RequestBody Endereco a) {

        Solicitante solicitante = repo.findById(id).orElseThrow();

        if (Objects.isNull(a)) return null;

        if (Objects.nonNull(a.getId())) {
            Endereco endereco = enderecoRepository.findById(a.getId()).orElseThrow();
            endereco.setSolicitante(solicitante);
            return solicitante;
        }

        return solicitante;
    }
    @Transactional
    @PostMapping(value = "/{id}/telefone")
    public Solicitante addTelefone(@PathVariable Long id, @RequestBody Telefone a) {

        Solicitante solicitante = repo.findById(id).orElseThrow();

        if (Objects.isNull(a)) return null;

        if (Objects.nonNull(a.getId())) {
            Telefone telefone = telefoneRepository.findById(a.getId()).orElseThrow();
            telefone.setSolicitante(solicitante);
            return solicitante;
        }

        return solicitante;
    }

    @Transactional
    @PostMapping
    public Solicitante salvarSolicitante(@RequestBody Solicitante solicitante) {
        return repo.save(solicitante);
    }

}