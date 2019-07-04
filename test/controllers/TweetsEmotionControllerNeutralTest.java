package controllers;

import static org.junit.Assert.*;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import akka.actor.ActorSystem;
import controllers.HashTagController;
import implementations.ReturnTwitterObject;
import play.mvc.Result;
import twitter4j.TwitterException;

/**
 * This class is to test tweets emotion controller
 * @author Akshay Bansal
 * @version 1.0
 *
 */
public class TweetsEmotionControllerNeutralTest {

	/**
	 * controller - A object of TweetsEmotionController.
	 */
	public static TweetsEmotionController controller;
	/**
	 * system - A object of ActorSystem.
	 */
	public static ActorSystem system;
	 
    /**
     * The method is to setup variable's for the test
     */
	@BeforeClass
	public static void setUp() {
		system = ActorSystem.create();
		controller = new TweetsEmotionController(system);
		ReturnTwitterObject.isTest = true;
		ReturnTwitterObject.emotion = "NEUTRAL";
	}
	 /**
     * This method is to teardown the setup for test
     */
	@AfterClass
	public static void tearDown() {
		controller = null;
		system = null;
		ReturnTwitterObject.isTest = false;
		ReturnTwitterObject.emotion = "";
	}
	
    /**
     * This method run the test
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TwitterException
     */
    @Test
    public void searchTweetsByHashTag() throws InterruptedException, ExecutionException, TwitterException {
    	CompletionStage<Result> result = controller.getTweetsEmotion("happy");
		Result r = result.toCompletableFuture().get();
		assertNotNull(r);
    }

}
