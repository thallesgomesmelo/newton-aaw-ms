package com.newton.aaw.rh.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/v1/employees")
public interface EmployeesResource {
	
	@Operation(summary = "Recupera um Employee a partir de um ID")
	@ApiResponses(value = {
			@ApiResponse(
					description = "Employee do ID retornado com sucesso", 
					responseCode = "200", 
					content = {@Content(
							mediaType = "application/json",
							schema = @Schema(implementation = EmployeeDto.class))
					}),
			@ApiResponse(
					description = "Employee com ID não encontrado", 
					responseCode = "404", 
					content = {@Content(
							mediaType = "application/json",
							schema = @Schema(implementation = ErrorResponseDto.class))
					})
	})
	@GetMapping("/{id}")
	EmployeeDto getById(String id);
	

	@Operation(summary = "Recupera todos os Employees")
	@ApiResponses(value = {
			@ApiResponse(
					description = "Employees retornado com sucesso", 
					responseCode = "200", 
					content = {@Content(
							mediaType = "application/json",
							array = @ArraySchema(
									schema = @Schema(implementation = EmployeeDto.class)))
					})
	})
	@GetMapping
	List<EmployeeDto> getAll();
	
	
	@Operation(summary = "Permite criar um novo Employee")
	@ApiResponses(value = {
			@ApiResponse(
					description = "Um Employee criado com sucesso", 
					responseCode = "201", 
					content = {@Content(
							mediaType = "application/json",
							schema = @Schema(implementation = EmployeeDto.class))
					}),
			@ApiResponse(
					description = "Employee não pode ser criado", 
					responseCode = "400", 
					content = {@Content(
							mediaType = "application/json",
							schema = @Schema(implementation = ErrorResponseDto.class))
					})
	})
	@PostMapping
	ResponseEntity<EmployeeDto> create(EmployeeDto em);
	

	@Operation(summary = "Atualiza um Employee existente a partir de um ID")
	@ApiResponses(value = {
			@ApiResponse(
					description = "Employee do ID foi atualizado com sucesso", 
					responseCode = "200", 
					content = {@Content(
							mediaType = "application/json",
							schema = @Schema(implementation = EmployeeDto.class))
					}),
			@ApiResponse(
					description = "Employee com ID não encontrado", 
					responseCode = "404", 
					content = {@Content(
							mediaType = "application/json",
							schema = @Schema(implementation = ErrorResponseDto.class))
					}),
			@ApiResponse(
					description = "Parâmetros inválidos", 
					responseCode = "400", 
					content = {@Content(
							mediaType = "application/json",
							schema = @Schema(implementation = ErrorResponseDto.class))
					})
	})
	@PutMapping("/{id}")
	EmployeeDto update(String id, EmployeeDto em);
	
	
	@Operation(summary = "Excluir um Employee a partir de um ID")
	@ApiResponses(value = {
			@ApiResponse(
					description = "Employee do ID foi excluido com sucesso", 
					responseCode = "204", 
					content = {@Content(
							mediaType = "application/json",
							schema = @Schema(implementation = EmployeeDto.class))
					}),
			@ApiResponse(
					description = "Employee com ID não encontrado", 
					responseCode = "404", 
					content = {@Content(
							mediaType = "application/json",
							schema = @Schema(implementation = ErrorResponseDto.class))
					})
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> delete(String id);

}