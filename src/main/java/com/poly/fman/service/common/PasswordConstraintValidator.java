package com.poly.fman.service.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.MessageResolver;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.PropertiesMessageResolver;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import com.poly.fman.interfaces.ValidPassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Data;

@Data
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    public List<String> messages;

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        try {
            // 5 dòng đầu dùng để load file user.properties và encode lại UTF-8
            URL resource = this.getClass().getClassLoader().getResource("message/user.properties");
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream(resource.getPath());
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            props.load(isr);

            MessageResolver resolver = new PropertiesMessageResolver(props);
            PasswordValidator validator = new PasswordValidator(
                    resolver,
                    new LengthRule(8, 16),
                    new WhitespaceRule(),
                    new CharacterRule(EnglishCharacterData.UpperCase, 1),
                    new CharacterRule(EnglishCharacterData.LowerCase, 1),
                    new CharacterRule(EnglishCharacterData.Digit, 1),
                    new CharacterRule(EnglishCharacterData.Special, 1));

            RuleResult result = validator.validate(new PasswordData(password));
            if (result.isValid()) {
                return true;
            }
            messages = validator.getMessages(result);

            String messageTemplate = messages.stream()
                    .collect(Collectors.joining(", "));
            context.buildConstraintViolationWithTemplate(messageTemplate)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

        // PasswordValidator validator = new PasswordValidator(Arrays.asList(
        // // at least 8 characters
        // new LengthRule(8, 30),

        // // ít nhất 1 ký tự in hoa
        // new CharacterRule(EnglishCharacterData.UpperCase, 1),

        // // at least one lower-case character
        // new CharacterRule(EnglishCharacterData.LowerCase, 1),

        // // at least one digit character
        // new CharacterRule(EnglishCharacterData.Digit, 1),

        // // at least one symbol (special character)
        // new CharacterRule(EnglishCharacterData.Special, 1),

        // // no whitespace
        // new WhitespaceRule()

        // ));

        return false;
    }

}