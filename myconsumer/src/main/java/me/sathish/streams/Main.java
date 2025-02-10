package me.sathish.streams;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    SathishStreamConsumer sathishStreamConsumer;

    public static void main(String[] args) {
        System.out.println("Calling Consumer!");
        SathishStreamConsumer sathishStreamConsumer = new SathishStreamConsumer();
        sathishStreamConsumer.consumeDataFromKafka();
    }
}
