/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.service;

import com.projeto.model.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author MARCOS
 */
@Service
public class ItemJPA {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean inserirItem(Item item) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(item);
            transaction.commit();
            System.out.println("Dados inseridos do Item " + item.getNomeItem());
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao inserir dados do Item " + item.getNomeItem());
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarItem(Item item) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(item);
            transaction.commit();
            System.out.println("Dados atualizados do Item " + item.getNomeItem());
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao atualizar dados do Item " + item.getNomeItem());
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirItem(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Item item = entityManager.find(Item.class, id);
            if (item != null) {
                entityManager.remove(item);
                transaction.commit();
                System.out.println("Item excluído com sucesso: " + item.getNomeItem());
                return true;
            } else {
                System.out.println("Item não encontrado com o id: " + id);
                return false;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao excluir item com id: " + id);
            e.printStackTrace();
            return false;
        }
    }

    public List<Item> listarTodosItems() {
        try {
            return entityManager.createQuery("FROM Item", Item.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao listar todos os itens");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Item> listarTodosFiltro(String filtro) {
        try {
            String queryString = "FROM Item WHERE " + filtro;
            return entityManager.createQuery(queryString, Item.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao listar itens com filtro");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Item> listarTodosFiltro(String filtro, String resultado) {
        try {
            String queryString = "SELECT " + resultado + " FROM Item WHERE " + filtro;
            return entityManager.createQuery(queryString, Item.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao listar itens com filtro e resultado específico");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
