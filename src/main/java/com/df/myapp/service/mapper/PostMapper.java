package com.df.myapp.service.mapper;

import com.df.myapp.domain.Post;
import com.df.myapp.domain.User;
import com.df.myapp.service.dto.PostDTO;
import com.df.myapp.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Post} and its DTO {@link PostDTO}.
 */
@Mapper(componentModel = "spring")
public interface PostMapper extends EntityMapper<PostDTO, Post> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userLogin")
    PostDTO toDto(Post s);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);
}
