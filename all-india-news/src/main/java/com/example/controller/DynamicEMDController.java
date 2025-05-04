package com.example.controller;

import com.example.model.DynamicEMD;
import com.example.service.DynamicEMDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dynamicemds")
public class DynamicEMDController {

    @Autowired
    private DynamicEMDService dynamicEMDService;

    @GetMapping
    public List<DynamicEMD> getAllDynamicEMDs() {
        return dynamicEMDService.getAllDynamicEMDs();
    }

    @GetMapping("/{id}")
    public DynamicEMD getDynamicEMDById(@PathVariable Long id) {
        return dynamicEMDService.getDynamicEMDById(id);
    }

    @PostMapping
    public DynamicEMD createDynamicEMD(@RequestBody DynamicEMD dynamicEMD) {
        return dynamicEMDService.createDynamicEMD(dynamicEMD);
    }

    @PutMapping("/{id}")
    public DynamicEMD updateDynamicEMD(@PathVariable Long id, @RequestBody DynamicEMD dynamicEMD) {
        return dynamicEMDService.updateDynamicEMD(id, dynamicEMD);
    }

    @DeleteMapping("/{id}")
    public void deleteDynamicEMD(@PathVariable Long id) {
        dynamicEMDService.deleteDynamicEMD(id);
    }
}
