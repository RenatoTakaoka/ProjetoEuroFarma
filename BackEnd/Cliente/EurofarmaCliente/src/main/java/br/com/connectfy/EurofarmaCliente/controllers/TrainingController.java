package br.com.connectfy.EurofarmaCliente.controllers;

import br.com.connectfy.EurofarmaCliente.dtos.EmployeeDTO;
import br.com.connectfy.EurofarmaCliente.dtos.TrainingCreationDTO;
import br.com.connectfy.EurofarmaCliente.dtos.TrainingDTO;
import br.com.connectfy.EurofarmaCliente.dtos.TrainingHistoricDTO;
import br.com.connectfy.EurofarmaCliente.services.TrainingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eurofarma/training")
public class TrainingController {

    @Autowired
    private TrainingService trainningService;

    @PostMapping
    public ResponseEntity<String> insertTrainning(@RequestBody @Valid TrainingCreationDTO trainningCreationDTO) {
        System.out.println(trainningCreationDTO);
        System.out.println(trainningCreationDTO);System.out.println(trainningCreationDTO);System.out.println(trainningCreationDTO);System.out.println(trainningCreationDTO);System.out.println(trainningCreationDTO);




        return trainningService.create(trainningCreationDTO);
    }

    @GetMapping
    public List<TrainingHistoricDTO> findAllTrainning(){
        return trainningService.findAll();
    }

    @GetMapping("/{id}")
    public TrainingHistoricDTO findTrainningById(@PathVariable Long id) {
        return trainningService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTrainning(@PathVariable Long id, @RequestBody @Valid TrainingDTO trainningDTO) {
        return trainningService.update(id, trainningDTO);
    }

    @PutMapping("/addEmployee/{code}/{password}")
    public ResponseEntity<?> addEmployee(@PathVariable String code,@PathVariable String password, @RequestBody @Valid EmployeeDTO employeeDTO) {
        return trainningService.addEmployee(code,password, employeeDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTrainning(@PathVariable Long id) {
        trainningService.delete(id);
    }

}
