package com.thoughtmechanix.licenses.clients;

import com.thouhgtmechanix.organization.rest.api.OrganizationApi;
import com.thouhgtmechanix.organization.rest.dto.OrganizationDto;
import org.springframework.stereotype.Component;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "organizationservice", fallback = OrganizationApiClient.OrganizationApiFallback.class)
public interface OrganizationApiClient extends OrganizationApi {

    @Component
    class OrganizationApiFallback implements OrganizationApiClient {
        @Override
        public OrganizationDto getOrganization(String organizationId) {
            return new OrganizationDto();
        }
    }
}
