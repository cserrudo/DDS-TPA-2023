package ar.edu.utn.frba.dds.servidor.utils;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;

public class LimitHelper implements Helper<Object> {
  @Override
  public CharSequence apply(Object context, Options options) throws IOException {
    if (context instanceof Iterable<?>) {
      int limit = options.param(0, 0);
      StringBuilder result = new StringBuilder();
      int count = 0;

      for (Object item : (Iterable<?>) context) {
        if (count >= limit) {
          break;
        }
        result.append(options.fn(item));
        count++;
      }

      return result.toString();
    }
    return "";
  }
}
