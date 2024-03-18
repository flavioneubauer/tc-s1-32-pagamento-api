package br.com.fiap.soat1.t32.repositories;

import br.com.fiap.soat1.t32.models.entities.PagamentoDb;
import org.springframework.data.repository.CrudRepository;

public interface PagamentoCrudRepository extends CrudRepository<PagamentoDb, Long> {


    PagamentoDb findByIdPedido(Long idPedido);
}
