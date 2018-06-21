package com.thouhgtmechanix.organization.rest.api;

import com.thouhgtmechanix.organization.rest.dto.OrganizationDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface OrganizationApi {
    String API_URL = "v1/organizations";

    @RequestMapping(value = API_URL + "/{organizationId}", method = RequestMethod.GET)
    OrganizationDto getOrganization(@PathVariable("organizationId") String organizationId);
}
