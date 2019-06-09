package com.recruitment.exposition.manage;

import common.dto.EnterpriseDto;
import infra.mysql.EnterpriseRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import use_case.EnterpriseRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/enterprises")
public class EnterpriseController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<EnterpriseDto> getEnterprises() {
        EnterpriseRepository enterpriseRepository = new EnterpriseRepositoryImpl();
        return enterpriseRepository.getEnterprises();
    }
}
