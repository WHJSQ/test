package com.example.Interceptor;

import com.example.util.Constant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Administrator on 2018/9/4.
 */
public class ConstantValidatorHandler implements ConstraintValidator<Constant,String> {
    private  String constant;
    @Override
    public void initialize(Constant constant) {
        this.constant = constant.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
      //判断参数是否等于设置的字段值，返回结果
        return constant.equals(s);
    }
}
