package ink.hansanshi.note.service;

import ink.hansanshi.note.dto.ServerResponse;
import ink.hansanshi.note.vo.UserVo;


/**
 * @author hansanshi
 * @date 2019/12/31
 */
public interface IUserService {

    ServerResponse<UserVo> login(String username, String password);

    ServerResponse validate(String username, String token);

    ServerResponse logout();

    ServerResponse<UserVo> register(String username, String password);

    ServerResponse changePassword(String oldPassword, String newPassword);
}
