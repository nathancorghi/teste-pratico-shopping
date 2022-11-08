package io.github.nathancorghi.mysql.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import io.github.nathancorghi.mysql.conexao.ConexaoMySql;
import io.github.nathancorghi.mysql.entity.Massas;
import io.github.nathancorghi.mysql.query.MassasQuery;

public class MassasRepository {

    private final ConexaoMySql conexaoMySql = new ConexaoMySql();
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(conexaoMySql.getDataSource());

    public Massas returnProducts() {

        return jdbcTemplate
                .queryForObject(
                        MassasQuery.findByProductName(),
                        new BeanPropertyRowMapper<>(Massas.class)
                );
    }

    public int updateProductColor(String produto, String cor) {

        return jdbcTemplate
                .update(
                        MassasQuery.updateProductColor(),
                        cor, produto);
    }
}
