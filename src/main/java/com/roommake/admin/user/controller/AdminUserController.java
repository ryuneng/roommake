package com.roommake.admin.user.controller;

import com.roommake.dto.Criteria;
import com.roommake.dto.ListDto;
import com.roommake.user.enums.UserStatusEnum;
import com.roommake.user.mapper.UserMapper;
import com.roommake.user.service.UserService;
import com.roommake.user.vo.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
@RequiredArgsConstructor
@Tag(name = "관리자 회원 관리 API", description = "회원리스트를 반환한다.")
public class AdminUserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "회원리스트 조회", description = "회원목록을 조회한다.")
    @GetMapping("/userList")
    public String userList(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                           @RequestParam(name = "sort", required = false, defaultValue = "date") String sort,
                           @RequestParam(name = "filt", required = false, defaultValue = "all") String filt,
                           @RequestParam(name = "opt", required = false) String opt,
                           @RequestParam(name = "keyword", required = false) String keyword,
                           Model model) {

        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setRows(rows);
        criteria.setSort(sort);
        criteria.setFilt(filt);

        if (StringUtils.hasText(opt) && StringUtils.hasText(keyword)) {
            criteria.setOpt(opt);
            criteria.setKeyword(keyword);
        }

        ListDto<User> dto = userService.getUsers(criteria);

        model.addAttribute("criteria", criteria);
        model.addAttribute("paging", dto.getPaging());
        model.addAttribute("users", dto.getItems());

        return "admin/user/user-list";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody List<Integer> userIds) {
        for (int userId : userIds) {
            User user = userMapper.getUserById(userId);
            user.setStatus(UserStatusEnum.BLOCK.getStatus());
            user.setUpdateDate(new Date());
            userService.modifyUser(user);
        }
        return "redirect:/admin/user/userList";
    }

    @GetMapping("/point")
    public String point() {
        return "admin/user/point";
    }

    @GetMapping("/grade")
    public String grade() {
        return "admin/user/grade";
    }
}
