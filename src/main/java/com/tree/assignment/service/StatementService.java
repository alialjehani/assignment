package com.tree.assignment.service;

import com.tree.assignment.dto.RequestDto;
import com.tree.assignment.dto.StatementDto;
import com.tree.assignment.exception.BusinessValidationException;
import com.tree.assignment.repository.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatementService {
    @Autowired
    private StatementRepository statementRepository;

    public List<StatementDto> getStatements(RequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (isAdmin(authentication)) {
            return fetchStatementsForAdmin(requestDto);
        }
        if (isUser(authentication)) {
                return fetchStatementsForUser(requestDto);
        }
        return null;
    }

    private List<StatementDto> fetchStatementsForAdmin(RequestDto requestDto) {
        if (requestDto.getAccountId() == null) {
            throw new BusinessValidationException("accountId cannot be null");
        }

        List<Map<String, Object>> statements = statementRepository.fetchStatementsForAdmin(requestDto);

        return statements.stream()
                .filter(statement -> {
                    String dateText = (String) statement.get("datefield");
                    LocalDate statementDate = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    if (requestDto.getFromDate() != null && requestDto.getToDate() != null) {
                        LocalDate fromDate = LocalDate.parse(requestDto.getFromDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                        LocalDate toDate = LocalDate.parse(requestDto.getToDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                        return statementDate.isAfter(fromDate) && !statementDate.isAfter(toDate);
                    } else if (requestDto.getFromDate() != null) {
                        LocalDate fromDate = LocalDate.parse(requestDto.getFromDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                        return statementDate.isEqual(fromDate) || statementDate.isAfter(fromDate);
                    } else if (requestDto.getToDate() != null) {
                        LocalDate toDate = LocalDate.parse(requestDto.getToDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                        return statementDate.isEqual(toDate) || !statementDate.isAfter(toDate);
                    }
                    return true; // Return all statements if no date filtering criteria are provided
                })
            .map(statement -> {
                StatementDto statementDto = new StatementDto();
                statementDto.setId((Integer) statement.get("id"));
                statementDto.setAccountId((Double) statement.get("accountId"));
                statementDto.setDatefield((String)statement.get("datefield"));
                // Parse the "amount" string into a BigDecimal
                String amount = (String) statement.get("amount");
                statementDto.setAmount(amount);
                return statementDto;
            })
            .collect(Collectors.toList());
    }


    private List<StatementDto> fetchStatementsForUser(RequestDto requestDto) {
        if (requestDto.getAccountId() == null) {
            throw new BusinessValidationException("accountId cannot be null");
        }
        if(requestDto.getToDate()!= null||
                requestDto.getFromDate()!= null||
                requestDto.getMaxAmount()!= null||
                requestDto.getMinAmount()!= null){
            throw new BusinessValidationException("Can't apply these parameters with your current permissions!");
        }
         List<Map<String,Object>> statements = statementRepository.fetchStatementsForUser(requestDto.getAccountId());
        // Filter statements that match the date criteria
        List<Map<String, Object>> filteredStatements = statements.stream()
                .filter(statement -> {
                    String dateText = (String) statement.get("datefield");
                    LocalDate statementDate = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
                    return statementDate.isAfter(threeMonthsAgo) || statementDate.isEqual(threeMonthsAgo);
                })
                .toList();

        List<StatementDto> statementsResponse = new ArrayList<>();
        for (Map<String, Object> statement : filteredStatements) {
            StatementDto statementDto = new StatementDto();
            statementDto.setId((Integer) statement.get("id"));
            statementDto.setAccountId((Double) statement.get("accountId"));
            statementDto.setDatefield((String) statement.get("datefield"));
            // Parse the "amount" string into a BigDecimal
            String amountText = (String) statement.get("amount");
            BigDecimal amount = new BigDecimal(amountText);

            statementDto.setAmount(String.valueOf(amount));
            statementsResponse.add(statementDto);
        }
        return statementsResponse;
    }

    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> "ROLE_ADMIN".equals(grantedAuthority.getAuthority()));
    }

    private boolean isUser(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> "ROLE_USER".equals(grantedAuthority.getAuthority()));
    }
}