package com.example.service;

import com.example.model.EMD;
import com.example.repository.EMDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EMDService {

    @Autowired
    private EMDRepository emdRepository;

    public List<EMD> getAllEMDs() {
        return emdRepository.findAll();
    }

    public EMD getEMDById(Long id) {
        return emdRepository.findById(id).orElse(null);
    }

    public EMD createEMD(EMD emd) {
        return emdRepository.save(emd);
    }

    public EMD updateEMD(Long id, EMD emd) {
        EMD existingEMD = emdRepository.findById(id).orElse(null);
        if (existingEMD != null) {
            emd.setId(id);
            return emdRepository.save(emd);
        }
        return null;
    }

    public void deleteEMD(Long id) {
        emdRepository.deleteById(id);
    }
}
