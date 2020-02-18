package ink.hansanshi.note.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import ink.hansanshi.note.dao.UserRepository;
import ink.hansanshi.note.dto.ServerResponse;
import ink.hansanshi.note.entity.UserDO;
import ink.hansanshi.note.service.IUserService;
import ink.hansanshi.note.util.MD5Util;
import ink.hansanshi.note.util.ThreadLocalUtil;
import ink.hansanshi.note.util.ValidateUtil;
import ink.hansanshi.note.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hansanshi
 * @date 2019/12/31
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private Cache<String, UserVo> userCache;

    @Value("${username:admin}")
    private String adminUsername;

    @Value("${password:admin}")
    private String adminPassword;

    @Value("${register-forbidden}")
    private boolean registerForbidden;

    @Autowired
    private UserRepository userRepository;

    private static final String TOKEN_PREFIX = "token_";

    @PostConstruct
    private void initAdminUser(){
        UserDO userDO = userRepository.findFirstByStatus(0);
        if (userDO == null){
            UserDO adminUser = new UserDO().setUsername(adminUsername).setPassword(MD5Util.MD5EncodeUtf8(adminPassword)).setStatus(0);
            userRepository.save(adminUser);
        }
    }

    @Override
    public ServerResponse<UserVo> login(String username, String password){
        String encodedPassword = MD5Util.MD5EncodeUtf8(password);
        UserDO userDO = userRepository.findByUsernameAndPassword(username, encodedPassword);
        if (userDO == null){
            return ServerResponse.buildErrorResponse("用户名或密码错误");
        }
        UserVo userVo = new UserVo().setToken(TOKEN_PREFIX + UUID.randomUUID().toString()).setUsername(username);
        userCache.put(userVo.getUsername(), userVo);
        return ServerResponse.buildSuccessResponse(userVo);
    }

    @Override
    public ServerResponse validate(String username, String token){
        UserVo userVo = userCache.getIfPresent(username);
        if (userVo == null || !token.equals(userVo.getToken())){
            return ServerResponse.buildErrorResponse("Invalid token");
        }else {
            return ServerResponse.buildSuccessResponse();
        }
    }

    @Override
    public ServerResponse logout() {
        userCache.invalidate(getUsername());
        return ServerResponse.buildSuccessResponse();
    }

    @Override
    public ServerResponse<UserVo> register(String username, String password){
        if (registerForbidden){
            throw new RuntimeException("Register forbidden");
        }
        ValidateUtil.notBlankString(username, password);
        ValidateUtil.notContainBlank(username,password);
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw new IllegalArgumentException("Arguments can't be blank");
        }
        UserDO userDO = userRepository.save(new UserDO().setUsername(username).setPassword(MD5Util.MD5EncodeUtf8(password)));
        UserVo userVo = new UserVo().setUsername(userDO.getUsername());
        return ServerResponse.buildSuccessResponse(userVo);
    }

    @Override
    @Transactional
    public ServerResponse changePassword(String oldPassword, String newPassword){
        ValidateUtil.notBlankString(oldPassword, newPassword);
        String oldEncodedPassword = MD5Util.MD5EncodeUtf8(oldPassword);
        UserDO userDO = userRepository.findByUsernameAndPassword(getUsername(), oldEncodedPassword);
        if (userDO == null){
            return ServerResponse.buildErrorResponse("Wrong password");
        }
        String newEncodedPassword = MD5Util.MD5EncodeUtf8(newPassword);
        userDO.setPassword(newEncodedPassword);
        userRepository.save(userDO);
        return ServerResponse.buildSuccessResponse();

    }

    private String getUsername(){
        return ThreadLocalUtil.getUsername();
    }

}
