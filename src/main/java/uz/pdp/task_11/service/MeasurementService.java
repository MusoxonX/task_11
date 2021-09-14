package uz.pdp.task_11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task_11.entity.Measurement;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

//    add
    public Result addMeasurement(@RequestBody Measurement measurementDto){
        boolean exists = measurementRepository.existsByName(measurementDto.getName());
        if (exists){
            return new Result("like this measurement already exists",false);
        }
        measurementRepository.save(measurementDto);
        return new Result("Measurement added to repository",true);
    }

//    get
    public List<Measurement> getMeasurement(){
        List<Measurement> all = measurementRepository.findAll();
        return all;
    }

//    get by Id
    public Measurement getById(@PathVariable Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()){
            return optionalMeasurement.get();
        }
        return new Measurement();
    }

//    update
    public Result editMeasurement(@PathVariable Integer id, @RequestBody Measurement measurementDto){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()){
            return new Result("measurement not found",false);
        }
        Measurement measurement = optionalMeasurement.get();
        measurement.setName(measurementDto.getName());
        measurementRepository.save(measurement);
        return new Result("measurement updated",true);
    }

//    delete
    public Result deleteMeasurement(@PathVariable Integer id){
        boolean exists = measurementRepository.existsById(id);
        if (exists){
            measurementRepository.deleteById(id);
            return new Result("measurement deleted",true);
        }
        return new Result("measurement not found",false);
    }
}
