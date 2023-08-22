package com.mentor;

public class TryToHandleError {

    public void call() {

        try {
            recursion();
        } catch (StackOverflowError err) {
            System.out.println(err);
        } finally {
            System.out.println("Do final");
        }

        System.out.println("Continue execution");
    }

    public void recursion() {
        recursion();
    }
}
