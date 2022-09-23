package org.example;

import io.micronaut.runtime.Micronaut;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Micronaut.build(args)
                .mainClass(App.class)
                .start();
    }
}
