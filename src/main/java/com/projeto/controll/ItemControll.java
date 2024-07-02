/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.controll;

import ch.qos.logback.core.model.Model;
import com.projeto.model.Item;
import com.projeto.service.ItemJPA;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author MARCOS
 */
@Controller
public class ItemControll {

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
}
