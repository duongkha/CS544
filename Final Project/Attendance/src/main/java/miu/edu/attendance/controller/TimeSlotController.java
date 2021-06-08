package miu.edu.attendance.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import miu.edu.attendance.domain.TimeSlot;
import miu.edu.attendance.dto.TimeSlotDTO;
import miu.edu.attendance.service.TimeSlotService;

@CrossOrigin
@RestController
@RequestMapping("/api/timeslots")
public class TimeSlotController {
    @Autowired
    TimeSlotService timeSlotService;
    
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<TimeSlotDTO> getAll(@RequestParam("page") Long pageNo, @RequestParam("size") Long size){
        List<TimeSlot> timeslots = timeSlotService.getAllTimeSlot().stream()
									.skip((pageNo - 1) * size).limit(size).collect(Collectors.toList());
        return timeslots.stream().map(x->modelMapper.map(x, TimeSlotDTO.class)).collect(Collectors.toList());
	}
    
    @PostMapping
    public Boolean addTimeSlot(@RequestBody TimeSlotDTO timeslotDTO){

        return timeSlotService.add(timeslotDTO);
    }
    
    @PostMapping("/update")
    public Boolean updateTimeSlot(@RequestBody TimeSlotDTO timeslotDTO){

        return timeSlotService.update(timeslotDTO);
    }
    
    @GetMapping("/delete/{id}")
    public Boolean deleteTimeSlot(@PathVariable Long id){
        return timeSlotService.delete(id);
    }
    
    @GetMapping({ "/{id}" })
    public @ResponseBody TimeSlotDTO getTimeSlot(@PathVariable Long id) {
        return modelMapper.map(timeSlotService.findTimeSlotByID(id), TimeSlotDTO.class);
    }
}
