package victor_santos.av_bom_jesus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import victor_santos.av_bom_jesus.dto.request.PersonDTORequest;
import victor_santos.av_bom_jesus.dto.response.PersonDTOResponse;
import victor_santos.av_bom_jesus.entity.Person;
import victor_santos.av_bom_jesus.mapper.PersonMapper;
import victor_santos.av_bom_jesus.service.PersonService;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService service;
    private PersonMapper mapper;

    @GetMapping
    public ResponseEntity<List<PersonDTOResponse>> getAll() {
        var data = service.getPersons();

        return ResponseEntity.ok(mapper.toResponseList(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTOResponse> getById (
        @PathVariable Long id
    ) {
        var data = service.getPersonById(id);

        return ResponseEntity.ok(mapper.toDtoResponse(data));
    }

    @PostMapping
    public ResponseEntity<PersonDTOResponse> addPerson(
        @RequestBody PersonDTORequest body
    ) {
        var data = mapper.toEntity(body);
        var response = service.addPerson(data);

        return ResponseEntity.ok(mapper.toDtoResponse(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTOResponse> updatePerson(
        @PathVariable Long id,
        @RequestBody PersonDTORequest body
    ) {
        var data = mapper.toEntity(body);
        var response = service.updatePerson(id, data);

        return ResponseEntity.ok(mapper.toDtoResponse(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(
        @PathVariable Long id
    ) {
        service.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("soft-delete/{id}")
    public ResponseEntity<Void> softDeletePerson(
            @PathVariable Long id
    ) {
        service.softDeletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
