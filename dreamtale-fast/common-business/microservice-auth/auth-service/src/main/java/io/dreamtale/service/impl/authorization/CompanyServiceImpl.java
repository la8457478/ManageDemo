package io.dreamtale.service.impl.authorization;

import org.springframework.stereotype.Service;

import io.dreamtale.bean.entity.authorization.Company;
import io.dreamtale.dao.authorization.company.CompanyDao;
import io.dreamtale.mybatisplus.extend.common.base.service.BaseService;
import io.dreamtale.service.authorization.CompanyService;
@Service
public class CompanyServiceImpl  extends BaseService<CompanyDao, Company> implements CompanyService {

}
