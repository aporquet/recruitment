package use_case;

import common.dto.EnterpriseDto;

import java.util.List;

public interface EnterpriseRepository {

    List<EnterpriseDto> getEnterprises();
}
