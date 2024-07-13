package com.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projeto.model.Item;

/**
 *
 * @author MARCOS
 */

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	 @Query("SELECT i FROM Item i WHERE " +
	           "(:campo = 'nomeItem' AND i.nomeItem LIKE %:valor%) OR " +
	           "(:campo = 'categoria' AND i.categoria LIKE %:valor%) OR " +
	           "(:campo = 'quantidade' AND str(i.quantidade) LIKE %:valor%) OR " +
	           "(:campo = 'preco' AND str(i.preco) LIKE %:valor%) OR " +
	           "(:campo = 'status' AND i.status LIKE %:valor%)")
	    List<Item> listarTodosFiltro(@Param("campo") String campo, @Param("valor") String valor);
}
