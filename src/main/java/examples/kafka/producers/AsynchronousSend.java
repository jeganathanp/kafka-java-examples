package examples.kafka.producers;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Example for asynchronous send in kafka
 * where call back method is tied to send methog.
 */
public class AsynchronousSend {

    public static void main(String[] args){
        AsynchronousSend as = new AsynchronousSend();
        as.sendMessage();
    }

    public void sendMessage(){
        //Define Properties
        Properties props = new Properties();
        //Adding Kafka properties
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "test");
        //props.setProperty(ProducerConfig.CLIENT_ID_CONFIG,"test");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //Kafka Producer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);
        //create and send message
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("test", "Hello World from java client");
        //this will fire and wait asynchronously
        kafkaProducer.send(record, new DemoProducerCallBack());
        kafkaProducer.close();
    }

}

class DemoProducerCallBack implements Callback {
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        System.out.println("Written in topic " + recordMetadata.topic());
        System.out.println("Offset detail " + recordMetadata.offset());
        System.out.println("Written to partition " + recordMetadata.partition());
    }
}
