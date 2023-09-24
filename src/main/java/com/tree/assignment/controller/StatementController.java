package com.tree.assignment.controller;

import com.tree.assignment.dto.RequestDto;
import com.tree.assignment.dto.StatementDto;
import com.tree.assignment.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StatementController {
    @Autowired
    private StatementService statementService;
    @PostMapping("/statements")
    public List<StatementDto> getStatements(@RequestBody RequestDto requestDto) {
        // Validate parameters here, return ResponseEntity with HTTP 400 if invalid.
        // Hash the account number before sending the response.
        // Create a DTO to control what is exposed in the response.
        // Example: StatementDTO statementDTO = mapToStatementDTO(statements);
        return statementService.getStatements(requestDto);

    }
}
