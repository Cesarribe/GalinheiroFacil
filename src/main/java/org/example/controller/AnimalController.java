package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.model.Animal;
import org.example.service.AnimalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/racas")
@Tag(name = "Raça", description = "Endpoints para gerenciamento de raças de aves")
public class AnimalController {

    private final AnimalService service;

    public AnimalController(AnimalService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar ou atualizar raça", description = "Salva ou atualiza os dados de uma raça")
    public Animal salvar(@Valid @RequestBody Animal animal) {
        return service.salvar(animal);
    }

    @GetMapping
    @Operation(summary = "Listar todas as raças", description = "Retorna uma lista com todas as raças cadastradas")
    public List<Animal> listarTodas() {
        return service.listarTodasRacas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar raça por ID", description = "Retorna os dados de uma raça específica")
    public Animal buscarPorId(
            @Parameter(description = "ID da raça", example = "1") @PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar raça", description = "Remove uma raça do sistema")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
