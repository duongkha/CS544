package miu.edu.attendance.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.dto.BarcodeRecordDTO;
import miu.edu.attendance.service.BarcodeRecordService;

@CrossOrigin
@RestController
@RequestMapping("/api/barcoderecord")
public class BarcodeRecordController {
    @Autowired
    BarcodeRecordService barcodeRecordService;
    
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<BarcodeRecordDTO> getAll(@RequestParam("page") Long pageNo, @RequestParam("size") Long size){
        List<BarcodeRecord> barcodeRecords = barcodeRecordService.getAllBarcodeRecord().stream()
        						.skip((pageNo - 1) * size).limit(size).collect(Collectors.toList());
        return barcodeRecords.stream().map(x->modelMapper.map(x, BarcodeRecordDTO.class)).collect(Collectors.toList());
	}
    
    @PostMapping
    public Boolean addBarcodeRecord(@RequestBody BarcodeRecordDTO barcodeRecordDTO){
        return barcodeRecordService.add(barcodeRecordDTO);
    }
    
    
    @GetMapping("/delete/{id}")
    public Boolean deleteBarcodeRecord(@PathVariable Long id){
        return barcodeRecordService.delete(id);
    }
    
    @GetMapping({ "/{id}" })
    public @ResponseBody BarcodeRecordDTO getBarcodeRecord(@PathVariable Long id) {
        return modelMapper.map(barcodeRecordService.findBarcodeRecordByID(id), BarcodeRecordDTO.class);
    }
}
