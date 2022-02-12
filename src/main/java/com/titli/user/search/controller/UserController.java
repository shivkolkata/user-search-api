package com.titli.user.search.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.titli.user.search.util.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.titli.user.search.entity.User;
import com.titli.user.search.service.IUserService;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	/*@GetMapping("/users")
    public List<User> fetchDepartmentList()
    {
        logger.debug("Inside User Controller");
        return userService.getAllUser();
    }*/

    //sample query url - http://localhost:8080/users?search=lastName:doe,age>25
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    @ResponseBody
    public List<User> search(@RequestParam(value = "search", required = false) String search) {
        List<SearchCriteria> params = new ArrayList<>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }
        return userService.searchUser(params);
    }
}
