package uy.com.minza.mutantes.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import uy.com.minza.mutantes.domain.DNA;

public interface DNARepository extends MongoRepository<DNA, String> {

}
