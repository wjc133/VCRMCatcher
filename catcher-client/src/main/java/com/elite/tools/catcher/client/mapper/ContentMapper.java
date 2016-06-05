package com.elite.tools.catcher.client.mapper;

import com.elite.tools.catcher.core.constant.ContentVo;
import com.elite.tools.catcher.core.domain.Content;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Created by df on 16/5/15.
 */
@Mapper
public interface ContentMapper {
    ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);

    @Mappings({
            @Mapping(target = "acctId", source = "content.acctId"),
            @Mapping(target = "companyName", source = "content.companyName"),
            @Mapping(target = "phoneDataVos", ignore = true)
    })
    ContentVo boToVo(Content content);
}
