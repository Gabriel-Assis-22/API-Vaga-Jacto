package Desafio_Vaga_Jacto_API.demo.relacional.controller;

import Desafio_Vaga_Jacto_API.demo.relacional.model.Carro;
import Desafio_Vaga_Jacto_API.demo.relacional.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @GetMapping
    public List<Carro> listar() {
        return carroRepository.listar();
    }

    @GetMapping("/{id}")
    public Carro buscarPorId(@PathVariable Long id) {
        return carroRepository.buscarPorId(id);
    }

    @PostMapping
    public Carro salvar(@RequestBody Carro carro) {
        return carroRepository.salvar(carro);
    }

    @PutMapping("/{id}")
    public Carro atualizar(@PathVariable Long id, @RequestBody Carro carro) {
        return carroRepository.atualizar(id, carro);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        carroRepository.deletar(id);
    }
}