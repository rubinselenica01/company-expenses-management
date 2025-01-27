package com.company.expenses_management.controller;

import com.company.expenses_management.model.dto.ExpenseCreationDto;
import com.company.expenses_management.model.dto.ExpenseDto;
import com.company.expenses_management.service.ExpenseService;
import com.company.expenses_management.utils.PathConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.company.expenses_management.utils.PathConstants.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(basePath + expenses)
public class ExpenseController {

    private final ExpenseService expenseService;


    @PostMapping(createRequest)
    public ResponseEntity<String> createExpenseRequest(@RequestBody ExpenseCreationDto expenseCreationDto){
        log.debug("Creating expense request");
        if (expenseService.createExpenseRequest(expenseCreationDto)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Request created successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request not created");
    }

    @GetMapping(viewExpenseRequestById)
    public ResponseEntity<ExpenseDto> viewExpenseRequest(@PathVariable("id") UUID expenseId){
        ExpenseDto expenseDto = expenseService.viewExpenseById(expenseId);
        return ResponseEntity.ok(expenseDto);
    }

    @GetMapping(viewAllExpenses)
    public ResponseEntity<List<ExpenseDto>> viewAllExpenses(){
        return ResponseEntity.ok(expenseService.listAll());
    }


    @GetMapping(viewAllExpensesByEmployeeNameOrLastName)
    public ResponseEntity<List<ExpenseDto>> viewAllExpensesByFirstAndLastName(@RequestParam String text){
        return ResponseEntity.ok(expenseService.findAllByFirstNameOrLastName(text));
    }

    //krijo metoden per aprovimin apo jo te expenses

}
