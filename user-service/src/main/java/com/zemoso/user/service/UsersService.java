package com.zemoso.user.service;

import com.zemoso.user.valueobjects.Department;
import com.zemoso.user.valueobjects.ResponseTemplateVO;
import com.zemoso.user.entity.Users;
import com.zemoso.user.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Users saveUsers(Users users) {
        log.info("Inside saveUser method of UserService");
        return usersRepository.save(users);
    }

    public ResponseTemplateVO getUserWithDepartment(Long usersId) {
        log.info("Inside getUserWithDepartment method of UsersService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Users users = usersRepository.findByUsersId(usersId);
        Department department =
                restTemplate.getForObject(
                        "http://DEPARTMENT-SERVICE/departments/"
                                + users.getDepartmentId(),
                        Department.class);
        vo.setUsers(users);
        vo.setDepartment(department);
        return vo;
    }
}
