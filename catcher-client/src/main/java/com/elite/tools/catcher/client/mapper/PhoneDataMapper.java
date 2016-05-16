package com.elite.tools.catcher.client.mapper;

import com.elite.tools.catcher.client.domain.PhoneDataVo;
import com.elite.tools.catcher.core.domain.PhoneData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by df on 16/5/15.
 */
@Mapper
public interface PhoneDataMapper {
    PhoneDataMapper INSTANCE = Mappers.getMapper(PhoneDataMapper.class);

    @Mappings({
            @Mapping(target = "name", source = "phoneData.contactName"),
            @Mapping(target = "mobilePhone", source = "phoneData.mobile"),
            @Mapping(target = "telephone", source = "phoneData.phone"),
            @Mapping(target = "email", source = "phoneData.email")
    })
    PhoneDataVo boToVo(PhoneData phoneData);

    List<PhoneDataVo> bosToVos(List<PhoneData> phoneDatas);

}
