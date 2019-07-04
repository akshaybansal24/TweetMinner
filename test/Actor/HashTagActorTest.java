package Actor;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import implementations.ReturnTwitterObject;

/**
 * This is to test hashtag actor
 * @author Akshay Bansal
 * @version 1.0
 */
public class HashTagActorTest {
	/**
	 * actorSystem - A object of ActorSystem
	 */
	public static ActorSystem actorSystem;
	/**
	 * searchActor - A object of ActorRef
	 */
	public static ActorRef searchActor;
	
	/**
	 * The method is to setup variable's for the test
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception
	{
		actorSystem = ActorSystem.create();
		searchActor = actorSystem.actorOf(HashTagActor.props());
		ReturnTwitterObject.isTest = true;
        ReturnTwitterObject.emotion = "HAPPY";
	}
	
	/**
	 * This method is to teardown the setup for test
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDown() throws Exception{
		actorSystem = null;
		searchActor = null;
		ReturnTwitterObject.isTest = false;
        ReturnTwitterObject.emotion = "";
	}
	
	/**
	 * This method run the test
	 */
	@Test
	public void test() {
		searchActor.tell(new HashTagActor.HashTagKey("test"), ActorRef.noSender());
		assertNotNull(searchActor);
	}
}