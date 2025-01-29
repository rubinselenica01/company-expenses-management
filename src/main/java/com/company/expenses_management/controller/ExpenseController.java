package com.company.expenses_management.controller;

import com.company.expenses_management.model.dto.ExpenseCreationDto;
import com.company.expenses_management.model.dto.ExpenseDto;
import com.company.expenses_management.security.SecurityUtils;
import com.company.expenses_management.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(viewAllExpensesByEmployeeId)
    public ResponseEntity<List<ExpenseDto>> viewAllExpensesByEmployeeId(@PathVariable("id")UUID id){
        return ResponseEntity.ok(expenseService.listAllByUserId(id));
    }

    @PostMapping(updateStatus)
    public ResponseEntity<Void> updateApprovalStatus(@PathVariable("id") UUID expenseId,
                                                     @RequestParam String status){
        expenseService.updateApprovalStatus(expenseId, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
