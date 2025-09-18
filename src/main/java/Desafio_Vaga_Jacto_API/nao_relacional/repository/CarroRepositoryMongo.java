package Desafio_Vaga_Jacto_API.nao_relacional.repository;

import Desafio_Vaga_Jacto_API.nao_relacional.model.CarroNaoRelacional;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("mongodb")
public interface CarroRepositoryMongo extends MongoRepository<CarroNaoRelacional, String> {
}