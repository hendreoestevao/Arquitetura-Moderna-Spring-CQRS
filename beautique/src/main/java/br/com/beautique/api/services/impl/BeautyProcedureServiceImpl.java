package br.com.beautique.api.services.impl;

import br.com.beautique.api.dtos.BeautyProcedureDTO;
import br.com.beautique.api.entities.BeautyProceduresEntity;
import br.com.beautique.api.repositories.BeautyProcedureRepository;
import br.com.beautique.api.services.BeautyProcedureService;
import br.com.beautique.api.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    private final ConverterUtil<BeautyProceduresEntity, BeautyProcedureDTO> converterUtil = new ConverterUtil<>(BeautyProceduresEntity.class, BeautyProcedureDTO.class)

    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO) {
        return null;
    }
}
