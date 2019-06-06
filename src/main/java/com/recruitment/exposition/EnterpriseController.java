package com.recruitment.exposition;

import com.recruitment.domains.Enterprise;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {
    private final EnterpriseRepository entrepriseRepository;

    public EnterpriseController(final EnterpriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @GetMapping
    public List<Enterprise> toto() {
    return this.entrepriseRepository.findAll();
    }
}
