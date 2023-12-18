package ar.edu.utn.frba.dds.servidor.utils;

import java.io.IOException;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class IfEquals implements Helper<Object> {
  @Override
  public CharSequence apply(Object context, Options options) throws IOException {
    String a = context instanceof String ? (String) context : "";
    String b = options.param(0, "");

    if (a.equals(b)) {
      return options.fn(this);
    } else {
      return options.inverse(this);
    }
  }
}
