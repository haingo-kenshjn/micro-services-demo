package com.thoughtmechanix.organization.controllers;


import com.thoughtmechanix.organization.model.Organization;
import com.thoughtmechanix.organization.services.OrganizationService;
import com.thouhgtmechanix.organization.rest.api.OrganizationApi;
import com.thouhgtmechanix.organization.rest.dto.OrganizationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizationServiceController implements OrganizationApi {
    @Autowired
    private OrganizationService orgService;
    private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceController.class);

    public OrganizationDto getOrganization(@PathVariable("organizationId") String organizationId) {
        logger.debug("Looking up data for org {}", organizationId);

        Organization org = orgService.getOrg(organizationId);
        org.setContactName(org.getContactName());

        OrganizationDto dto = new OrganizationDto();
        BeanUtils.copyProperties(org, dto);

        return dto;
    }

    public void updateOrganization(@PathVariable("organizationId") String orgId, @RequestBody OrganizationDto dto) {
        Organization org = new Organization();
        BeanUtils.copyProperties(dto, org);
        orgService.updateOrg(org);

    }

    public void saveOrganization(@RequestBody OrganizationDto dto) {
        Organization org = new Organization();
        BeanUtils.copyProperties(dto, org);
        orgService.saveOrg(org);
    }

    public void deleteOrganization(@PathVariable("organizationId") String orgId) {
        orgService.deleteOrg(orgId);

    }
}
