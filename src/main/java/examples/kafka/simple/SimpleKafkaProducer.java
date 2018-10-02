package examples.kafka.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * This is example for simple Kafka Producer
 */
public class SimpleKafkaProducer {
    public static void main(String[] args){
        //Define Properties
        Properties props = new Properties();
        //Adding Kafka properties
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.setProperty(ProducerConfig.CLIENT_ID_CONFIG,"test");
        //props.setProperty(ProducerConfig.CLIENT_ID_CONFIG,"test");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //Kafka Producer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(props);
        //create and send message
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("test","Hello World from java client");
        kafkaProducer.send(record);
        kafkaProducer.close();
    }
}
