package lv.javaguru.java2.services.resource.impls;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.resource.FindResourceService;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by user on 17.04.2017.
 */
@Component
class FindResourceServiceImpl implements FindResourceService {

    @Autowired
    @Qualifier("HibernateResourceDAO")
    private ResourceDAO resourceDAO;

    @Autowired
    private DataInputValidator inputValidator;
    @Autowired
    private DataExistValidator existValidator;

    @Override
    public Resource findResource(Long resourceId){
        inputValidator.validateResourceIdInput(resourceId);
        existValidator.validateResourceIdExists(resourceId);
        return resourceDAO.getByID(resourceId).get();
    }
}
