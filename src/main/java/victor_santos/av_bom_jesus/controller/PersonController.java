package victor_santos.av_bom_jesus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import victor_santos.av_bom_jesus.dto.request.PersonDTORequest;
import victor_santos.av_bom_jesus.dto.request.StudentDTORequest;
import victor_santos.av_bom_jesus.dto.response.PersonDTOResponse;
import victor_santos.av_bom_jesus.dto.response.ProfessorDTOResponse;
import victor_santos.av_bom_jesus.dto.response.StudentDTOResponse;
import victor_santos.av_bom_jesus.entity.Professor;
import victor_santos.av_bom_jesus.mapper.PersonMapper;
import victor_santos.av_bom_jesus.service.ImgBBService;
import victor_santos.av_bom_jesus.service.PersonService;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService service;
    @Autowired
    private PersonMapper mapper;
    @Autowired
    private ImgBBService imgBBService;

    @GetMapping
    public ResponseEntity<List<PersonDTOResponse>> getAll() {
        var data = service.getPersons();

        return ResponseEntity.ok(mapper.toResponseList(data, mapper::toDtoResponse));
    }

    @GetMapping("/student")
    public ResponseEntity<List<StudentDTOResponse>> getAllStudents() {
        var data = service.getStudents();

        return ResponseEntity.ok(mapper.toResponseList(data, mapper::toDtoResponse));
    }

    @GetMapping("/professor")
    public ResponseEntity<List<ProfessorDTOResponse>> getAllProfessors() {
        var data = service.getProfessors();

        return ResponseEntity.ok(mapper.toResponseList(data, mapper::toDtoResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTOResponse> getById (
        @PathVariable Long id
    ) {
        var data = service.getPersonById(id);

        return ResponseEntity.ok(mapper.toDtoResponse(data));
    }

    @PostMapping(value = "", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<PersonDTOResponse> addPerson(
        @RequestPart("person") String personJson,
        @RequestPart(value = "file", required = false) MultipartFile file
    ) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var dto = objectMapper.readValue(personJson, PersonDTORequest.class);

        if (dto instanceof StudentDTORequest student){
            if (file != null && !file.isEmpty()) {
                String url = imgBBService.uploadImage(file);
                student.setPhoto(url);
            }
        }

        var entity = mapper.toEntity(dto);
        var savedEntity = service.addPerson(entity);

        return ResponseEntity.ok(mapper.toDtoResponse(savedEntity));
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
