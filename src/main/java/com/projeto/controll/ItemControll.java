/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.controll;

<<<<<<< HEAD
import ch.qos.logback.core.model.Model;
import com.projeto.model.Item;
import com.projeto.service.ItemJPA;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
=======
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.model.Item;
import com.projeto.service.ItemService;
>>>>>>> 003551b (funcionalidades de cadastro, edição, exclusão, pesquisa geral e pesquisa com filtros)

/**
 *
 * @author MARCOS
 */
@Controller
public class ItemControll {

<<<<<<< HEAD
    private final ItemJPA itemJPA;

    @Autowired
    public ItemControll(ItemJPA itemJPA) {
        this.itemJPA = itemJPA;
    }

    @GetMapping("/")
    public String telaPrincipalHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
=======
	@Autowired
	private ItemService itemService;

	@GetMapping("/")
	public String telaPrincipalHome() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home(Model model) {
		List<Item> listaItemsEstoque = itemService.listarTodosItems();
		model.addAttribute("listaItemsEstoque", listaItemsEstoque);
		return "home";
	}

	@GetMapping("/filtrar")
    @ResponseBody
    public List<Item> filtrarItems(@RequestParam("campo") String campo, @RequestParam("valor") String valor) {
        return itemService.filtrarItems(campo, valor);
    }

	@PostMapping("/novo-registro")
	public String salvarNovoRegistro(Item item, Model model) {
		itemService.inserirItem(item);
		return "redirect:/home";
	}

	@GetMapping("/editar-item/{id}")
	public String editarItemForm(@PathVariable Integer id, Model model) {
		Optional<Item> item = itemService.buscarItemPorId(id);
		if (item.isPresent()) {
			model.addAttribute("item", item.get());
		}
		return "formEditarRegistro";
	}

	@PostMapping("/editar-item")
	public String editarItem(@ModelAttribute Item item) {
		itemService.atualizarItem(item);
		return "redirect:/home";
	}

	@PostMapping("/excluir-item/{id}")
	public String excluirItem(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		try {
			itemService.excluirItem(id);
			redirectAttributes.addFlashAttribute("successMessage", "Item excluído com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Erro ao excluir item.");
		}
		return "redirect:/home";
	}

>>>>>>> 003551b (funcionalidades de cadastro, edição, exclusão, pesquisa geral e pesquisa com filtros)
}
