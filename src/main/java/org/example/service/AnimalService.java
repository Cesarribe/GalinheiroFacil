package org.example.service;

import org.example.model.Animal;
import org.example.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public List<Animal> listarTodasRacas() {
        return repository.findAll();
    }

    public Animal buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raça não encontrada"));
    }

    public Animal salvar(Animal animal) {
        return repository.save(animal);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
