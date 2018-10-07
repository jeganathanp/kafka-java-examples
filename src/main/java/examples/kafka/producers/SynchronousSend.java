package examples.kafka.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * This is example for kafka synchronous send
 * will fire send method and wait for reply from the server
 */
public class SynchronousSend {
    public static void main(String[] args) {
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
        //this will fire and leave kafka to handle the message
        try {
            RecordMetadata data = kafkaProducer.send(record).get();
            System.out.println("Written in topic "+ data.topic());
            System.out.println("Offset detail "+ data.offset());
            System.out.println("Written to partition "+ data.partition());
        } catch (InterruptedException e) {
           e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        kafkaProducer.close();
    }
}
