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

@Tag(name = "Expenses Controller")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(basePath + expenses)
public class ExpenseController {

    private final ExpenseService expenseService;


    @PostMapping(createRequest)
    @Operation(summary = "Employee access only : can create expense request")
    public ResponseEntity<String> createExpenseRequest(@RequestBody ExpenseCreationDto expenseCreationDto){
        log.debug("Creating expense request");
        if (expenseService.createExpenseRequest(expenseCreationDto)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Request created successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request not created");
    }

    @GetMapping(viewExpenseRequestById)
    @Operation(summary = "Employee access only : can create expense request")
    public ResponseEntity<ExpenseDto> viewExpenseRequest(@PathVariable("id") UUID expenseId){
        ExpenseDto expenseDto = expenseService.viewExpenseById(expenseId);
        return ResponseEntity.ok(expenseDto);
    }

    @GetMapping(viewAllExpenses)
    @Operation(summary = "Manager access only : can view all expenses")
    public ResponseEntity<List<ExpenseDto>> viewAllExpenses(){
        return ResponseEntity.ok(expenseService.listAll());
    }

    @GetMapping(viewAllExpensesByEmployeeId)
    @Operation(summary = "Both roles access : can view all expenses", description = "The manager can see all expenses. The employee can see only his expenses.")
    public ResponseEntity<List<ExpenseDto>> viewAllExpensesByEmployeeId(@PathVariable("id")UUID id){
        return ResponseEntity.ok(expenseService.listAllByUserId(id));
    }

    @GetMapping(viewAllExpensesByEmployeeNameOrLastName)
    @Operation(summary = "Manager only access : view all expenses searched with a string", description = "This query uses ilike so whatever you type will return a result.")
    public ResponseEntity<List<ExpenseDto>> viewAllExpensesByFirstAndLastName(@RequestParam String text){
        return ResponseEntity.ok(expenseService.findAllByFirstNameOrLastName(text));
    }


    @GetMapping(updateStatus)
    @Operation(summary = "Manager only access : approve or decline request")
    public ResponseEntity<Void> updateApprovalStatus(@PathVariable("id") UUID expenseId,
                                                     @RequestParam boolean status){
        expenseService.updateApprovalStatus(expenseId, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
