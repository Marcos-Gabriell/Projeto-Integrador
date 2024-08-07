/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/**
 *
 * @author MARCOS
 */
@Entity
@Table(name = "tbl_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_item", nullable = false)
    private String nomeItem;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "quantidade", nullable = false, columnDefinition = "DECIMAL(10,5)")
    private BigDecimal quantidade;

    @Column(name = "preco", nullable = false, columnDefinition = "DECIMAL(10,5)")
    private BigDecimal preco;

    @Column(name = "status", nullable = false)
    private String status;

    public Item() {

    }

    public Item(Integer id, String nomeItem, String categoria, BigDecimal quantidade, BigDecimal preco, String status) {
        this.id = id;
        this.nomeItem = nomeItem;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.preco = preco;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
