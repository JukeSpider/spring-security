package com.customer.mapper;

import com.customer.dto.RoleDto;
import com.customer.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleMapper extends IBaseMapper<RoleEntity, RoleDto> {

}