package com.wcyv90.sp.cache.redis.domain.dto;

import com.wcyv90.sp.cache.redis.domain.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private LocalDateTime createTime;

    public static UserDTO from(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

}
