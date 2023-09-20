package com.tree.assignment.service;

import com.tree.assignment.dto.RequestDto;
import com.tree.assignment.dto.StatementDto;
import com.tree.assignment.repository.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatementService {

    @Autowired
    private StatementRepository statementRepository;

    public List<StatementDto> getStatements(RequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isAdmin(authentication)) {
            // 'admin' can perform all requests with date and amount range
            // Implement your logic to fetch statements based on parameters for admin
            return fetchStatementsForAdmin(requestDto);
        } else if (isUser(authentication)) {
            // 'user' can only request statements without parameters (three months back)
            if (requestDto == null) {
                return fetchStatementsForUser(requestDto);
            } else {
                // 'user' trying to specify parameters should result in HTTP unauthorized (401)
                throw new UnauthorizedAccessException("Unauthorized access for specified parameters");
            }
        } else {
            throw new UnauthorizedAccessException("Unauthorized access");
        }
    }

    private List<StatementDto> fetchStatementsForAdmin(RequestDto requestDto) {
        // Implement logic to fetch statements based on parameters for admin
    }

    private List<StatementDto> fetchStatementsForUser(RequestDto requestDto) {
        // Implement  logic to fetch statements for 'user' without parameters
    }

    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> "admin".equals(grantedAuthority.getAuthority()));
    }

    private boolean isUser(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> "user".equals(grantedAuthority.getAuthority()));
    }
}