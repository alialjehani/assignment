package com.tree.assignment.repository;

import com.tree.assignment.dto.RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class StatementRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StatementRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> fetchStatementsForUser(String accountId) {
        String sql = "SELECT * FROM statement WHERE accountId = ?";
        List<Map<String, Object>> statements = jdbcTemplate.queryForList(sql, accountId);
        return statements;
    }

    public List<Map<String, Object>> fetchStatementsForAdmin(RequestDto requestDto) {
        // Initialize a list to store the query parameters
        List<Object> params = new ArrayList<>();

        // Start building the SQL query
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM statement WHERE 1 = 1");

        // Check if the 'accountId' parameter is provided in the requestDto
        if (requestDto.getAccountId() != null) {
            // Add a condition to filter by 'accountId'
            sqlBuilder.append(" AND accountId = ?");
            params.add(requestDto.getAccountId()); // Add accountId parameter
        }

        // Check if the 'minAmount' parameter is provided in the requestDto
        if (requestDto.getMinAmount() != null) {
            // Add a condition to filter by 'minAmount'
            sqlBuilder.append(" AND amount >= ?");
            params.add(requestDto.getMinAmount());
        }

        // Check if the 'maxAmount' parameter is provided in the requestDto
        if (requestDto.getMaxAmount() != null) {
            // Add a condition to filter by 'maxAmount'
            sqlBuilder.append(" AND amount <= ?");
            params.add(requestDto.getMaxAmount());
        }

        // Convert the list of parameters to an array
        Object[] paramsArray = params.toArray();

        // Execute the query with parameters
        List<Map<String, Object>> statements = jdbcTemplate.queryForList(sqlBuilder.toString(), paramsArray);

        return statements;
    }

}




