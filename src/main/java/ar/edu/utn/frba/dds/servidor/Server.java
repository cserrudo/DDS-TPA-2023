package ar.edu.utn.frba.dds.servidor;

import ar.edu.utn.frba.dds.servidor.handlers.AppHandlers;
import ar.edu.utn.frba.dds.servidor.init.Initializer;
import ar.edu.utn.frba.dds.servidor.middlewares.AuthMiddleware;
import ar.edu.utn.frba.dds.servidor.utils.IfEquals;
import ar.edu.utn.frba.dds.servidor.utils.IfGreaterThanHelper;
import ar.edu.utn.frba.dds.servidor.utils.LimitHelper;
import ar.edu.utn.frba.dds.servidor.utils.PrettyProperties;
import ar.edu.utn.frba.dds.servidor.utils.SubtractHelper;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.JavalinRenderer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Server {
    private static Javalin app = null;

    public static Javalin app() {
        if (app == null)
            throw new RuntimeException("App no inicializada");
        return app;
    }

    public static void init() {
        if (app == null) {
            PrettyProperties.getInstance();
            Integer port = Integer.parseInt(System.getProperty("port", "8080"));
            app = Javalin.create(config()).start(port);
            initTemplateEngine();
            AppHandlers.applyHandlers(app);
            Router.init();
            if (Boolean.parseBoolean(PrettyProperties.getInstance().propertyFromName("dev_mode"))) {
                Initializer.init();
            }
        }
    }

    private static Consumer<JavalinConfig> config() {
        return config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);
            AuthMiddleware.apply(config);
        };
    }

    private static void initTemplateEngine() {
        JavalinRenderer.register(
                (path, model, context) -> {
                    TemplateLoader loader = new ClassPathTemplateLoader();
                    loader.setPrefix("/templates");
                    loader.setSuffix(".hbs");

                    try {
                        Handlebars handlebars = new Handlebars(loader);
                        handlebars.registerHelper("ifGreaterThan", new IfGreaterThanHelper());
                        handlebars.registerHelper("subtract", new SubtractHelper());
                        handlebars.registerHelper("limit", new LimitHelper());
                        handlebars.registerHelper("ifEquals", new IfEquals());

                        String name = path.replace(".hbs", "");
                        Template template = handlebars.compile(name);

                        return template.apply(model);
                    } catch (IOException e) {
                        e.printStackTrace();
                        context.status(HttpStatus.NOT_FOUND);
                        return "No se encuentra la página indicada...";
                    }
                }, ".hbs");
    }
}
