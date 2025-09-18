package Desafio_Vaga_Jacto_API.nao_relacional.controller;

import Desafio_Vaga_Jacto_API.nao_relacional.model.CarroNaoRelacional;
import Desafio_Vaga_Jacto_API.nao_relacional.repository.CarroRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros") // Mesmo endpoint, sem problemas!
@Profile("mongodb") // SÓ ATIVA ESTE CONTROLLER NO PERFIL 'mongodb'
public class CarroControllerMongo {

    private final CarroRepositoryMongo repository;

    @Autowired
    public CarroControllerMongo(CarroRepositoryMongo repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CarroNaoRelacional> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroNaoRelacional> buscarPorId(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CarroNaoRelacional salvar(@RequestBody CarroNaoRelacional carro) {
        carro.setId(null);
        return repository.save(carro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarroNaoRelacional> atualizar(@PathVariable String id, @RequestBody CarroNaoRelacional carro) {
        return repository.findById(id)
                .map(carroExistente -> {
                    carro.setId(carroExistente.getId()); // Preserva o ID original
                    CarroNaoRelacional carroAtualizado = repository.save(carro);
                    return ResponseEntity.ok(carroAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o carro não existe
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content (sucesso sem corpo)
    }
}