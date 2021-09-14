package uz.pdp.task_11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.task_11.entity.Supplier;
import uz.pdp.task_11.payload.Result;
import uz.pdp.task_11.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

//    add
    public Result addSupplier(@RequestBody Supplier supplierDto){
        boolean exists = supplierRepository.existsByPhoneNumber(supplierDto.getPhoneNumber());
        if (exists){
            return new Result("like this phone number already exists",false);
        }
        supplierRepository.save(supplierDto);
        return new Result("Supplier added to repository",true);
    }

//    get
    public List<Supplier> getSupplier(){
        List<Supplier> all = supplierRepository.findAll();
        return all;
    }

//    get by Id
    public Supplier getById(@PathVariable Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()){
            return optionalSupplier.get();
        }
        return new Supplier();
    }

//    update
    public Result editSupplier(@PathVariable Integer id, @RequestBody Supplier supplierDto){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()){
            return new Result("Supplier not found",false);
        }
        Supplier supplier = optionalSupplier.get();
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplierRepository.save(supplier);
        return new Result("Supplier updated",true);
    }

//    delete
    public Result deleteSupplier(@PathVariable Integer id){
        boolean exists = supplierRepository .existsById(id);
        if (exists){
            supplierRepository.deleteById(id);
            return new Result("Supplier deleted",true);
        }
        return new Result("Supplier not found",false);
    }
}
