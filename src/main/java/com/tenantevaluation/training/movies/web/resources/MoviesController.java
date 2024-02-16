package com.tenantevaluation.training.movies.web.resources;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MoviesController {


    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test");
    }


/*
  @PostMapping("/save")
  @Operation(description = "Save a id")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "ID saved successfully."), @ApiResponse(responseCode = "400", description = "Exception controller"), @ApiResponse(responseCode = "500", description = "Internal server error encountered.")})
  //@Secured({AuthoritiesConstants.APPLICANT, AuthoritiesConstants.ADMIN})
  public ResponseEntity<ApplicationReqDTO> saveUnit(@Valid @RequestBody RegulaDTO reqDto) throws BadRequestAlertException, InvalidPendingApplicationException, InternalServerErrorException {
    ApplicationReqDTO respDto = _BASE_Service.saveIdDocument(reqDto);
    return ResponseEntity.ok(respDto);
  }
*/


}
