package ar.edu.utn.frba.dds.servidor.utils;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;

public class SubtractHelper implements Helper<Object> {
  @Override
  public CharSequence apply(Object context, Options options) throws IOException {
    if (context instanceof Integer) {
      int value = (int) context;
      int subtractBy = options.param(0, 0);

      return Integer.toString(value - subtractBy);
    }

    return "";
  }
}
