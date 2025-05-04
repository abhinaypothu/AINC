package com.example.controller;

import com.example.model.EMD;
import com.example.service.EMDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emds")
public class EMDController {

    @Autowired
    private EMDService emdService;

    @GetMapping
    public List<EMD> getAllEMDs() {
        return emdService.getAllEMDs();
    }

    @GetMapping("/{id}")
    public EMD getEMDById(@PathVariable Long id) {
        return emdService.getEMDById(id);
    }

    @PostMapping
    public EMD createEMD(@RequestBody EMD emd) {
        return emdService.createEMD(emd);
    }

    @PutMapping("/{id}")
    public EMD updateEMD(@PathVariable Long id, @RequestBody EMD emd) {
        return emdService.updateEMD(id, emd);
    }

    @DeleteMapping("/{id}")
    public void deleteEMD(@PathVariable Long id) {
        emdService.deleteEMD(id);
    }
}
