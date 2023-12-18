package ar.edu.utn.frba.dds.servidor.utils;

import java.io.IOException;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class IfGreaterThanHelper implements Helper<Object> {
  @Override
  public CharSequence apply(Object context, Options options) throws IOException {
    int length = context instanceof Integer ? (int) context : 0;
    int comparisonValue = options.param(0, 0);

    if (length > comparisonValue) {
      return options.fn(this);
    } else {
      return options.inverse(this);
    }
  }
}
