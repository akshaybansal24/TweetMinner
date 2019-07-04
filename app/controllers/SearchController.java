package controllers;

import play.libs.F;
import play.libs.streams.ActorFlow;
import akka.actor.*;
import akka.stream.*;
import play.mvc.*;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import Actor.SearchTweetsActor;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 * @author akshay bansal
 * @version 1.0
 **/
public class SearchController extends Controller {

	/**
	 * ActorSystem Object
	 */
	@com.google.inject.Inject private ActorSystem actorSystem;
	/**
	 *Materializer Object
	 */
	@com.google.inject.Inject private  Materializer materializer;
		
	/**
	 * This method is used to make connection to websocket
	 * It will provide the connection for pushing tweets.
	 * @return WebSocket
	 */
	public WebSocket socket() {
		System.out.println("in controller");
		return WebSocket.Text.acceptOrResult(request->{
			if(originCheck(request)){
				return CompletableFuture.completedFuture(
						F.Either.Right(ActorFlow.actorRef(SearchTweetsActor::props, actorSystem, materializer)));
			}
			else {
				return CompletableFuture.completedFuture(F.Either.Left(forbidden()));
			}
		});
	}
	
    /**
     * This method will check if the request is coming from reliable source or not.
     * @param request of type Http.RequestHeader
     * @return boolean
     */
    private boolean originCheck(Http.RequestHeader request) {
		final Optional<String> origin = request.header("Origin");
		if(! origin.isPresent()) {
			return false;
		}else if(origin.get().contains("localhost:9000") || origin.get().contains("localhost:19001")) {
			return true;
		}else {
			return false;
		}
	}

	/**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(search.render());
    }

}
