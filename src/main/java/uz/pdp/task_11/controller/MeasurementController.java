package uz.pdp.task_11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_11.entity.Measurement;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;

    @PostMapping()
    public Result addMeasurement(@RequestBody Measurement measurementDto){
        Result result = measurementService.addMeasurement(measurementDto);
        return result;
    }

    @GetMapping()
    public List<Measurement> getMeasurement(){
        List<Measurement> measurement = measurementService.getMeasurement();
        return measurement;
    }

    @GetMapping("/{id}")
    public Measurement getById(@PathVariable Integer id){
        Measurement measurement = measurementService.getById(id);
        return measurement;
    }

    @PutMapping("/{id}")
    public Result updateMeasurement(@PathVariable Integer id, @RequestBody Measurement measurementDto){
        Result result = measurementService.editMeasurement(id, measurementDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable Integer id){
        Result result = measurementService.deleteMeasurement(id);
        return result;
    }
}
