package com.apuliasoft.test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.apuliasoft.test.model.Slot;
import com.apuliasoft.test.service.TimesheetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:values.properties")
public class TimesheetController {

    private List<Slot> slots = new ArrayList<>();

    @Autowired
    ObjectMapper objMapper;

    @Autowired
    TimesheetService tsService;

    @Autowired
    public void setValues(@Value("${data}") String values) throws JsonMappingException, JsonProcessingException {
        this.slots.addAll(objMapper.readValue(values, new TypeReference<List<Slot>>(){}));
    }

    @GetMapping(path = "api")
    List<Slot> getRes() {
        return this.slots;
    }

    @GetMapping(path = "api/filtered")
    List<Object[]> getFiltered(@RequestParam(name = "aggregateBy") Optional<String> aggregateBy) throws JsonMappingException, JsonProcessingException {
        List<String> by = Arrays.asList(aggregateBy.get().split(","));
        return this.tsService.aggregate(this.slots, by);
    }

}
