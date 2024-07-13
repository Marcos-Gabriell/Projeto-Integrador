/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.service;

import com.projeto.model.Item;
import com.projeto.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MARCOS
 */
@Service
public class ItemService {

	@Autowired
	private ItemRepository repo;

	public void inserirItem(Item item) {
		if (buscarItemPorId(item.getId()).isPresent()) {
			throw new RuntimeException("Item já existe com ID : " + item.getId());
		}
		repo.save(item);
	}

	public void atualizarItem(Item item) {
		if (buscarItemPorId(item.getId()).isPresent()) {
			repo.save(item);
		} else {
			throw new RuntimeException("Item inexistente para atualizar com ID : " + item.getId());
		}
	}

	public void excluirItem(Integer id) {
		if (!buscarItemPorId(id).isPresent()) {
			throw new RuntimeException("Item ID : " + id + " nao encontrado");
		}
		repo.deleteById(id);
	}

	public List<Item> listarTodosItems() {
		return repo.findAll();
	}

	public List<Item> filtrarItems(String campo, String valor) {
		return repo.listarTodosFiltro(campo, valor);
	}

	public Optional<Item> buscarItemPorId(Integer id) {
		return repo.findById(id);
	}
}
