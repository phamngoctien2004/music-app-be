package com.tien.music_app.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tien.music_app.Enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {
    private String id;
    @NotBlank
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Vui lòng nhập tên người dùng")
    private String name;

    @NotNull(message = "Vui lòng chọn giới tính")
    private Gender gender;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate birth;
    private String avatar;
}
