/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.projeto.model.Item;
import com.projeto.repository.ItemRepository;
import com.projeto.service.ItemService;

/**
 *
 * @author MARCOS
 */

@ExtendWith(MockitoExtension.class)
public class ItemTest {

	@InjectMocks
	private ItemService itemService;

	@Mock
	private ItemRepository itemRepository;

	private Item item;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		item = new Item();
		item.setId(1);
		item.setNomeItem("ItemTest");
		item.setCategoria("CategoriaTest");
		item.setQuantidade(new BigDecimal("10.00"));
		item.setPreco(new BigDecimal("100.00"));
		item.setStatus("Disponivel");
	}

	@Test
	void cadastroDeItemRetornaSucesso() {
		when(itemRepository.save(any(Item.class))).thenReturn(item);

		itemService.inserirItem(item);

		verify(itemRepository, times(1)).save(item);
	}

	@Test
	void cadastroDeItemRetornoErro() {
		doThrow(new RuntimeException("Erro ao salvar item")).when(itemRepository).save(any(Item.class));

		Exception exception = assertThrows(RuntimeException.class, () -> {
			itemService.inserirItem(item);
		});

		assertEquals("Erro ao salvar item", exception.getMessage());
	}

	@Test
	void cadastroDeItemDadosRepetidosSucesso() {
		when(itemRepository.save(any(Item.class))).thenReturn(item);

		itemService.inserirItem(item);

		verify(itemRepository, times(1)).save(item);
	}

	@Test
	void cadastroDeItemDadosRepetidosErro() {
		when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

		Exception exception = assertThrows(RuntimeException.class, () -> {
			itemService.inserirItem(item);
		});

		assertEquals("Item já existe com ID : " + item.getId(), exception.getMessage());
	}

	@Test
	void editarDadosItemSucesso() {
		when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));
		when(itemRepository.save(any(Item.class))).thenReturn(item);

		item.setNomeItem("Item Atualizado");
		itemService.atualizarItem(item);

		verify(itemRepository, times(1)).save(item);
	}

	@Test
	void editarDadosItemErro() {
		when(itemRepository.findById(anyInt())).thenReturn(Optional.empty());

		Exception exception = assertThrows(RuntimeException.class, () -> {
			itemService.atualizarItem(item);
		});

		assertEquals("Item inexistente para atualizar com ID : " + item.getId(), exception.getMessage());
	}

	@Test
	void excluirDadosItemSucesso() {
		when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));
		doNothing().when(itemRepository).deleteById(anyInt());

		itemService.excluirItem(item.getId());

		verify(itemRepository, times(1)).deleteById(item.getId());
	}

	@Test
	void excluirDadosItemErro() {
		when(itemRepository.findById(anyInt())).thenReturn(Optional.empty());

		Exception exception = assertThrows(RuntimeException.class, () -> {
			itemService.excluirItem(1);
		});

		assertEquals("Item ID : " + item.getId() + " nao encontrado", exception.getMessage());
	}

	@Test
	void listarDadosItemSucesso() {
		List<Item> items = Arrays.asList(item);
		when(itemRepository.findAll()).thenReturn(items);

		List<Item> found = itemService.listarTodosItems();

		assertFalse(found.isEmpty());
		assertEquals(1, found.size());
	}

	@Test
	void listarDadosItemErro() {
		when(itemRepository.findAll()).thenReturn(Collections.emptyList());

		List<Item> found = itemService.listarTodosItems();

		assertTrue(found.isEmpty());
	}
}