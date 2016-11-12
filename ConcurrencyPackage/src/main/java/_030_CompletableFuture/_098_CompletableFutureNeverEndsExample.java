package _030_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class _098_CompletableFutureNeverEndsExample
{

    public static void main( String[] args ) throws InterruptedException, ExecutionException
    {
        // you can create a completable future directly using its constructor
        CompletableFuture<String> completableFuture = new CompletableFuture<String>();

        // if we try to use it now, our program will never end
        completableFuture.get();

    }

}
