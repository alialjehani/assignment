package com.tree.assignment.controller;

import com.tree.assignment.dto.RequestDto;
import com.tree.assignment.dto.StatementDto;
import com.tree.assignment.entity.Statement;
import com.tree.assignment.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/statements")
public class StatementController {
    @Autowired
    private StatementService statementService;
    @GetMapping
    public List<StatementDto> getStatements(RequestDto requestDto) {
        // Validate parameters here, return ResponseEntity with HTTP 400 if invalid.
        // Hash the account number before sending the response.
        // Create a DTO to control what is exposed in the response.
        // Example: StatementDTO statementDTO = mapToStatementDTO(statements);
        return statementService.getStatements(requestDto);

    }
}
