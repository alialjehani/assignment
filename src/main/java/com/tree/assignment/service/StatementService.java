package com.tree.assignment.service;

import com.tree.assignment.dto.RequestDto;
import com.tree.assignment.dto.StatementDto;
import com.tree.assignment.repository.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatementService {
    @Autowired
    private StatementRepository statementRepository;
    public List<StatementDto> getStatements(RequestDto requestDto) {
            // Log or inspect the principal to check roles
//            if (principal != null) {
//                // Check roles and username
//                System.out.println("Principal Name: " + principal.getName());
//                if (principal instanceof Authentication authentication) {
//                    System.out.println("User Authorities: " + authentication.getAuthorities());
//                }
//            }

            // Your statement retrieval logic here...
        List<StatementDto> list = new ArrayList<>();
            list.add(new StatementDto(1L,"Ali",null,100.00));
            return list;
        }
    }
