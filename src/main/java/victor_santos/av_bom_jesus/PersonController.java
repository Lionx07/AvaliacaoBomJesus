package victor_santos.av_bom_jesus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        List<Person> data = service.getPersons();

        return ResponseEntity.ok(mapper.toResponseList(data));
    }

}
