package com.example.demo.domain.model;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserForm {

    // 必須入力、20文字以下であること
    @NotBlank(message = "名前を入力してください。", groups = Group1.class)
    @Size(min = 1, max = 20, message = "名前を20文字以内で入力してください。", groups = Group2.class)
    private String name;

    // Email形式であること
    @Email(message = "E-Mailを正しい形式で入力してください。", groups = Group1.class)
    private String email;

    // 必須入力、入力値が0～100であること
    @NotNull(message = "年齢を入力してください。", groups = Group1.class)
    @Min(value = 0, message = "年齢は0以上を入力してください。", groups = Group2.class)
    @Max(value = 100, message = "年齢は100以下を入力してください。", groups = Group2.class)
    private Integer age;

    
    @Size(min = 1, max = 20, message = "備考は20文字以内で入力してください。", groups = Group2.class)
    private String remakes;
    
    public interface Group1 {}
    public interface Group2 {}

    @GroupSequence({Group1.class, Group2.class})
    public interface Groups {}

}