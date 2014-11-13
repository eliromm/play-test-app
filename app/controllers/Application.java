package controllers;

import java.util.Map;
import java.util.HashMap;
import org.fluentd.logger.FluentLogger;

import play.libs.Json;
import com.fasterxml.jackson.databind.node.*;
import com.fasterxml.jackson.databind.JsonNode;

import play.*;
import play.mvc.*;
import play.api.Logger;
import play.mvc.Http.Request;

import views.html.*;

public class Application extends Controller {
  private static final FluentLogger LOG = FluentLogger.getLogger("app","127.0.0.1", 24224);

  public static Result index() {
      return ok(index.render("Your new application is ready."));
  }

  public static Result play-test-app() {
    JsonNode json = request().body().asJson();
    ObjectNode result = Json.newObject();
    String name = json.findPath("name").textValue();
    if(name == null) {
      result.put("status", "KO");
      result.put("message", "Missing parameter [name]");
      return badRequest(result);
    } else {
      result.put("status", "OK");
      result.put("message", "Hello " + name);
      return ok(result);
    }
  }

  public static Result ip() {
    ObjectNode result = Json.newObject();
    Request req = play.mvc.Http.Context.current().request();
    String x = req.getHeader("X-Forwarded-For") ;
    String p = req.getHeader("X-Forwarded-Proto") ;
    String ua = request().getHeader("User-Agent");
    String r = request().remoteAddress();
    //
    result.put("x-forwarded-for", x );
    result.put("x-forwarded-prot", p );
    result.put("user-agent", ua );
    result.put("remote_address", r );
    //
    Map<String, Object> data = new HashMap<>();
    data.put("x-forwarded-for", x );
    data.put("x-forwarded-prot", p );
    data.put("user-agent", ua );
    data.put("remote_address", r );
    //
    LOG.log("remote-test", data);
    return ok(result);
  }
}
