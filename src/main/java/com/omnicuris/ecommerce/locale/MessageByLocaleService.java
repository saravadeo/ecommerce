package com.omnicuris.ecommerce.locale;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageByLocaleService {

  private final MessageSource messageSource;

  public MessageByLocaleService(final MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  public String getMessage(final String code) {
    return getMessage(code, null);
  }

  public String getMessage(final String code, final Object[] args) {
    return getMessage("en", code, args);
  }

  public String getMessage(final String variant, final String code,
      final Object[] args) {
    return messageSource.getMessage(code, args, new Locale(variant, "IN"));
  }
}
